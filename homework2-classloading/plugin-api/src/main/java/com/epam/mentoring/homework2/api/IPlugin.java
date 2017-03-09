package com.epam.mentoring.homework2.api;

import java.util.Map;

/**
 * Plugin interface.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public interface IPlugin {

    String getId();

    PluginDescription getDescription();

    String call(Map<String, String> param) throws PluginExecutionException;

}
