package com.bracket

import io.micronaut.http.HttpStatus

class TeamGameController {

    static scaffold = TeamGame

    TeamGameService teamGameService

    def newTournment(){
        teamGameService.dropGames()
        teamGameService.createRoundRobin()
        render status: HttpStatus.OK.code, text: "All Good"
    }
}
