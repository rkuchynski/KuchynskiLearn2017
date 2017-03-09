package com.epam.mentoring.homework2.impl;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.api.PluginDescription;
import com.epam.mentoring.homework2.api.PluginExecutionException;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

/**
 * Plugin that summarizes passed numbers and returns result with specified precision.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public class SummPlugin implements IPlugin {

    private static final String ID = "SummPlugin";
    private static final String RESULT = "Double representation of the summ with specified precision";
    private static final String ERROR = "When non-numeric param is found";

    private static final String PARAM_VALUES = "values";
    private static final String PARAM_PRECISION = "precision";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public PluginDescription getDescription() {
        Map<String, String> params = Maps.newHashMap();
        params.put(PARAM_VALUES, "Comma separated numbers (required)");
        params.put(PARAM_PRECISION, "Count of digits after decimal point (optional, default 2)");
        return new PluginDescription(params, RESULT, ERROR);
    }

    @Override
    public String call(Map<String, String> param) throws PluginExecutionException {
        if (MapUtils.isEmpty(param)
                || !param.containsKey(PARAM_VALUES)
                || StringUtils.isBlank(param.get(PARAM_VALUES))) {
            throw new PluginExecutionException("Parameter \"" + PARAM_VALUES + "\" is required and cannot be empty");
        }
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(getPrecision(param));
        return format.format(getSumm(param));
    }

    private double getSumm(Map<String, String> param) throws PluginExecutionException {
        double summ = 0.0;
        for(String value : param.get(PARAM_VALUES).split(",")) {
            if (StringUtils.isBlank(value)) {
                throw new PluginExecutionException("Wrong value of the parameter \"" + PARAM_VALUES + "\"");
            }
            summ += parseParam(value);
        }
        return summ;
    }

    private int getPrecision(Map<String, String> param) {
        if (param.containsKey(PARAM_PRECISION)) {
            try {
                return Integer.parseInt(param.get(PARAM_PRECISION));
            } catch (NumberFormatException nexc) {
                return 2;
            }
        }
        return 2;
    }

    private Double parseParam(String paramValue) throws PluginExecutionException {
        try {
            return Double.parseDouble(paramValue);
        } catch (NumberFormatException nexc) {
            throw new PluginExecutionException("Wrong value of the parameter \"" + PARAM_VALUES + "\"", nexc);
        }
    }
}
