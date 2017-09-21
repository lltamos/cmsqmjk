package com.quanmin.test;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Log4JTest {
    static Logger logger=Logger.getLogger(
            Log4JTest.class.getName());

    public static void main(String[] args)
            throws IOException, SQLException {

        logger.debug("This is a debug message!");
        logger.info("This is info message!");
        logger.warn("This is a warn message!");
        logger.error("This is error message!");
        try{
            System.out.println(5/0);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
        }
    }
}
