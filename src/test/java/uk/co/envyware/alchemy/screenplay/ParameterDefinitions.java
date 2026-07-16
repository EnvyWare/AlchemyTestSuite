package uk.co.envyware.alchemy.screenplay;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import uk.co.envyware.alchemy.api.Alchemy;

public class ParameterDefinitions {

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
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
    public String category(String categoryName) {
        return categoryName;
    }

    @ParameterType("A|B|C|D")
    public String answer(String answerName) {
        return answerName;
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
