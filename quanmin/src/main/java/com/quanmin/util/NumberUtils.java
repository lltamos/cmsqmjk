package com.quanmin.util;

import java.text.DecimalFormat;

public class NumberUtils {
    public static String parseDouble(double totalPrice) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println();
        return df.format(totalPrice);

    }
}
