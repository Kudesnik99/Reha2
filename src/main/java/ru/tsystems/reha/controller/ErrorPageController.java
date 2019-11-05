package ru.tsystems.reha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController {
    @RequestMapping(value = "/errors", method = {RequestMethod.GET, RequestMethod.POST})
    public String renderErrorPage(HttpServletRequest httpRequest, Model theModel) {
        //int httpErrorCode = getErrorCode(httpRequest);

        //switch (httpErrorCode) {
        //    case 400:
        theModel.addAttribute("errorMessage", "Error message!");
        return "error-page";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}
