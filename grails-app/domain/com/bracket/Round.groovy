package com.bracket

class Round extends GenericDomainObject {

    String name
    Integer sortOrder

    static hasMany = [rounds: TeamGame]

    static constraints = {
        name nullable: false, blank: false
    }

    static mapping = {
        version false
        id generator: 'assigned'
        sort "sortOrder"
    }

    @Override
    String toString() {
        this.name
    }
}
