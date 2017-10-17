package com.quanmin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesDataUtils {

	private volatile static Properties mProperties;

	static {
		mProperties = new Properties();
		InputStream in = LoadPropertiesDataUtils.class.getResourceAsStream("/config.properties");
		try {
			mProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		if (mProperties == null) {
            return "";
        }
		return mProperties.getProperty(key, "");
	}
}
