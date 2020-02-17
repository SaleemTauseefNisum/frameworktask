package resources;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class Utilities {


    JsonPath jPath;

    public static String getGlobalValues(String key) throws IOException {
        Properties propOjb = new Properties();
        String dirPath = System.getProperty("user.dir");
        FileInputStream fIS = new FileInputStream(dirPath + "\\src\\test\\resources\\global.properties");
        propOjb.load(fIS);
        return propOjb.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String res = response.asString();
        jPath = new JsonPath(res);
        return jPath.get(key).toString();

    }
}
