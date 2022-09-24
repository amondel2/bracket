package com.bracket

class TeamGame extends GemericDomainObject{

    Team homeTeam
    Team awayTeam
    Integer homeScore
    Integer awayScore

    static mapping = {
        version false
    }

    static belongsTo = [homeTeam:Team, awayTeam: Team]

    static constraints = {
        homeTeam nullable: false
        awayTeam nullable: false
    }
}
