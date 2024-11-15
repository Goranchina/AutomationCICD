import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException {

        landingPage.loginApplication("goranfdssfq@gmail.com","Somfsfepass10");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }

    @Test
    public void ProductErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("goranpodolski@gmail.com", "AnyPass12");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);


    }

}
