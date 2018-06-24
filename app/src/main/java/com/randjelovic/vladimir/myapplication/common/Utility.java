package com.randjelovic.vladimir.myapplication.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utility {

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public static double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        double result;

        if(!value.isNaN() && !value.isInfinite()){
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            result = bd.doubleValue();
        }
        else{
            result=0;
        }
        return result;
    }
}
