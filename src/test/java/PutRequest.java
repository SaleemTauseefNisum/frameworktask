import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequest {
    @Test
    public void updateRecord() {
        //BaseURL
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        given().
                body("{\"name\":\"Saleem\",\"salary\":\"1123\",\"age\":\"23\"}").
                when().
                put("/api/v1/update/76").
                then().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status", equalTo("success")).and();

        //JsonPath jPath = new JsonPath(res.asString());
        //String status =jPath.get("status");


        System.out.println("Updated");


    }
}
