package com.epam.mentoring.homework2.impl;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.api.PluginDescription;
import com.epam.mentoring.homework2.api.PluginExecutionException;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 *
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public class RegexReplacePlugin implements IPlugin {

    private static final String ID = "com.epam.mentoring.plugins.RegexReplacePlugin";
    private static final String RESULT = "Text after regexp replacement";
    private static final String ERROR = "If any operation fails";

    private static final String PARAM_TEXT = "text";
    private static final String PARAM_REGEX = "regex";
    private static final String PARAM_REPLACE = "replace";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public PluginDescription getDescription() {
        Map<String, String> params = Maps.newHashMap();
        params.put(PARAM_TEXT, "Text to change (required)");
        params.put(PARAM_REGEX, "Regexp to use (required)");
        params.put(PARAM_REPLACE, "Char sequence to replace by (optional, empty string is default)");
        return new PluginDescription(params, RESULT, ERROR);
    }

    @Override
    public String call(Map<String, String> param) throws PluginExecutionException {
        validateParams(param);
        String replace = (param.containsKey(PARAM_REPLACE)) ? param.get(PARAM_REPLACE) : "";
        try {
            return param.get(PARAM_TEXT).replaceAll(param.get(PARAM_REGEX), replace);
        } catch (PatternSyntaxException pse) {
            throw new PluginExecutionException("Wrong regex value", pse);
        }
    }

    private void validateParams(Map<String, String> param) throws PluginExecutionException {
        if (MapUtils.isEmpty(param)) {
            throw new PluginExecutionException(
                    "Parameters \"" + PARAM_REGEX + "\", \"" + PARAM_REPLACE + "\" are required and cannot be empty"
            );
        }
        if (!param.containsKey(PARAM_TEXT) || StringUtils.isBlank(param.get(PARAM_TEXT))) {
            throw new PluginExecutionException("Parameter \"" + PARAM_TEXT + "\" is required and cannot be empty");
        }
        if (!param.containsKey(PARAM_REGEX) || StringUtils.isBlank(param.get(PARAM_REGEX))) {
            throw new PluginExecutionException("Parameter \"" + PARAM_REGEX + "\" is required and cannot be empty");
        }
    }
}
