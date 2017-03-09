package com.epam.mentoring.homework2.web.controller;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.api.PluginDescription;
import com.epam.mentoring.homework2.api.PluginExecutionException;
import com.epam.mentoring.homework2.web.service.PluginService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Rest controller to call plugins.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
@RestController
@RequestMapping("/plugins")
public class PluginController {

    private static final PluginDescription NULL_DESCRIPTION =
            new PluginDescription(Maps.newHashMap(), "Plugin does not exist", "");
    private static final String NULL_RESULT = "Plugin that you try to call does not exist";

    @Autowired
    private PluginService pluginService;

    @RequestMapping(value = {"/all/","/all"}, method = RequestMethod.GET)
    public Set<String> getAllPlugins() {
        return pluginService.getPluginIds();
    }

    @RequestMapping(value = "/{pluginId}/help", method = RequestMethod.GET)
    public PluginDescription getPluginDescription(@PathVariable("pluginId") String pluginId) {
        return doGetPluginDescription(pluginService.getPluginById(pluginId));
    }

    @RequestMapping(value = "/{pluginId}/call", method = RequestMethod.GET)
    public String callPlugin(@PathVariable("pluginId") String pluginId,
                             @RequestParam Map<String,String> requestParams) {
        return doCallPlugin(pluginService.getPluginById(pluginId), requestParams);
    }

    @RequestMapping(value = "/className/{pluginClass}/help", method = RequestMethod.GET)
    public PluginDescription getPluginDescriptionByClassName(@PathVariable("pluginClass") String pluginClass) {
        return doGetPluginDescription(pluginService.getPluginByClassName(pluginClass));
    }

    @RequestMapping(value = "/className/{pluginClass}/call", method = RequestMethod.GET)
    public String callPluginByClassName(@PathVariable("pluginClass") String pluginClass,
                                        @RequestParam Map<String,String> requestParams) {
        return doCallPlugin(pluginService.getPluginByClassName(pluginClass),requestParams);
    }

    private PluginDescription doGetPluginDescription(IPlugin plugin) {
        if (null == plugin) {
            return NULL_DESCRIPTION;
        }
        return plugin.getDescription();
    }

    private String doCallPlugin(IPlugin plugin, Map<String, String> requestParams) {
        if (null == plugin) {
            return NULL_RESULT;
        }
        try {
            return plugin.call(requestParams);
        } catch (PluginExecutionException e) {
            return e.getMessage();
        }
    }
}
