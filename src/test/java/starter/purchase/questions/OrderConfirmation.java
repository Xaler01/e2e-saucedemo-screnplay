// src/test/java/starter/purchase/questions/OrderConfirmation.java
package starter.purchase.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.purchase.ui.SaucedemoPage;

public class OrderConfirmation {
    public static Question<String> message() {
        return actor -> Text.of(SaucedemoPage.CONFIRMATION_MESSAGE)
                .answeredBy(actor);
    }
}