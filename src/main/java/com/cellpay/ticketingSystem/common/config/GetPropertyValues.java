package com.cellpay.ticketingSystem.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

    InputStream inputStream;

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try {
            String file = "dbinit.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(file);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) inputStream.close();
        }
        return properties;
    }
}
