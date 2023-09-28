package com.task

import groovyx.net.http.RESTClient

class RestClient {

    @Delegate
    private RESTClient restClient

    RestClient(int port) {
        restClient = new RESTClient("http://localhost:$port")
            restClient.handler.failure = { resp, reader ->
                [data:resp, reader:reader, status: resp.status]
            }
            restClient.handler.success = { resp, reader ->
                [data:resp, reader:reader, status: resp.status]
            }
        }

}