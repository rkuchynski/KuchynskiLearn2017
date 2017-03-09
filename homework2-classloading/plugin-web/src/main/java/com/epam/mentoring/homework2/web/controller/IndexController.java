package com.epam.mentoring.homework2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to get index page with instruction how to use the app.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

}
