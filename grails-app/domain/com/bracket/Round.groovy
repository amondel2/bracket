package com.bracket

class Round extends GenericDomainObject {

    String name

    static hasMany = [rounds: TeamGame]

    static constraints = {
        name nullable: false, blank: false
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
