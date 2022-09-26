package com.bracket

import grails.gorm.transactions.Transactional

@Transactional
class TeamGameService {
    Integer numberOfBoards = 4;

    def dropGames() {
        Round.deleteAll(Round.list())
        TeamGame.deleteAll(TeamGame.list())
    }

    def createRoundRobin() {
        List<Team> teamList = Team.list()
        Integer half = Math.floor(teamList.size() / 2).toInteger()
        while (teamList) {
            Team homeTeamObj = teamList.pop()
            teamList.each { Team awayTeamObj ->
                Round roundObj = findRound(homeTeamObj, awayTeamObj)
                if (homeTeamObj.homeGames.size() >= half) {
                    TeamGame.findOrSaveWhere(awayTeam: homeTeamObj, homeTeam: awayTeamObj, awayScore: 0, homeScore: 0, round: roundObj).save(flush: true)
                } else {
                    TeamGame.findOrSaveWhere(awayTeam: awayTeamObj, homeTeam: homeTeamObj, awayScore: 0, homeScore: 0, round: roundObj).save(flush: true)
                }
                homeTeamObj.refresh()
                awayTeamObj.refresh()
            }
        }
        Round.list().each { Round rndObj ->
            if (TeamGame.countByRound(rndObj) != numberOfBoards) {
                TeamGame.findAllByRound(rndObj).each { TeamGame tmObj ->
                    for (Round rnditObj in Round.list()) {
                        if (TeamGame.countByRound(rnditObj) != numberOfBoards) {
                            TeamGame teamRound = TeamGame.withCriteria {
                                eq("round", rnditObj)
                                or {
                                    awayTeam {
                                        or {
                                            eq("id", tmObj.homeTeamId)
                                            eq("id", tmObj.awayTeamId)
                                        }
                                    }
                                    homeTeam {
                                        or {
                                            eq("id", tmObj.homeTeamId)
                                            eq("id", tmObj.awayTeamId)
                                        }
                                    }
                                }
                            }?.getAt(0)
                            if (!teamRound && rnditObj.sortOrder < tmObj.round.sortOrder ) {
                                tmObj.round = rnditObj
                                tmObj.save(flush:true)
                                break
                            }

                        }
                    }
                }
            }
            rndObj.refresh()
        }
        Round.list().each { Round rndObj ->
            int rtn = TeamGame.countByRound(rndObj)
            if (rtn == 0) {
                rndObj.delete(flush: true)
            }

        }


    }

    Round findRound(Team awayTeamObj, Team homeTeamObj) {
        def teamRound
        Integer round = 0
        Round roundObj
        do {
            roundObj = Round.findOrSaveWhere(name: "${++round}", sortOrder: round).save(flush: true)
            teamRound = TeamGame.withCriteria {
                eq("round", roundObj)
                or {
                    awayTeam {
                        or {
                            eq("id", homeTeamObj.id)
                            eq("id", awayTeamObj.id)
                        }
                    }
                    homeTeam {
                        or {
                            eq("id", homeTeamObj.id)
                            eq("id", awayTeamObj.id)
                        }
                    }
                }
            }?.getAt(0)
        } while (teamRound || TeamGame.countByRound(roundObj) >= numberOfBoards)


        //see if we can put this in an earlier round
        for (int roundSearch = 1; roundSearch <= roundObj.sortOrder; roundSearch++) {
            Round swapRound = Round.findBySortOrder(roundSearch)
            def searchRound = TeamGame.findAllByRound(swapRound)
            TeamGame teamSwap
            TeamGame teamCheck = null
            Integer searchRoundCount = 0
            do {
                try {
                    teamCheck = searchRound[searchRoundCount++]
                    teamSwap = TeamGame.withCriteria {
                        eq("round", roundObj)
                        or {
                            awayTeam {
                                or {
                                    eq("id", teamCheck.homeTeamId)
                                    eq("id", teamCheck.awayTeamId)
                                }
                            }
                            homeTeam {
                                or {
                                    eq("id", teamCheck.homeTeamId)
                                    eq("id", teamCheck.awayTeamId)
                                }
                            }
                        }
                    }?.getAt(0)
                    def noLook = TeamGame.withCriteria {
                        eq("round", swapRound)
                        or {
                            awayTeam {
                                or {
                                    eq("id", homeTeamObj.id)
                                    eq("id", awayTeamObj.id)
                                }
                            }
                            homeTeam {
                                or {
                                    eq("id", homeTeamObj.id)
                                    eq("id", awayTeamObj.id)
                                }
                            }
                        }
                    }?.getAt(0)
                    if (!teamSwap && !noLook) {
                        teamCheck.round = roundObj
                        teamCheck.save(flush: true)
                        return swapRound
                    }
                } catch (Exception e) {
                    teamCheck = null
                }
            } while (teamCheck)
        }
        roundObj
    }

    def getRounds (def order) {
        if(order && order == 'desc') {
            TeamGame.list().sort { x, y -> y.round.sortOrder <=> x.round.sortOrder }
        } else {
            TeamGame.list().sort { x, y -> x.round.sortOrder <=> y.round.sortOrder }
        }
    }

    def saveItems(itemList) {
        itemList.each {
            TeamGame game = TeamGame.findById(it.id)
            game.awayScore = it.awayScore.toInteger()
            game.homeScore = it.homeScore.toInteger()
            game.winner = game.awayScore > game.homeScore ? game.awayTeam : ( game.awayScore < game.homeScore ? game.homeTeam : null )
            game.loser = game.winner && game.winner == game.homeTeam ? game.awayTeam : (game.winner ? game.awayTeam : null)
            game.save(flush:true)
        }
    }
}
