import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

    ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
    List<WebElement> products = productCatalogue.getProductList();
    productCatalogue.addProductToCart(input.get("product"));
    CartPage cartPage = productCatalogue.goToCartPage();

    Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
    Assert.assertTrue(match);
    CheckoutPage checkoutPage = cartPage.goToCheckut();
    checkoutPage.selectCountry("India");
    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
    String confirmMessage = confirmationPage.getConfirmationMessage();
    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

    }

    // To verify ZARA COAT 3 is displaying in orders page

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        // ZARA COAT 3
        ProductCatalogue productCatalogue = landingPage.loginApplication("goransfq@gmail.com","Somepass10");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)} };
    }

    //Extent Reports


//    @DataProvider
//    public Object[][] getData() {
//        return new Object[][] {{"goransfq@gmail.com", "Somepass10", "ZARA COAT 3"}, {"goranpodolski@gmail.com", "AnyPass12", "ADIDAS ORIGINAL"} };
//
//    }

//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "goransfq@gmail.com");
//        map.put("password", "Somepass10");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "goranpodolski@gmail.com");
//        map1.put("password", "AnyPass12");
//        map1.put("product", "ADIDAS ORIGINAL");


}
