import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Chrome {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String driverPath = "";
        String url = "http://alex.academy/ua";

        if (System.getProperty("os.name").contains("Mac"))
            driverPath = "webdrivers/mac/chromedriver";
        else if (System.getProperty("os.name").contains("Windows"))
            driverPath = "webdrivers/pc/chromedriver.exe";
        else
            throw new IllegalArgumentException("Unknown OS");

        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("disable-infobars");
        option.addArguments("--disable-notifications");

        if (System.getProperty("os.name").contains("Mac"))
            option.addArguments("-start-fullscreen");
        else if (System.getProperty("os.name").contains("Windows"))
            option.addArguments("--start-maximized");

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);

        String ua = driver.findElement(By.id("id_ua")).getText();
        System.out.println(ua);
        Thread.sleep(1000);
        driver.quit();
    }
}
