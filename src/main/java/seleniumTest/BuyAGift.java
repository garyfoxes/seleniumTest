package seleniumTest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by gfox on 11/05/2016.
 */
public class BuyAGift {

    private static final Logger logger = LogManager.getLogger(BuyAGift.class.getName());

    public static void buyGiftBasedOnCustomerType(String customerType) {
        WebElement element = (new WebDriverWait(SampleTest.driver, 30, 1)).until(ExpectedConditions.presenceOfElementLocated(Objects.lnkGiftByCustomerType(customerType)));
        element.click();
    }

    public static void selectMenGiftOnPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(SampleTest.driver, 10, 1);
        logger.debug("Waiting for mens gift");
        wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.divPageIntroduction()));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Objects.componentMensDrivingSection()));
        JavascriptExecutor script = (JavascriptExecutor) SampleTest.driver;
        script.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.debug("Clicking SuperCar Driving Blast");
        WebElement productText = wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.txtProductTitle("Supercar Driving Blast")));
        productText.click();
        WebElement btnBuyNow = wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.btnBuyNow()));
        btnBuyNow.click();
        WebElement checkBoxGiftPack = wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.checkboxGiftPack()));
        checkBoxGiftPack.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Objects.ajaxLoader()));
        WebElement deliveryDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(Objects.dropdownDelivery()));
        script.executeScript("arguments[0].scrollIntoView(true);", deliveryDropdown);
        Select dropdown = new Select(deliveryDropdown);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Objects.ajaxLoader()));
        dropdown.selectByIndex(1);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Objects.ajaxLoader()));
        SampleTest.driver.findElement(Objects.btnPaySecurelyNow()).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Objects.ajaxLoader()));
        logger.log(Level.INFO, "Selecting Alert");
        wait.until(ExpectedConditions.alertIsPresent());
        SampleTest.driver.switchTo().alert().accept();
        WebElement btnContinueAsGuest = wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.btnContinueAsGuest()));
        SampleTest.driver.findElement(Objects.inputEmailAddress()).sendKeys("joe.john@hotmail.com");
        btnContinueAsGuest.click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(Objects.dropdownTitle()));
        Select titleDropdown = new Select(title);
        titleDropdown.selectByIndex(1);
        Thread.sleep(5000);
    }
}
