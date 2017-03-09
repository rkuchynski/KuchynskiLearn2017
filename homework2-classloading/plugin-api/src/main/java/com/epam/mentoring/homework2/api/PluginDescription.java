package com.epam.mentoring.homework2.api;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Plugin description object. Stores information about how to call plugin and its result and errors.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public class PluginDescription {

    private final Map<String, String> params;
    private final String result;
    private final String errors;

    public PluginDescription(Map<String, String> params, String result, String errors) {
        this.params = Collections.unmodifiableMap(Maps.newHashMap(params));
        this.result = result;
        this.errors = errors;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getResult() {
        return result;
    }

    public String getErrors() {
        return errors;
    }
}
