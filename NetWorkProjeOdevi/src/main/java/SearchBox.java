import org.openqa.selenium.WebDriver;

public class SearchBox extends BasePage{

    public SearchBox(WebDriver driver) {
        super(driver);
    }

    public void search(String text) {
        sendKeysToElement(searchBoxLocator,text);
        submitEnter(searchBoxLocator);
    }

}
