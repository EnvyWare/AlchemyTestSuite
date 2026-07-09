package uk.co.envyware.alchemy.api;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public class WaitForValue {

    public static <T> Performable toChangeFrom(T startingValue, Supplier<T> valueFunction) {
        return builder(startingValue, valueFunction).build();
    }

    public static <T> Builder<T> builder(T startingValue, Supplier<T> valueFunction) {
        return new Builder<T>()
                .withStartingValue(startingValue)
                .withValueFunction(valueFunction);
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private String title = "{0} waits for the target to change from {1}";
        private T startingValue;
        private Supplier<T> valueFunction;
        private Duration timeout = Duration.ofSeconds(3);
        private Duration pollingInterval = Duration.ofMillis(100);

        private Builder() {
        }

        public Builder<T> withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder<T> withStartingValue(T startingValue) {
            this.startingValue = startingValue;
            return this;
        }

        public Builder<T> withValueFunction(Supplier<T> valueFunction) {
            this.valueFunction = valueFunction;
            return this;
        }

        public Builder<T> withTimeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder<T> withPollingInterval(Duration pollingInterval) {
            this.pollingInterval = pollingInterval;
            return this;
        }

        public Performable build() {
            return Task.where(this.title,
                    actor -> {
                        var driver = BrowseTheWeb.as(actor).getDriver();

                        new WebDriverWait(driver, this.timeout)
                                .pollingEvery(this.pollingInterval)
                                .until(webDriver -> {
                                    var currentValue = this.valueFunction.get();

                                    return !Objects.equals(currentValue, this.startingValue);
                                });
                    }
            );
        }
    }
}
