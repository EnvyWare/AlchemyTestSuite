package uk.co.envyware.alchemy.rest;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import uk.co.envyware.alchemy.api.Alchemy;

public class ParameterDefinitions {

    @ParameterType("[0-9]+")
    public int code(String code) {
        return Integer.parseInt(code);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
