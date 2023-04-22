package com.workvenue.backend.core.util.exception;

import com.google.gson.JsonObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        JsonObject header = new JsonObject();
        header.addProperty("success", false);
        header.addProperty("message", "Bu servise yetkiniz bulunmamaktadir.");

        JsonObject body = new JsonObject();
        body.add("header", header);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(body.toString());
    }
}
