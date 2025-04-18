package starter.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.purchase.questions.OrderConfirmation;
import starter.purchase.tasks.AddToCart;
import starter.purchase.tasks.Checkout;
import starter.purchase.tasks.CompletePurchase;
import starter.purchase.tasks.Login;
import starter.purchase.ui.SaucedemoPage;
import starter.purchase.utils.PurchaseDataReader;


import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PurchaseStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I login as user {string}")
    public void i_login_as_user(String userKey) throws Exception {
        JsonNode userData = PurchaseDataReader.getUserData(userKey);
        String username = userData.get("username").asText();
        String password = userData.get("password").asText();

        theActorCalled(username).attemptsTo(
                Open.url("https://www.saucedemo.com"),
                WaitUntil.the(SaucedemoPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Login.withCredentials(username, password)
        );
    }

    @When("I add the listed products for user {string}")
    public void i_add_products_for_user(String userKey) throws Exception {
        JsonNode userData = PurchaseDataReader.getUserData(userKey);
        ObjectMapper mapper = new ObjectMapper();
        List<String> products = mapper.convertValue(
                userData.get("products"),
                new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {}
        );

        theActorInTheSpotlight().attemptsTo(
                AddToCart.fromList(products)
        );
    }

    @When("I view my cart")
    public void i_view_my_cart() {
        theActorInTheSpotlight().attemptsTo(
                Checkout.proceed()
        );
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        theActorInTheSpotlight().attemptsTo(
                Checkout.proceed()
        );
    }

    @When("I enter checkout data for user {string} and index {int}")
    public void i_enter_checkout_data(String userKey, int index) throws Exception {
        List<Map<String, String>> checkoutList = PurchaseDataReader.getCheckoutList(userKey);

        if (index < checkoutList.size()) {
            theActorInTheSpotlight().attemptsTo(
                    CompletePurchase.withInfo(checkoutList.get(index))
            );
        } else {
            throw new IllegalArgumentException("Invalid index for checkoutData");
        }
    }

    @Then("I should see the confirmation for user {string}")
    public void i_should_see_confirmation(String userKey) throws Exception {
        JsonNode userData = PurchaseDataReader.getUserData(userKey);
        String expectedMessage = userData.get("confirmationMessage").asText();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(OrderConfirmation.message()).contains(expectedMessage)
        );
    }
}
