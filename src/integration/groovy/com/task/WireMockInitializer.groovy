package com.task

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.Slf4jNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.env.MutablePropertySources
import org.springframework.core.env.PropertySource
import org.springframework.mock.env.MockPropertySource

@Order(Ordered.HIGHEST_PRECEDENCE)
class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final WIREMOCK_BEAN_NAME = "wireMockServer"
    public static final WIREMOCK_PROPERTIES_NAME = "wiremockProperties"
    public static final WIREMOCK_HTTP_CONFIG_PATH = "wiremock.http"

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        WireMockServer wireMockServer = wireMock(wireMockConfig())

        applicationContext.getBeanFactory().registerSingleton(WIREMOCK_BEAN_NAME, wireMockServer)

        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources()
        propertySources.addFirst(wireMockPropertySource(wireMockServer))
    }

    private static PropertySource<?> wireMockPropertySource(WireMockServer wireMockServer) {
        MockPropertySource source = new MockPropertySource(WIREMOCK_PROPERTIES_NAME)
        configure(source, WIREMOCK_HTTP_CONFIG_PATH, wireMockServer.port(), httpUrl(wireMockServer))
        return source
    }

    private static MockPropertySource configure(MockPropertySource source, String prefix, int port, String url) {
        return source
                .withProperty(prefix + ".port", port)
                .withProperty(prefix + ".url", url)
    }

    private static httpUrl(WireMockServer wireMockServer) {
        return wireMockServer.baseUrl()
    }

    private static WireMockServer wireMock(WireMockConfiguration config) {
        WireMockServer wireMockServer = new WireMockServer(config)
        wireMockServer.start()
        return wireMockServer
    }

    private static WireMockConfiguration wireMockConfig() {
        return WireMockConfiguration
                .wireMockConfig()
                .notifier(new Slf4jNotifier(true))
                .extensions(new ResponseTemplateTransformer(false))
                .dynamicPort()
    }



}
