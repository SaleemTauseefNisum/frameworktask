package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities {

    static RequestSpecification req;
    JsonPath jPath;

    public RequestSpecification requestSpecifications() throws IOException {
        if (req == null){
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123").
                addFilter(RequestLoggingFilter.logRequestTo(log)).
                addFilter(ResponseLoggingFilter.logResponseTo(log)).
                setContentType(ContentType.JSON).build();
        return req;
        }
        return req;
    }

    public static String getGlobalValues(String key) throws IOException {
        Properties propOjb = new Properties();
        String dir =System.getProperty("user.dir");
        FileInputStream fIS = new FileInputStream(dir + "\\src\\test\\resources\\global.properties");
        propOjb.load(fIS);
        return propOjb.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String res = response.asString();
        jPath = new JsonPath(res);
        return jPath.get(key).toString();

    }
}
