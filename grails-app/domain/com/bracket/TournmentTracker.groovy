package com.bracket

class TournmentTracker extends GenericDomainObject {

    Team winner
    Team loser
    Integer winerScore
    Integer loserScore

    static mapping = {
        version false
        id generator: 'assigned'
    }

    static constraints = {
        winner nullable: false
        loser nullable: true
        winerScore nullable: false
        loserScore nullable: false
    }
}
