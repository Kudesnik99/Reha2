package ru.tsystems.reha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Form corresponding error message depend on error code.
 * Processes "/errors" URL
 * @author Laristov Alexey
 */

@Controller
public class ErrorPageController {
    /**
     * Prepared error message and insert it into model attribute
     * @param httpRequest
     * @param model
     * @return error-page
     */
    @RequestMapping(value = "/errors", method = {RequestMethod.GET, RequestMethod.POST})
    public String renderErrorPage(HttpServletRequest httpRequest, Model theModel) {
        //int httpErrorCode = getErrorCode(httpRequest);

        //switch (httpErrorCode) {
        //    case 400:
        theModel.addAttribute("errorMessage", "Error message!");
        return "error-page";
    }

    /**
     * Get error codes from request
     * @param httpRequest
     * @return Integer error code
     */
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}
