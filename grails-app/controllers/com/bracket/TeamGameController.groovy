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
        render view:'showTournment', model:[tournmanet:teamGameService.getRounds(params.order)]
    }

    def saveRound() {
        teamGameService.saveItems(new JsonSlurper().parseText(params.items))
    }
}
