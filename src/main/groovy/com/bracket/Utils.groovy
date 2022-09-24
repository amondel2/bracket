package com.bracket
import groovy.transform.CompileStatic


@Singleton
class Utils {
    @CompileStatic
    String idGenerator(){
        UUID.randomUUID().toString().replaceAll("-", "")
    }
}