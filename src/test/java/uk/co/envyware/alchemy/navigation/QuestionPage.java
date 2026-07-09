package uk.co.envyware.alchemy.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import java.util.regex.Pattern;

public class QuestionPage {

    public static final Target QUESTION_ID = Target.the("question id")
            .locatedBy("[data-testid='quiz-question-counter']");

    public static final Target QUIZ_TIMER = Target.the("quiz timer")
            .locatedBy("[data-testid='quiz-timer']");

    private static final Target ANSWER_BUTTON = Target.the("{0} answer button")
            .locatedBy("[data-testid='answer-option'][data-option-label='{0}']");

    public static final Target BACK_BUTTON = Target.the("back button")
            .locatedBy("[data-testid='previous-question-button']");

    public static final Target NEXT_BUTTON = Target.the("next button")
            .locatedBy("[data-testid='next-question-button']");

    public static final Target SUBMIT_BUTTON = Target.the("submit button")
            .locatedBy("[data-testid='submit-answer-button']");

    public static final Target QUIZ_CATEGORY = Target.the("quiz category")
            .locatedBy("[data-testid='quiz-question-category']");

    public static final Target LEARNING_FEEDBACK = Target.the("learning feedback section")
            .locatedBy("[data-testid='learning-feedback']");

    public static final Target LEARNING_FEEDBACK_EXPLANATION = Target.the("learning feedback explanation section")
            .locatedBy("[data-testid='learning-feedback-explanation']");

    public static final Target QUIZ_EXAM_TITLE = Target.the("quiz exam title")
            .locatedBy("[data-testid='quiz-exam-title']");

    private static final Pattern QUESTION_ID_PATTERN = Pattern.compile("Question (\\d+) of (\\d+)");
    private static final Pattern QUIZ_TIMER_PATTERN = Pattern.compile("(Timer|Time spent): (\\d{1,2}:)?(\\d{1,2}):(\\d{1,2})");


    public static Question<Integer> currentQuestionNumber() {
        return Question.about("current question number")
                .answeredBy(a -> {
                    var questionIdText = QUESTION_ID.resolveFor(a).getText();
                    var matcher = QUESTION_ID_PATTERN.matcher(questionIdText);

                    if (!matcher.matches()) {
                        throw new IllegalStateException("Question ID text does not match expected format: " + questionIdText);
                    }

                    return Integer.parseInt(matcher.group(1));
                });
    }

    public static Question<Integer> currentTime() {
        return Question.about("current quiz time")
                .answeredBy(a -> {
                    var timerText = QUIZ_TIMER.resolveFor(a).getText();
                    var matcher = QUIZ_TIMER_PATTERN.matcher(timerText);

                    if (!matcher.matches()) {
                        throw new IllegalStateException("Quiz timer text does not match expected format: " + timerText);
                    }

                    int hours = matcher.group(2) != null ? Integer.parseInt(matcher.group(2).replace(":", "")) : 0;
                    int minutes = Integer.parseInt(matcher.group(3));
                    int seconds = Integer.parseInt(matcher.group(4));

                    return hours * (60 * 60) + minutes * 60 + seconds;
                });
    }

    public static Performable selectAnswer(String optionLabel) {
        return Click.on(answerButton(optionLabel));
    }

    public static Performable submitAnswer() {
        return Click.on(SUBMIT_BUTTON);
    }

    public static Target answerButton(String optionLabel) {
        return ANSWER_BUTTON.of(optionLabel);
    }

    public static Question<String> currentCategory() {
        return Question.about("current quiz category")
                .answeredBy(a -> QUIZ_CATEGORY.resolveFor(a).getText());
    }

    public static Question<String> currentExamTitle() {
        return Question.about("current quiz exam title")
                .answeredBy(a -> QUIZ_EXAM_TITLE.resolveFor(a).getText());
    }

}
