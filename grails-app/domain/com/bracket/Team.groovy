package com.bracket

class Team extends GenericDomainObject{

    String name

    static hasMany = [homeGames:TeamGame,awayGames:TeamGame,wins:TeamGame,losses:TeamGame]
    static mappedBy = [homeGames: 'homeTeam',awayGames: 'awayTeam',wins: 'winner', losses: 'loser']
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
