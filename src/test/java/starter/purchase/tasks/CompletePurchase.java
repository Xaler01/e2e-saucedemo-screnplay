package starter.purchase.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.purchase.ui.SaucedemoPage;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletePurchase {
    public static Task withInfo(Map<String, String> info) {
        return Task.where("{0} completes purchase information",
                WaitUntil.the(SaucedemoPage.FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(info.get("firstName")).into(SaucedemoPage.FIRST_NAME),
                Enter.theValue(info.get("lastName")).into(SaucedemoPage.LAST_NAME),
                Enter.theValue(info.get("zipCode")).into(SaucedemoPage.ZIP_CODE),
                Click.on(SaucedemoPage.CONTINUE_BUTTON),
                WaitUntil.the(SaucedemoPage.FINISH_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(SaucedemoPage.FINISH_BUTTON)
        );
    }
}