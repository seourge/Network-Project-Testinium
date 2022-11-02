import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    SearchBox searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
    }

    public SearchBox searchBox() {
        return this.searchBox;
    }

    public void acceptCookies(){
        if(isDisplayed(acceptCookiesLocator)){
            click(acceptCookiesLocator);
        }
    }

    public void submitCart(){
        find(cartButtonLocator).click();
    }
    public void deleteProduct(){
        find(deleteButtonLocator).click();
        find(deleteButtonLocator2).click();
    }
    public boolean isCartEmpty(){
        return isDisplayed(emptyCartTextLocator);
    }

}
