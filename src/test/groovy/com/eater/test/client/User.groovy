package com.eater.test.client

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static io.restassured.RestAssured.given
import static org.hamcrest.core.StringEndsWith.endsWith

class User {

    final static Logger LOGGER = LoggerFactory.getLogger(User.class)

    boolean registered
    String login
    String userIdCookieValue
    String redirectLocation

    def request = given().port(5050)
    int statusCode = -1

    def 'user opens'(String path) {
        request.redirects().follow(false)
        def response = request.get(path)
        response.statusCode == 307
        userIdCookieValue = response.cookie("EATER-ID-COOKIE")
        redirectLocation = response.header("location")
    }

    def 'user registers with name'(String userName) {
        def response = request.urlEncodingEnabled(true)
                .param("userName", userName)
                .post("/registration")
        statusCode = response.statusCode()

        userIdCookieValue = response.cookie("EATER-ID-COOKIE")

        LOGGER.info("userIdCookieValue: {}", userIdCookieValue)
    }
}
