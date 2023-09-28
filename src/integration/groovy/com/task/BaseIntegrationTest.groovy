package com.task

import com.task.RestClient
import com.task.stub.GitApiStub
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest(classes = [Application], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = WireMockInitializer)
class BaseIntegrationTest  extends Specification {

    static {
        System.setProperty("spring.config.location", "classpath:application-integration.yml")
    }


    @Value('${local.server.port}')
    protected int port

    @Autowired
    GitApiStub gitApiStub

    RestClient restClient

    void setup() {
        restClient = new RestClient(port)
    }

}