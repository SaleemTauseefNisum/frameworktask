import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;


public class AddAndDeleteEmployee {
    public Integer id;

    @Before
    public void beforeTest(){
        Properties propNew = new Properties();
        propNew.get("HOST");
    }
    @Test
    public void postRecord() {
        //BaseURL
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        Response res = given().
                body("{\"name\":\"test\",\n" +
                        "\"salary\":\"123\",\n" +
                        "\"age\":\"23\"}").
                when().
                post("/api/v1/create").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                //Extract the result initially in raw format
                        extract().response();

        //Convert it into string to pull out required field from response
        JsonPath js = new JsonPath(res.asString());
        id = js.get("data.id");
        System.out.println("Extracted response is : " + res.asString());
        System.out.println("Generated id is : " + id);

        //Delete Employee by providing ID
        given().
                when().
                delete("api/v1/delete/" + id + "").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON);
        System.out.println("Deleted");

    }
}
