package com.bracket

import grails.gorm.transactions.Transactional

@Transactional
class TeamGameService {

    def dropGames() {
        Round.deleteAll(Round.list())
        TeamGame.deleteAll(TeamGame.list())
    }

    def createRoundRobin() {
        int teamsCount = Team.count()
        List<Team> teamList = Team.list()
        int totalRounds
        int matchesPerRound
        Team home, away, tmp
        if(teamsCount % 2 == 0 ) {
            totalRounds =   teamsCount  - 1
            matchesPerRound = Math.floor(teamsCount / 2)
        } else {
            totalRounds =   teamsCount
            matchesPerRound = Math.floor((teamsCount - 1) / 2)
        }

        if(teamsCount % 2 == 0) {
            Integer half = Math.ceil(teamList.size() / 2).toInteger()
            while(teamList) {
                Team homeTeamObj = teamList.pop()
                teamList.each {Team awayTeamObj ->
                    TeamGame teamRound
                    Integer round = 0
                    Round roundObj
                    do {
                        roundObj = Round.findOrSaveWhere(name: "${++round}").save(flush:true)
                        teamRound = TeamGame.withCriteria {
                           eq("round", roundObj)
                            or{
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
                    } while(teamRound)

                    if(homeTeamObj.homeGames.size() >= half) {
                        TeamGame.findOrSaveWhere(awayTeam: homeTeamObj, homeTeam: awayTeamObj, awayScore: 0, homeScore: 0, round: roundObj).save(flush:true)
                    } else {
                        TeamGame.findOrSaveWhere(awayTeam: awayTeamObj, homeTeam: homeTeamObj, awayScore: 0, homeScore: 0, round: roundObj).save(flush:true)
                        homeTeamObj.refresh()
                    }
                }
            }
        }
         else {
            for (int round = 1; round < totalRounds; round++) {
                Round roundObj = Round.findOrSaveWhere( name :  "${round}")
                    for (int match = 0; match < matchesPerRound; match++) {
                        home = teamList[match + 1]
                        away = teamList[(teamsCount - 1) - match]
                        TeamGame.findOrSaveWhere(awayTeam: away, homeTeam: home, awayScore: 0, homeScore: 0, round: roundObj).save()
                    }
                }
            tmp = teamList[teamsCount - 1];
            for (int i = teamsCount - 1; i > 0; i--) {
                teamList[i] = teamList[i - 1];
            }
            teamList[0] = tmp

        }

    }
}
