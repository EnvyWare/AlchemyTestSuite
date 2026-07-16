package uk.co.envyware.alchemy.pageobjects.action;

import net.serenitybdd.annotations.Step;
import uk.co.envyware.alchemy.api.WaitForValue;
import uk.co.envyware.alchemy.pageobjects.page.QuestionPage;

public class QuestionPageActions {

    private QuestionPage questionPage;

    @Step("Checks that the timer element is increasing")
    public boolean isTimerCountingUp() {
        var timer = this.questionPage.getQuizTimer();
        WaitForValue.builder(timer, () -> this.questionPage.getQuizTimer())
                .withDriver(this.questionPage.getDriver())
                .perform();

        var updatedTimer = this.questionPage.getQuizTimer();
        return timer < updatedTimer;
    }

    @Step("Selects the answer element {0}")
    public void selectAnswer(String answer) {
        this.questionPage.selectAnswer(answer);
    }

    @Step("Submits the selected answer")
    public void submitAnswer() {
        this.questionPage.submitAnswer();
    }

    @Step("Checks that the learning mode feedback is visible")
    public boolean shouldSeeLearningModeFeedback() {
        return this.questionPage.isFeedbackVisible();
    }

    @Step("Checks that the feedback explanation is visible")
    public boolean shouldSeeLearningModeExplanation() {
        return this.questionPage.isFeedbackExplanationVisible();
    }

    @Step("Checks the page moves to the next question")
    public boolean shouldMoveToNextQuestion() {
        var questionNumber = this.getCurrentQuestionNumber();
        this.moveToNextQuestion();
        WaitForValue.builder(questionNumber, this::getCurrentQuestionNumber)
                .withDriver(this.questionPage.getDriver())
                .perform();
        var updatedQuestionNumber = this.getCurrentQuestionNumber();
        return updatedQuestionNumber > questionNumber;
    }

    @Step("Checks the page moves to the previous question")
    public boolean shouldMoveToPreviousQuestion() {
        var questionNumber = this.getCurrentQuestionNumber();
        this.moveToPreviousQuestion();
        WaitForValue.builder(questionNumber, this::getCurrentQuestionNumber)
                .withDriver(this.questionPage.getDriver())
                .perform();
        var updatedQuestionNumber = this.getCurrentQuestionNumber();
        return updatedQuestionNumber < questionNumber;
    }

    @Step("Click the next question button")
    public void moveToNextQuestion() {
        this.questionPage.getNextButton().click();
    }

    @Step("Click the next question button")
    public void moveToPreviousQuestion() {
        this.questionPage.getPreviousButton().click();
    }

    @Step("Get current question number")
    public int getCurrentQuestionNumber() {
        return this.questionPage.getQuestionNumber();
    }

    @Step("Checks that they see learning mode")
    public boolean shouldSeeLearningMode() {
        return this.questionPage.isLearningModeScreenDisplayed();
    }

    @Step("Gets the current question category")
    public String getQuestionCategory() {
        return this.questionPage.getCategoryElement().getText();
    }
}
