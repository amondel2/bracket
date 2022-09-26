package com.bracket

class GenericDomainObject implements Serializable {

    String id

    private static final serialVersionUID = 1L

    def beforeValidate() {
        if(!id || id.equals(null)) {
            id  = Utils.getInstance().idGenerator()
        }
    }

    def beforeInsert() {
        if(!id || id.equals(null)) {
            id  = Utils.getInstance().idGenerator()
        }
    }
}