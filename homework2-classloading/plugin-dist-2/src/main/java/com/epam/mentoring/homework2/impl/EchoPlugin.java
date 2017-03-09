package com.epam.mentoring.homework2.impl;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.api.PluginDescription;
import com.epam.mentoring.homework2.api.PluginExecutionException;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Echo plugin, returns object with passed params and their values.
 * <p/>
 * Date: 08.03.2017
 *
 * @author Raman Kuchynski
 */
public class EchoPlugin implements IPlugin {

    private static final String ID = "EchoPlugin";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public PluginDescription getDescription() {
        return new PluginDescription(Maps.newHashMap(), "List of passed params", "");
    }

    @Override
    public String call(Map<String, String> param) throws PluginExecutionException {
        StringBuilder echo = new StringBuilder("{");
        for(String key : param.keySet()) {
            echo.append("\"").append(key).append("\":\"").append(param.get(key)).append("\", ");
        }
        echo.setLength(echo.length() - 2);
        echo.append("}");
        return echo.toString();
    }
}
