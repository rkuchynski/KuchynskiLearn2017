package com.epam.mentoring.homework2.web.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
@Controller
@RequestMapping("/locations")
public class LocationController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> getAllLocations() {
        return Lists.newArrayList();
    }

}
