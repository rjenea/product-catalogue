package com.sky.util;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

@Controller
@ControllerAdvice
final class CommonExceptionHandling implements ErrorController {

    @RequestMapping(value = "/error")
    public void error(HttpServletResponse response) throws IOException {
        checkNotNull(response,"Response is required");
        response.sendRedirect(getErrorPath());
    }

    @Override
    public String getErrorPath() {
        return "/err.html";
    }
}
