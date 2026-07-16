package uk.co.envyware.alchemy.pageobjects.page;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import uk.co.envyware.alchemy.api.Alchemy;
import uk.co.envyware.alchemy.screenplay.navigation.HomePage;

@DefaultUrl("https://alchemy.envyware.co.uk/")
public class AlchemyHomePage extends PageObject {

    private static final String PLATFORM_GRID = "[data-testid='platforms-grid']";
    private static final String PLATFORM_CARD = "[data-testid='platform-card']";
    private static final String EXAM_GRID = "[data-testid='exams-grid']";
    private static final String EXAM_CARD = "[data-testid='exam-card']";
    private static final String CATEGORY_DROP_DOWN = "[data-testid='focused-learning-category-select']";
    private static final String LAUNCH_CATEGORY_MODE = "[data-testid='study-category-button']";

    public void selectPlatform(String platform) {
        this.find("[data-testid='platform-card'][data-platform-id='" + platform + "']")
                .click();
        this.waitFor(EXAM_GRID);
    }

    public void selectCategory(String category) {
        var dropdown = $(CATEGORY_DROP_DOWN);

        dropdown.selectByValue(category);
    }

    public void launchCategoryMode() {
        $(LAUNCH_CATEGORY_MODE).click();
    }

    public void startLearningModeFor(String platform, String exam) {
        this.startQuizInMode(platform, exam, Alchemy.LEARNING_MODE_LAUNCH);
    }

    public void startMockExamFor(String platform, String exam) {
        this.startQuizInMode(platform, exam, Alchemy.MOCK_EXAM_LAUNCH);
    }

    public void startQuizInMode(String platform, String exam, String launchMode) {
        var examCard = this.getExamCard(platform, exam);
        var launchButton = examCard.findElement(By.cssSelector("[data-testid='" + launchMode + "']"));

        launchButton.click();
    }

    public boolean isDisplayed() {
        this.waitFor(PLATFORM_GRID);
        return $(PLATFORM_GRID).isVisible() &&
                this.hasVisiblePlatformCards();
    }

    public boolean hasVisiblePlatformCards() {
        return this.findAll(PLATFORM_CARD)
                .stream()
                .anyMatch(WebElementState::isVisible);
    }

    public boolean hasVisibleExamCards() {
        return $(EXAM_GRID).isVisible() &&
                this.findAll(EXAM_CARD)
                        .stream()
                        .anyMatch(WebElementState::isVisible);
    }

    public boolean isExamCardVisible(String platform, String exam) {
        return this.getExamCard(platform, exam).isVisible();
    }

    public WebElementFacade getExamCard(String platform, String exam) {
        return this.find("[data-platform-id='" + platform + "'][data-exam-id='" + exam + "'][data-testid='exam-card']");
    }

    public void waitUntilDisplayed() {
        this.waitFor(PLATFORM_GRID);
    }

    public void shouldBeDisplayed() {
        waitUntilDisplayed();

        if (!this.isDisplayed()) {
            throw new AssertionError("Alchemy home page should be displayed");
        }
    }
}
