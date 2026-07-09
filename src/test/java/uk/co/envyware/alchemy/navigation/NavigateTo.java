package uk.co.envyware.alchemy.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

    public static Performable navigateToHomePage() {
        return Task.where("{0} opens the Alchemy home page",
                Open.browserOn().the(AlchemyHomePage.class));
    }

}
