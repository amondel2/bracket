package com.bracket

import groovy.json.JsonSlurper
import io.micronaut.http.HttpStatus

class TeamGameController {

    static scaffold = TeamGame

    TeamGameService teamGameService

    def newTournment(){
        teamGameService.dropGames()
        teamGameService.createRoundRobin()
        render status: HttpStatus.OK.code, text: "All Good"
    }

    def showTournment() {
        render view:'showTournment', model:[tournmanet:teamGameService.getRounds()]
    }

    def saveRound() {
        teamGameService.saveItems(new JsonSlurper().parseText(params.items))
    }

    def nextRound() {
        def rounds =  teamGameService.getNextRound(new JsonSlurper().parseText(params.items))
        StringBuilder html = new StringBuilder()
        if(rounds instanceof Team) {
            html.append("The Winning Team is ${rounds.name}")
        } else {
            rounds.each {
                html.append(g.render template: 'bracket', model:[totalTeams:it.size(), sortedTeams:it,totalGames:Math.ceil(it.size()/2)])
            }
        }
        render text:  html.toString(), contentType: "text/html"

    }

    def resumeTournment() {
        if(!teamGameService.hasSavedTournments()) {
            forward action: 'createBacket'
        }
        def rounds =  teamGameService.resumeTournment()
        StringBuilder html = new StringBuilder()
        if(rounds instanceof Team) {
            html.append("The Winning Team is ${rounds.name}")
        } else {
            rounds.each {
                html.append(g.render template: 'bracket', model:[totalTeams:it.size(), sortedTeams:it,totalGames:Math.ceil(it.size()/2)])
            }
        }
        render view:  'showBracket', model: [bracketHtml:html.toString()]

    }

    def createBacket() {
        StringBuilder html = new StringBuilder()
        if(Team.count() % 2 == 0 && (Team.count()/2).toInteger() % 2 != 0 && Team.count() > 2 ) {
            List sortedTeams = teamGameService.teamsSort(Team.list())
            html.append(g.render template: 'bracket', model:[totalTeams:1, sortedTeams:[sortedTeams.pop()],totalGames:1])
            html.append(g.render template: 'bracket', model:[totalTeams:1, sortedTeams:[sortedTeams.pop()],totalGames:1])
            html.append(g.render template: 'bracket', model:[totalTeams:sortedTeams.size(), sortedTeams:sortedTeams,totalGames:Math.ceil(sortedTeams.size()/2)])

        } else {
            html.append(g.render template: 'bracket', model:[totalTeams:Team.count(), sortedTeams:teamGameService.createBracket(),totalGames:Math.ceil(Team.count()/2)])
        }
        render view:  'showBracket', model: [bracketHtml:html.toString()]
    }
}
