import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Random;

public class BasePage {
    WebDriver driver;
    String firstPriceLoc; //Sepete gidilmeden önceki fiyat
    String priceLoc; //Sepetteki fiyat
    String firstSizeLoc; //Sepete gidilmeden önceki beden
    String sizeLoc; //Sepetteki beden
    By searchBoxLocator = By.xpath("//input[@id='search']");
    By acceptCookiesLocator = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    By scrollDownToLocator = By.xpath("//button[@class='button -secondary -sm relative']");
    By firstDiscountProductLocator = By.xpath("(//span[small='%'])[1]");
    By randomSizeLocator = By.xpath("(//label[@class='radio-box__label'])[1]");
    By goToCartLocator = By.xpath("//a[@class='button -primary header__basket--checkout header__basketModal -checkout']");
    By goToContinueLocator = By.xpath("//button[@class='continueButton n-button large block text-center -primary']");
    By mailLocator = By.xpath("//input[@class='input js-trim']");
    By passwordLocator = By.xpath("//input[@class='input']");
    By logoLocator = By.cssSelector(".headerCheckout__logo__img");
    By loginButtonLocator = By.xpath("//button[@class='n-button large block text-center -primary ']");
    By cartButtonLocator = By.xpath("//button[@class='header__basketTrigger js-basket-trigger -desktop']");
    By deleteButtonLocator = By.cssSelector("svg[class='header__basketProductRemove']");
    By deleteButtonLocator2 = By.xpath("//button[@class='btn -black o-removeCartModal__button']");
    By emptyCartTextLocator = By.xpath("//span[text()='Sepetiniz Henüz Boş']");
    By orginalPriceLocator = By.xpath("//span[@class='cartItem__price -old -seasonPrice']");
    By discountPriceLocator = By.xpath("//span[@class='cartItem__price -sale']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By by) {
        return driver.findElement(by);
    }

    public void click(By by) {
        find(by).click();
    }

    public void sendKeysToElement(By by, String text) {
        find(by).sendKeys(text);
    }

    public void submitEnter(By by) {
        find(by).sendKeys(Keys.ENTER);
    }

    public boolean isDisplayed(By by) {
        return find(by).isDisplayed();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 15000; i += 81) {
            js.executeScript("window.scrollTo(0, " + i + ")");
        }
        click(scrollDownToLocator);
    }

    public void hover() {
        WebElement ele = driver.findElement(firstDiscountProductLocator);
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();

    }

    public void selectRandom() {
        List<WebElement> allProducts = driver.findElements(randomSizeLocator);
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
    }

    public void firstCheck() {
        firstPriceLoc = driver.findElement(By.cssSelector("span[class='a-product__price -salePrice']")).getText();
        System.out.println(firstPriceLoc);

        firstSizeLoc = driver.findElement(By.xpath("(//div[@class='header__basketProductVariation--val'])[2]")).getText();
        System.out.println(firstSizeLoc);
    }

    public void secondCheck() {
        priceLoc = driver.findElement(By.cssSelector("span[class='cartItem__price -sale']")).getText();
        System.out.println(priceLoc);

        sizeLoc = driver.findElement(By.xpath("(//span[@class='cartItem__attrValue'])[1]")).getText();
        System.out.println(sizeLoc);

        Assertions.assertEquals(firstPriceLoc, priceLoc, "prices are not equals");
        Assertions.assertEquals(firstSizeLoc, sizeLoc, "sizes are not equals");
    }

    public boolean orginalAndDiscountPriceCheck() {
        try
        {
           int discountedPrice=Integer.parseInt(find(discountPriceLocator).getText().split(",")[0]);
           int originalPrice=Integer.parseInt(find(orginalPriceLocator).getText().split(",")[0]);

           if(originalPrice>discountedPrice){
               return true;
           }
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return false;
    }
}
