import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void checkDiscountPrice(){
        orginalAndDiscountPriceCheck();
    }


    public void firstSizeAndPriceCheck() {
        firstCheck();
    }

    public void sizeAndPriceCheck(){
      secondCheck();
    }

    public void goToContinue() {
        find(goToContinueLocator).click();
    }
}
