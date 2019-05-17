package com.eater.test.client

import io.restassured.response.ValidatableResponse

import static io.restassured.RestAssured.given
import static org.hamcrest.core.StringEndsWith.endsWith

class User {


    boolean registered
    String login
    String userIdCookieValue
    String redirectLocation

    def 'user opens'(String path) {
        def request = given().port(5050).redirects().follow(false)
        def response = request.get(path)
        response.statusCode == 307
        userIdCookieValue = response.cookie("EATER-ID-COOKIE")
        redirectLocation = response.header("location")
    }
}
