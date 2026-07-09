package uk.co.envyware.alchemy.navigation;

import net.serenitybdd.screenplay.Actor;

import java.util.Locale;

public class Alchemy {

    public static final String SALESFORCE_PLATFORM = "SF";
    public static final String GOOGLE_PLATFORM = "GCP";

    public static final String SALESFORCE_ADMIN_EXAM = "ADMIN";

    public static final String MOCK_EXAM_LAUNCH = "start-mock-quiz-button";
    public static final String LEARNING_MODE_LAUNCH = "start-learning-mode-button";

    public static String getPlatformId(Actor actor) {
        return getPlatformId(actor.getName());
    }

    public static String getPlatformId(String platformName) {
        return switch (platformName.toLowerCase(Locale.ROOT)) {
            case "salesforce", "sf" -> SALESFORCE_PLATFORM;
            case "google", "gcp" -> GOOGLE_PLATFORM;
            default -> throw new IllegalArgumentException("Unknown platform: " + platformName);
        };
    }

    public static String getExamId(Actor actor) {
        return getExamId(actor.getName());
    }

    public static String getExamId(String examName) {
        return switch (examName.toLowerCase(Locale.ROOT)) {
            case "administrator", "admin" -> SALESFORCE_ADMIN_EXAM;
            default -> throw new IllegalArgumentException("Unknown exam: " + examName);
        };
    }
}
