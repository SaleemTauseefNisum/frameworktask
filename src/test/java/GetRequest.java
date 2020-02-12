import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest {

    public static void main(String[] args) {
        //TODO Auto-generated method

        //BaseURL
        RestAssured.baseURI = "http://dummy.restapiexample.com";

        given().
                param("page", "2").
                        when().
                get("api/v1/employees").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                body("data[0].employee_name", equalTo("Tiger Nixon")).
                extract().response();


        System.out.println("Successful");
    }
}
