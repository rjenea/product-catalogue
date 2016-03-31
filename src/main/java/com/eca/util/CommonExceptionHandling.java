package com.eca.util;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@Controller
@ControllerAdvice
final class CommonExceptionHandling implements ErrorController {

    @RequestMapping(value = "/error")
    public void error(HttpServletResponse response) throws IOException {
        checkNotNull(response, "Response is required");
        response.sendError(SC_BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return "/err.html";
    }
}
