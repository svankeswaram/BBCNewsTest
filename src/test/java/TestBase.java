import java.util.concurrent.TimeUnit;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    private static WebDriver driver;

    private static final String CHROME_KEY="webdriver.chrome.driver";
    private static final String CHROME_VALUE="drivers/chromedriver";
    /**
     * Wait timeout 1 second.
     */
    public final long ONE_SECOND_MS = 1000;
    /**
     * Wait timeout 2 seconds.
     */
    public static final long TWO_SECOND_MS = 2000;
    /**
     * Wait timeout 3 seconds.
     */
    public static final long THREE_SECOND_MS = 3000;
    /**
     * Wait timeout 5 seconds.
     */
    public static final long FIVE_SECOND_MS = 5000;
    /**
     * Browser timeout.
     */
    private static final long TIMEOUT = 20;
    @Before
    public void before() {

        System.setProperty(CHROME_KEY, CHROME_VALUE);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            if (screenshot != null ) {
                scenario.embed(screenshot, "image/png");
            }
        }
        driver.close();
    }
}
