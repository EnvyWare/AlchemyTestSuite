package uk.co.envyware.alchemy.pageobjects;

import io.cucumber.java.ParameterType;
import uk.co.envyware.alchemy.api.Alchemy;

public class ParameterDefinitions {

    @ParameterType(".*")
    public String actor(String actorName) {
        return actorName;
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
}
