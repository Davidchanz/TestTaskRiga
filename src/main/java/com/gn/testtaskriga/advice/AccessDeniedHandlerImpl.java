package com.gn.testtaskriga.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gn.testtaskriga.dto.response.ApiErrorDto;
import com.gn.testtaskriga.dto.response.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ServletOutputStream out = response.getOutputStream();
        var om = new ObjectMapper();
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.FORBIDDEN,
                request.getRequestURI(),
                new ErrorDto("403 FORBIDDEN", "Your account has no right to access this resource!")
        );
        om.writeValue(
                out,
                errorDto
        );
        out.flush();
    }
}
