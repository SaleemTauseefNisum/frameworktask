package definitions;

import cucumber.api.java.Before;


import java.io.IOException;


public class Hooks {
@Before("@DeletePlace")
public void beforeScenario() throws IOException
{		//execute this code only when place id is null
    //write a code that will give you place id

    Stepdefinitions m =new Stepdefinitions();
    if(Stepdefinitions.place_id==null)
    {

        m.appEmployeePayloadWith("user1", "French", "Asia");
        m.userCallSomethingWithSomethingHttpRequest("addPlaceAPI", "POST");
        m.verifyPlaceIdCreatedMapsToUsing("user1", "getPlaceAPI");
    }



}


}

