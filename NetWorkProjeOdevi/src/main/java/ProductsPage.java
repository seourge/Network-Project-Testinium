import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void firstDiscountItem() {
        hover();
    }
    public void selectRandomSize(){
        selectRandom();
    }

    public void goToCart() {
        find(goToCartLocator).click();
    }
}
