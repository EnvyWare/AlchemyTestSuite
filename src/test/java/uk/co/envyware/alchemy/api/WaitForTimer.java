package uk.co.envyware.alchemy.api;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public class WaitForTimer {

    public static <T> Performable toChangeFrom(Supplier<T> valueFunction, T startingValue) {
        return Task.where("{0} waits for the target to change from " + startingValue,
                actor -> {
                    var driver = BrowseTheWeb.as(actor).getDriver();

                    new WebDriverWait(driver, Duration.ofSeconds(3))
                            .pollingEvery(Duration.ofMillis(100))
                            .until(webDriver -> {
                                var currentValue = valueFunction.get();
                                return !Objects.equals(currentValue, startingValue);
                            });
                }
        );
    }
}
