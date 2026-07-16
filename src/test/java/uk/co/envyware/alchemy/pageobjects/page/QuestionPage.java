package uk.co.envyware.alchemy.pageobjects.page;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

@DefaultUrl("https://alchemy.envyware.co.uk/")
public class QuestionPage extends PageObject {

    private static final String QUIZ_EXAM_TITLE = "[data-testid='quiz-exam-title']";
    private static final String SUBMIT_BUTTON = "[data-testid='submit-answer-button']";
    private static final String QUIZ_TIMER = "[data-testid='quiz-timer']";
    private static final String LEARNING_MODE_FEEDBACK = "[data-testid='learning-feedback']";
    private static final String LEARNING_MODE_FEEDBACK_EXPLANATION = "[data-testid='learning-feedback-explanation']";
    private static final String NEXT_BUTTON = "[data-testid='next-question-button']";
    private static final String PREVIOUS_BUTTON = "[data-testid='previous-question-button']";
    private static final String QUESTION_ID = "[data-testid='quiz-question-counter']";
    private static final String CATEGORY = "[data-testid='quiz-question-category']";

    private static final Pattern QUESTION_ID_PATTERN = Pattern.compile("Question (\\d+) of (\\d+)");
    private static final Pattern QUIZ_TIMER_PATTERN = Pattern.compile("(Timer|Time spent): (\\d{1,2}:)?(\\d{1,2}):(\\d{1,2})");

    public boolean isLearningModeScreenDisplayed() {
        return $(QUIZ_EXAM_TITLE).waitUntilVisible().containsText("LEARNING MODE");
    }

    public String getExamTitle() {
        return $(QUIZ_EXAM_TITLE).waitUntilVisible().getText();
    }

    public boolean isSubmitButtonDisplayed() {
        return this.$(SUBMIT_BUTTON).isVisible();
    }

    public int getQuestionNumber() {
        var questionIdText = $(QUESTION_ID).getText();
        var matcher = QUESTION_ID_PATTERN.matcher(questionIdText);

        if (!matcher.matches()) {
            throw new IllegalStateException("Question ID text does not match expected format: " + questionIdText);
        }

        return Integer.parseInt(matcher.group(1));
    }

    public int getQuizTimer() {
        var timerText = $(QUIZ_TIMER).getText();
        var matcher = QUIZ_TIMER_PATTERN.matcher(timerText);

        if (!matcher.matches()) {
            throw new IllegalStateException("Quiz timer text does not match expected format: " + timerText);
        }

        int hours = matcher.group(2) != null ? Integer.parseInt(matcher.group(2).replace(":", "")) : 0;
        int minutes = Integer.parseInt(matcher.group(3));
        int seconds = Integer.parseInt(matcher.group(4));

        return hours * (60 * 60) + minutes * 60 + seconds;
    }

    public void selectAnswer(String answer) {
        this.$("[data-testid='answer-option'][data-option-label='" + answer + "']").click();
    }

    public void submitAnswer() {
        this.$(SUBMIT_BUTTON).click();
    }

    public boolean isFeedbackVisible() {
        return $(LEARNING_MODE_FEEDBACK).isVisible();
    }

    public boolean isFeedbackExplanationVisible() {
        return $(LEARNING_MODE_FEEDBACK_EXPLANATION).waitUntilVisible().isVisible();
    }

    public WebElementFacade getNextButton() {
        return $(NEXT_BUTTON).waitUntilVisible();
    }

    public WebElementFacade getPreviousButton() {
        return $(PREVIOUS_BUTTON).waitUntilVisible();
    }

    public WebElementFacade getCategoryElement() {
        return $(CATEGORY).waitUntilVisible();
    }
}
