package com.task.stub

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static  com.github.tomakehurst.wiremock.client.WireMock.get
import static  com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static  org.springframework.http.HttpHeaders.CONTENT_TYPE
import static  org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Component
class GitApiStub {

    @Autowired
    private WireMockServer wireMockServer

    void stubGetUserData(String username, int status = 200) {
        username = username.toUpperCase()
        wireMockServer.stubFor(get(urlEqualTo("/users/$username"))
                .willReturn(aResponse()
                    .withStatus(status)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .withBody("{\n" +
                            "  \"login\": \"octocat\",\n" +
                            "  \"id\": 583231,\n" +
                            "  \"node_id\": \"MDQ6VXNlcjU4MzIzMQ==\",\n" +
                            "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=4\",\n" +
                            "  \"gravatar_id\": \"\",\n" +
                            "  \"url\": \"https://api.github.com/users/octocat\",\n" +
                            "  \"html_url\": \"https://github.com/octocat\",\n" +
                            "  \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                            "  \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                            "  \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                            "  \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                            "  \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                            "  \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                            "  \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                            "  \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                            "  \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                            "  \"type\": \"User\",\n" +
                            "  \"site_admin\": false,\n" +
                            "  \"name\": \"The Octocat\",\n" +
                            "  \"company\": \"@github\",\n" +
                            "  \"blog\": \"https://github.blog\",\n" +
                            "  \"location\": \"San Francisco\",\n" +
                            "  \"email\": null,\n" +
                            "  \"hireable\": null,\n" +
                            "  \"bio\": null,\n" +
                            "  \"twitter_username\": null,\n" +
                            "  \"public_repos\": 8,\n" +
                            "  \"public_gists\": 8,\n" +
                            "  \"followers\": 10576,\n" +
                            "  \"following\": 9,\n" +
                            "  \"created_at\": \"2011-01-25T18:44:36Z\",\n" +
                            "  \"updated_at\": \"2023-09-22T11:25:21Z\"\n" +
                            "}")))

    }

    void stubUserDataNotFound(String username, int status = 404) {
        wireMockServer.stubFor(get(urlEqualTo("/users/$username"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody("{\n" +
                                "  \"message\": \"Not Found\",\n" +
                                "  \"documentation_url\": \"https://docs.github.com/rest/users/users#get-a-user==\",\n" +
                                "}")))

    }

}