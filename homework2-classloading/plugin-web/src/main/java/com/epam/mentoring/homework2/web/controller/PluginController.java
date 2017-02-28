package com.epam.mentoring.homework2.web.controller;

import com.epam.mentoring.homework2.web.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
@RestController
@RequestMapping("/plugins")
public class PluginController {

    @Autowired
    private PluginService pluginService;

    @RequestMapping(value = "/all/", method = RequestMethod.GET)
    public String getAllPlugins() {
        return "All plugins requested";
    }

}
