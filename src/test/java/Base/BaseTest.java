package Base;


import Pages.HomepagePage;
import Pages.PracticeFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static WebDriver driver;

    public HomepagePage homepagePage;
    public PracticeFormPage practiceFormPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homepagePage = new HomepagePage();
        practiceFormPage = new PracticeFormPage();
    }
}
