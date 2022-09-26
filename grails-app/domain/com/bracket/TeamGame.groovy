package com.bracket

class TeamGame extends GenericDomainObject{

    Integer homeScore
    Integer awayScore
    Team homeTeam
    Team awayTeam
    Round round
    Team winner
    Team loser


    static mapping = {
        version false
        id generator: 'assigned'
    }

    static belongsTo = [homeTeam:Team, awayTeam: Team, round: Round, winner: Team, loser:Team]

    static constraints = {
        id nullable: false, display: true
        homeScore nullable: true
        awayScore nullable: true
        homeTeam nullable: false
        awayTeam nullable: false
        round nullable: true
        winner nullable: true
        loser nullable: true
    }

    @Override
    String toString() {
        "In Round ${round}: ${homeTeam} verus ${awayTeam} ${winner ? "The winner is ${winner} and the loser is ${loser}" : '' }"
    }
}
