package uk.co.envyware.alchemy.pageobjects.action;

import net.serenitybdd.annotations.Step;
import uk.co.envyware.alchemy.pageobjects.page.AlchemyHomePage;
import uk.co.envyware.alchemy.pageobjects.page.QuestionPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LearningModeActions {

    private AlchemyHomePage homePage;
    private QuestionPage questionPage;

    @Step("Start learning mode for {0} {1}")
    public void startLearningModeFor(String platform, String exam) {
        this.homePage.selectPlatform(platform);
        this.homePage.startLearningModeFor(platform, exam);
    }

    @Step("Check that the learning mode screen is displayed")
    public void shouldSeeLearningModeScreen() {
        assertThat(this.questionPage.getExamTitle()).contains("LEARNING MODE");
    }

    @Step("Check that the learning mode screen is displayed")
    public void shouldNotSeeLearningModeScreen() {
        assertThat(this.questionPage.getExamTitle()).doesNotContain("LEARNING MODE");
    }

    @Step("Check that learning mode screen submit button is visible")
    public void shouldSeeSubmitButton() {
        assertThat(this.questionPage.isSubmitButtonDisplayed()).isTrue();
    }

}
