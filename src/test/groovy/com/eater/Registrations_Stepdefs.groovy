package com.eater

import com.eater.test.client.User
import org.hamcrest.core.IsNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.notNullValue

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

final Logger LOGGER = LoggerFactory.getLogger("Registraion_Sterdefs")

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

When(/user registers with name {string}/) { String userName ->
    user.invokeMethod('user registers with name', userName)
}

When(/user registers/) {  ->
    user.invokeMethod('user opens','/registration')

}

Then(/user is registered user/) {  ->
    assertThat("Registraion response code", user.statusCode, equalTo(200))
    assertThat("User id cookie not present or value is empty",user.userIdCookieValue, notNullValue())
}

World {
    user = new User()
}
