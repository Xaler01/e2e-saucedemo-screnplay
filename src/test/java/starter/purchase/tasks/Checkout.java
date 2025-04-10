// src/test/java/starter/purchase/tasks/Checkout.java
package starter.purchase.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import starter.purchase.ui.SaucedemoPage;

public class Checkout {
    public static Task proceed() {
        return Task.where("{0} proceeds to checkout",
                Click.on(SaucedemoPage.CART_BUTTON),
                Click.on(SaucedemoPage.CHECKOUT_BUTTON)
        );
    }
}