package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import starter.purchase.questions.OrderConfirmation;
import starter.purchase.tasks.*;
import starter.purchase.ui.SaucedemoPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PurchaseStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I login as {string} with password {string}")
    public void i_login_as_with_password(String username, String password) {
        theActorCalled(username).attemptsTo(
                Open.url("https://www.saucedemo.com"),
                WaitUntil.the(SaucedemoPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Login.withCredentials(username, password)
        );
    }

    @When("I add the first two products to the cart")
    public void i_add_the_first_two_products_to_the_cart() {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.twoProducts()
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


    @When("I enter my checkout information from file {string} for user {int}")
    public void enter_checkout_info_from_file(String filename, int userIndex) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Ruta del archivo JSON con los diferentes datos
        File file = Paths.get("src", "test", "resources", "test-data", filename).toFile();

        // Leer todos los usuarios
        List<Map<String, String>> users = mapper.readValue(file, List.class);

        // Seleccionar el usuario por su índice
        if (userIndex >= 0 && userIndex < users.size()) {
            Map<String, String> user = users.get(userIndex);

            // Aquí se llama a la tarea para completar la compra con la información del usuario seleccionado
            theActorInTheSpotlight().attemptsTo(
                    CompletePurchase.withInfo(user)
            );
        } else {
            throw new IllegalArgumentException("Invalid user index: " + userIndex);
        }
    }

    @Then("I should see the order confirmation {string}")
    public void i_should_see_the_order_confirmation(String expectedMessage) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(OrderConfirmation.message()).contains(expectedMessage)
        );
    }
}