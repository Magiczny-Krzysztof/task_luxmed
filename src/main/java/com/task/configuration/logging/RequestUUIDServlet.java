package com.task.configuration.logging;

import lombok.RequiredArgsConstructor;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.task.configuration.audit.AuditConstants.UUID_MDC;


@Component
@RequiredArgsConstructor
public class RequestUUIDServlet extends DispatcherServlet {


    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MDC.put(UUID_MDC, UUID.randomUUID().toString());
            super.doDispatch(request, response);
        } finally {
            MDC.remove(UUID_MDC);
        }
    }


}
