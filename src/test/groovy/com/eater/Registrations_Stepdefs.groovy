package com.eater

import com.eater.test.client.User

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

User user

Given(/unregistered user/) {  ->
    user.registered = false
    user.login = null
    user.userIdCookieValue = null
}

When(/user opens any page/) {  ->
    user.invokeMethod('user opens','/anypage')
}


Then(/user is redirected to login\\/register page/) {  ->
    user.redirectLocation == 'registration'
}

When(/user registers/) {  ->
    user.invokeMethod('user opens','/registration')

}

Then(/user is registered user/) {  ->
    user.userIdCookieValue != null
}

World {
    user = new User()
}
