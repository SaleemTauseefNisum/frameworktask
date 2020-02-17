package definitions;

import cucumber.api.java.After;

public class Hooks {
    @After()
    public void afterScenario(){
        Stepdefinitions sF = new Stepdefinitions();
        sF.driver.quit();

    }

}
