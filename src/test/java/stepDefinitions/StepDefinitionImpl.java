package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
        //code
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username,password);
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When ("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckut();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_is_displayed_on_Confirmation_Page(String string) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
        Assert.assertEquals(string,landingPage.getErrorMessage());
        driver.close();
    }
}
