package uk.co.envyware.alchemy.pageobjects.action;

import net.serenitybdd.annotations.Step;
import uk.co.envyware.alchemy.pageobjects.page.AlchemyHomePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageActions {

    private AlchemyHomePage homePage;

    @Step("Open the Alchemy home page")
    public void openHomePage() {
        this.homePage.open();
    }

    @Step("Check that the home page screen is displayed")
    public void shouldSeeHomePageScreen() {
        assertThat(this.homePage.isDisplayed()).isTrue();
    }

    @Step("Select {0} platform")
    public void selectPlatform(String platform) {
        this.homePage.selectPlatform(platform);
    }

    @Step("Check that exams are displayed")
    public void shouldSeeAvailableExams() {
        assertThat(this.homePage.hasVisibleExamCards()).isTrue();
    }

    @Step("Check that exams are not displayed")
    public void shouldNotSeeAvailableExams() {
        assertThat(this.homePage.hasVisibleExamCards()).isFalse();
    }

    @Step("Check that {0} {1} exam card is visible")
    public void shouldSeePlatformExamCard(String platform, String exam) {
        assertThat(this.homePage.isExamCardVisible(platform, exam)).isTrue();
    }
}
