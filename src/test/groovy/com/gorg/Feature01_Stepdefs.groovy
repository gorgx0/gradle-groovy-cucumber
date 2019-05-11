package com.gorg

import cucumber.api.PendingException
// Add functions to register hooks and steps to this script.
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


When(/something/) { ->
    println "something"
}

Then(/result/) {  ->
    println "result"
}

