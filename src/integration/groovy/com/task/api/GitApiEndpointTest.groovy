package com.task.api

import groovyx.net.http.HttpResponseDecorator
import com.task.BaseIntegrationTest

class GitApiEndpointTest extends BaseIntegrationTest {

    def succes_response = [
            "id": "583231",
            "login": "octocat",
            "name": "The Octocat",
            "type": "User",
            "avatarUrl": "https://avatars.githubusercontent.com/u/583231?v=4",
            "createdAt":"2011-01-25T18:44:36.000+00:00",
            "calculations": "0.00014183055975794251466794160254636381068849004805088043212890625"
    ]


    def "should get user data from git api"() {
        given:
            gitApiStub.stubGetUserData("octocat")
        when:
            def response = restClient.get([path:"/user/octocat"])
        then:
            response.status == 200
            response.reader == succes_response

    }

    def "should get user not found message"() {
        given:
            gitApiStub.stubUserDataNotFound("!")
        when:
            def response = restClient.get([path:"/user/!"])
        then:
            response.status == 404
            response.reader["message"] == "NOT_FOUND"

    }

    def "should get user validation failed message"() {
        when:
            def response = restClient.get([path:"/user/ "])
        then:
            response.status == 400
            response.reader["message"] == "VALIDATION_FAILED"

    }


}