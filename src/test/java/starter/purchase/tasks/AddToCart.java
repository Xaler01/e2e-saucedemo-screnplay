package starter.purchase.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import starter.purchase.ui.SaucedemoPage;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddToCart {
    public static Task twoProducts() {
        return Task.where("{0} adds two products to cart",
                // Primero agrega el primer producto
                Click.on(SaucedemoPage.PRODUCT_1),

                // Verifica si el modal está visible y, si lo está, lo cierra sin detener el flujo
                //Check.whether(SaucedemoPage.PASSWORD_MODAL).isVisible()
                //        .andIfSo(Click.on(SaucedemoPage.CLOSE_MODAL_BUTTON)),

                // Luego agrega el segundo producto
                Click.on(SaucedemoPage.PRODUCT_2)
        );
    }
}
