package uk.co.envyware.alchemy.screenplay.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matchers;
import uk.co.envyware.alchemy.screenplay.navigation.HomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class HomePageStepDefinitions {

    @Given("{actor} is doing exam practice")
    public void isDoingExamPractice(Actor actor) {
        actor.wasAbleTo(
                HomePage.open()
        );
    }

    @Then("{actor} should see the home page")
    public void shouldSeeTheAlchemyHomePage(Actor actor) {
        actor.attemptsTo(Ensure.that(TheWebPage.source()).contains("Alchemy"));
    }

    @Then("{actor} should see exams appear")
    public void shouldSeeExamsAppear(Actor actor) {
        actor.attemptsTo(WaitUntil.the(HomePage.EXAM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(seeThat("visible exam cards",HomePage.visibleExamCount(), Matchers.greaterThan(0)));
    }

    @Then("{actor} should not see exams appear")
    public void shouldNotSeeExamsAppear(Actor actor) {
        actor.attemptsTo(WaitUntil.the(HomePage.EXAM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(seeThat("visible exam cards",HomePage.visibleExamCount(), Matchers.equalTo(0)));
    }
}
