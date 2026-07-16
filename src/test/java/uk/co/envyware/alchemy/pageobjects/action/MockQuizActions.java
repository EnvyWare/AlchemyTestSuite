package uk.co.envyware.alchemy.pageobjects.action;

import net.serenitybdd.annotations.Step;
import uk.co.envyware.alchemy.pageobjects.page.AlchemyHomePage;
import uk.co.envyware.alchemy.pageobjects.page.QuestionPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MockQuizActions {

    private AlchemyHomePage homePage;
    private QuestionPage questionPage;

    @Step("Start learning mode quiz for {0} {1}")
    public void startLearningModeFor(String platform, String exam) {
        this.homePage.selectPlatform(platform);
        this.homePage.startLearningModeFor(platform, exam);
    }

    @Step("Start mock quiz for {0} {1}")
    public void startMockQuizFor(String platform, String exam) {
        this.homePage.selectPlatform(platform);
        this.homePage.startMockExamFor(platform, exam);
    }

    @Step("Select {0} category")
    public void selectCategory(String category) {
        this.homePage.selectCategory(category);
    }

    @Step("Start learning mode quiz with selected category")
    public void startLearningModeForCategory() {
        this.homePage.launchCategoryMode();
    }

}
