package com.bracket

import groovy.transform.ToString

class Team extends GemericDomainObject{

    String name

    static hasMany = [homeGames:TeamGame,awayGames:TeamGame]
    static mappedBy = [homeGames: 'homeTeam',awayGames: 'awayTeam' ]
    static constraints = {
        name blank:false, nullable: false, unique: true
    }

    static mapping = {
        version false
    }

    @Override
    String toString() {
        this.name
    }
}
