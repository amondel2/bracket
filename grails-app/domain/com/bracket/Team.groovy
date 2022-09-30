package com.bracket

class Team extends GenericDomainObject{

    String name

    static hasMany = [homeGames:TeamGame,awayGames:TeamGame,wins:TeamGame,losses:TeamGame,tourney_wins:TournmentTracker,tourney_loses:TournmentTracker]
    static mappedBy = [homeGames: 'homeTeam',awayGames: 'awayTeam',wins: 'winner', losses: 'loser',tourney_loses:'loser',tourney_wins:'winner']
    static constraints = {
        name blank:false, nullable: false, unique: true
    }

    static mapping = {
        version false
        id generator: 'assigned'
    }

    @Override
    String toString() {
        this.name
    }
}
