package uk.co.envyware.alchemy.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import uk.co.envyware.alchemy.navigation.HomePage;

public class PlatformStepDefinitions {

    @When("{actor} clicks the {platform} platform")
    public void clicksThePlatformCard(Actor actor, String platform) {
        actor.attemptsTo(HomePage.selectPlatform(platform));
    }

    @Given("{actor} has selected the {platform} platform")
    public void hasSelectedThePlatform(Actor actor, String platform) {
        actor.attemptsTo(
                HomePage.open(),
                HomePage.selectPlatform(platform)
        );
    }

}
