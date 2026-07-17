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

    @ParameterType("[0-9]+")
    public int size(String size) {
        return Integer.parseInt(size);
    }

    @ParameterType(".*")
    public String platform(String platformName) {
        return Alchemy.getPlatformId(platformName);
    }

    @ParameterType(".*")
    public String exam(String examName) {
        return Alchemy.getExamId(examName);
    }

    @ParameterType(".*")
    public String category(String category) {
        return category;
    }

    @ParameterType("[^\"]+")
    public String error(String error) {
        return error;
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
