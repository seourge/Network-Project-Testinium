import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends BasePage{

    private CSVReader csvReader;
    String[] cell;
    String CSV_PATH = "C:\\Java Projelerim\\NetWorkProjeOdevi\\Csv-Format\\LoginInformation.csv";
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void mailAndPasswordCsv() throws IOException, CsvValidationException {
        csvReader = new CSVReader(new FileReader(CSV_PATH));

        while ((cell=csvReader.readNext())!=null){
            for (int i = 0; i < 1; i++) {
                String mail = cell[i];
                String password = cell[i+1];
                find(mailLocator).sendKeys(mail);
                find(passwordLocator).sendKeys(password);
            }
        }
    }
    public boolean loginButtonCheck() {
        return isDisplayed(loginButtonLocator);
    }
    public void logoSubmit(){
        find(logoLocator).click();
    }

}
