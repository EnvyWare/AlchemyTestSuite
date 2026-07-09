package uk.co.envyware.alchemy.navigation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
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

    private static final Pattern QUESTION_ID_PATTERN = Pattern.compile("Question (\\d+) of (\\d+)");
    private static final Pattern QUIZ_TIMER_PATTERN = Pattern.compile("(Timer|Time spent): (\\d{1,2}:)?(\\d{1,2}):(\\d{1,2})");


    public static Question<Integer> currentQuestionNumber(Actor actor) {
        var questionIdText = QUESTION_ID.resolveFor(actor).getText();
        var matcher = QUESTION_ID_PATTERN.matcher(questionIdText);

        if (!matcher.matches()) {
            throw new IllegalStateException("Question ID text does not match expected format: " + questionIdText);
        }

        return Question.about("current question number")
                .answeredBy(a -> Integer.parseInt(matcher.group(1)));
    }

    public static Question<Integer> currentTime(Actor actor) {
        var timerText = QUIZ_TIMER.resolveFor(actor).getText();
        var matcher = QUIZ_TIMER_PATTERN.matcher(timerText);

        if (!matcher.matches()) {
            throw new IllegalStateException("Quiz timer text does not match expected format: " + timerText);
        }

        int hours = matcher.group(2) != null ? Integer.parseInt(matcher.group(2).replace(":", "")) : 0;
        int minutes = Integer.parseInt(matcher.group(3));
        int seconds = Integer.parseInt(matcher.group(4));

        return Question.about("current quiz time")
                .answeredBy(a -> hours * (60 * 60) + minutes * 60 + seconds);
    }

    public static Target answerButton(String optionLabel) {
        return ANSWER_BUTTON.of(optionLabel);
    }

    public static Question<String> currentCategory() {
        return Question.about("current quiz category")
                .answeredBy(a -> QUIZ_CATEGORY.resolveFor(a).getText());
    }


}
