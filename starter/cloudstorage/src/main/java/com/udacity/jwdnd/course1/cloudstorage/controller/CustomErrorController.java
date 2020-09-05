package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.data.Message;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String errorMsg = "";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());



            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg = "Http Error Code: 404. Resource not found";

            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMsg = "Http Error Code: 500. Internal Server Error";
            } else if (statusCode == HttpStatus.NON_AUTHORITATIVE_INFORMATION.value()) {
                errorMsg = "Http Error Code: 401. Unauthorized";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                errorMsg = "Http Error Code: 400. Bad Request";
            }
        }

        Message message = new Message(true, false, errorMsg);
        model.addAttribute("message", message.getMsg());
        model.addAttribute("message", message.getMsg());
        model.addAttribute("error", message.isError());


        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
