package seleniumTest;

import com.google.common.base.Strings;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gfox on 10/05/2016.
 */
public class SampleTest {

    Logger logger = LogManager.getLogger(SampleTest.class);
    static WebDriver driver;
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    private String deviceName;
    private String deviceSerial;
    private boolean physicalDevice;

    @BeforeTest
    @Parameters({"deviceName", "serialId", "physicalDevice"})
    public void setUpDriver(@Optional("") String deviceName, @Optional("") String serialId, boolean physicalDevice) {
        this.deviceName = deviceName;
        this.deviceSerial = serialId;
        this.physicalDevice = physicalDevice;
        System.setProperty("webdriver.chrome.driver", new File("").getAbsolutePath() + "\\selenium_standalone\\windows\\googlechrome\\64bit\\chromedriver.exe");
        capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
        HashMap<String, String> chromePreferences = new HashMap<String, String>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        capabilities.setCapability("chrome.prefs", chromePreferences);
        driver = physicalDevice ? physicalDevice() : emulatedDevice();
    }

    @Test
    public void startTest() throws InterruptedException {
        driver.get("https://www.buyagift.co.uk/");
        BuyAGift.buyGiftBasedOnCustomerType("men");
        BuyAGift.selectMenGiftOnPage();
    }

    @AfterTest
    public void quit() {
        driver.quit();
        if (this.physicalDevice) {
            stopAdbServer();
        }
    }

    public WebDriver emulatedDevice() {
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", this.deviceName);
        chromeOptions.put("mobileEmulation", mobileEmulation);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    public WebDriver physicalDevice() {
        startAdbServer();
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("androidPackage", "com.android.chrome");
        if (!Strings.isNullOrEmpty(this.deviceSerial)) {
            chromeOptions.put("androidDeviceSerial", this.deviceSerial);
        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    @SneakyThrows
    public void startAdbServer() {
        Runtime.getRuntime().exec("adb devices");
        logger.log(Level.INFO, "Starting adb server");
        Process p = Runtime.getRuntime().exec("adb.exe start-server");
        p.waitFor();
    }

    @SneakyThrows
    public void stopAdbServer() {
        logger.log(Level.INFO, "Stopping adb server");
        Process p = Runtime.getRuntime().exec("adb.exe kill-server");
        p.waitFor();
    }
}
