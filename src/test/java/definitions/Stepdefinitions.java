package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Stepdefinitions extends Utilities {
    RequestSpecification res;
    ResponseSpecification reqresponse;
    Response response;
    TestDataBuild tBD = new TestDataBuild();
    static String place_id;


    @Given("^App Employee Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void appEmployeePayloadWith(String name, String language, String address) throws IOException {

        reqresponse = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        res = given().spec(requestSpecifications()).
                body(tBD.addPlacePayLoad(name,language, address));

        System.out.println("Given Block");
    }

    @When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void userCallSomethingWithSomethingHttpRequest(String resource, String method)  {

        APIResources resourceApi = APIResources.valueOf(resource);
        System.out.println(resourceApi.getResource());

        reqresponse = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")){
            response = res.when().post(resourceApi.getResource());
        }
        else if (method.equalsIgnoreCase("GET")){
            response = res.when().get(resourceApi.getResource());
        }

 //               then().spec(reqresponse).extract().response();
        System.out.println("B");


    }

    @Then("^Api call got \"([^\"]*)\" with \"([^\"]*)\" response$")
    public void apiCallIsSuccessWith200Response(String arg0, String arg1) {
        assertEquals(response.getStatusCode(), 200);
        System.out.println("C");
    }

    @Given("^app test$")
    public void appTest() {
        System.out.println("test");
    }


    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void inResponseBodyIs(String expectedkey, String expectedvalue) {

        assertEquals(getJsonPath(response,expectedkey), expectedvalue);

    }

    @And("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verifyPlaceIdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        // Prepare request Spec
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecifications()).queryParam("place_id",place_id);
        userCallSomethingWithSomethingHttpRequest(resource,"GET");
        String nameFromResponse =getJsonPath(response,"name");
        assertEquals(nameFromResponse, expectedName);

    }

    @Given("^DeletePlace Payload$")
    public void deleteplacePayload() throws IOException {
    res = given().spec(requestSpecifications()).body(tBD.deletePlacePayload(place_id));

    }

    @Given("Update Employee Payload \"([^\"]*)\"")
    public void updateEmployeePayload(String employee) {

    }
}
