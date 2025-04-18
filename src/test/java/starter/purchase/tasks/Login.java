package starter.purchase.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.purchase.ui.SaucedemoPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login {

    public static Task withCredentials(String username, String password) {
        return Task.where("{0} logs in with username " + username,
                WaitUntil.the(SaucedemoPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(SaucedemoPage.USERNAME_FIELD),
                WaitUntil.the(SaucedemoPage.PASSWORD_FIELD, isVisible()).forNoMoreThan(5).seconds(),
                Enter.theValue(password).into(SaucedemoPage.PASSWORD_FIELD),
                WaitUntil.the(SaucedemoPage.LOGIN_BUTTON, isVisible()).forNoMoreThan(5).seconds(),
                Click.on(SaucedemoPage.LOGIN_BUTTON)


        );
    }
}