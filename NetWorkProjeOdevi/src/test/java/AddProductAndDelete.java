import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class AddProductAndDelete extends BaseTest {
    String networkUrl = "https://www.network.com.tr/";
    String networkUrlceket = "https://www.network.com.tr/search?searchKey=ceket&page=2";
    HomePage homepage;
    ProductsPage productsPage;
    CartPage cartPage;
    LoginPage loginPage;

    @Test
    @Order(1)
    public void search_product() {
        //-Network url nin geldiği kontrol edilir
        //-Arama sekmesinde ceket yazdırılır ve arama yaptırılır
        homepage = new HomePage(driver);
        homepage.acceptCookies();
        Assertions.assertEquals(networkUrl,"https://www.network.com.tr/", "---------url is not correct---------");
        homepage.searchBox().search("ceket");
    }

    @Test
    @Order(2)
    public void selectProduct() throws InterruptedException {
        //-Ürün listeme sayfasında daha fazla göster butonuna kadar scroll yapılır
        //-2.sayfaya geçildiği kontrol edilir(url değişiyor)
        //-Ürün listeleme sayfasında İndirimli ilk ürüne hover edilerek rastgele beden seçimi yapılır.
        productsPage = new ProductsPage(driver);
        Thread.sleep(400);
        productsPage.scrollDown();
        Assertions.assertEquals(networkUrlceket,"https://www.network.com.tr/search?searchKey=ceket&page=2", "---------url is not correct---------");
        Thread.sleep(600);
        productsPage.firstDiscountItem();
        Thread.sleep(900);
        productsPage.selectRandomSize();
    }

    @Test
    @Order(3)
    public void goToCart() throws InterruptedException {
        //-Sağ alt köşede açılan Sepete git butonu seçilir
        //-Ürüne ait beden ve fiyat bilgisinin sepette doğru geldiği kontrol edilir.
        //-Sepete eklenen ürünün eski fiyatının indirimli fiyatından büyük olduğu kontrol ettirilir
        //-Devam et butonuna tıklanır
        cartPage = new CartPage(driver);
        Thread.sleep(500);
        cartPage.firstSizeAndPriceCheck();
        Thread.sleep(800);
        productsPage.goToCart();
        Thread.sleep(800);
        cartPage.sizeAndPriceCheck();
        Thread.sleep(600);

        cartPage.checkDiscountPrice();
        cartPage.goToContinue();
    }

    @Test
    @Order(4)
    public void loginInformation() throws IOException, CsvValidationException, InterruptedException {
        //-Kullanıcı bilgileri csv formatından çekilerek doldurulur(E-posta-şifre csv uzantılı dosyadan okunması
        //gerekmektedir)
        //-Giriş yap butonunun geldiği kontrol edilir
        //-Network logosuna tıklanır.
        Thread.sleep(800);
        loginPage = new LoginPage(driver);
        loginPage.mailAndPasswordCsv();
        Assertions.assertTrue(loginPage.loginButtonCheck(), "login button not visible");
        loginPage.logoSubmit();
    }

    @Test
    @Order(5)
    public void deleteCart() throws InterruptedException {
        //-Anasayfa üzerinde çanta logosuna tıklanarak Sepetim ekranın sağ tarafında açılır.
        //-Ürün sepetten çıkarılarak sepetin boş olduğu kontrol edilir
        homepage.submitCart();
        homepage.deleteProduct();
        Thread.sleep(800);
        homepage.submitCart();
        Assertions.assertTrue(homepage.isCartEmpty(),"cart is not empty");
    }
}
