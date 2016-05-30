package seleniumTest;

import org.openqa.selenium.By;

/**
 * Created by gfox on 11/05/2016.
 */
public class Objects {

    public static By divPageIntroduction() {
        return By.id("page_introduction");
    }

    public static By lnkGiftByCustomerType(String customerType) {
        return By.xpath("//a[@data-re ='homepage-_-pod-_-1-_-" + customerType + "']");
    }

    public static By componentMensDrivingSection() {
        return By.id("driving_section");
    }

    public static By txtProductTitle(String title) {
        return By.xpath("//h2[@class='prd_ttl' and text() ='" + title + "'] | //h2[@class='prd_ttl' and text() ='Triple Supercar Driving Blast Special Offer']");
    }

    public static By btnBuyNow() {
        return By.xpath("(//div[@class='buynowbutton']/input)[1]");
    }

    public static By checkboxGiftPack() {
        return By.xpath("(//input[@name ='packagingoptions-0'])[2]");
    }

    public static By dropdownDelivery() {
        return By.xpath("//select[contains(@ng-change,'changeBasketDelivery')]");
    }

    public static By btnPaySecurelyNow() {
        return By.xpath("//div[@id ='basket_payment_options']/a[@ng-click='continueToCheckout()']");
    }

    public static By btnContinueAsGuest() {
        return By.xpath("//button[@ng-click='ContinueAsGuest(true)']");
    }

    public static By inputEmailAddress() {
        return By.xpath("//input[@id='account_email_field']");
    }

    public static By dropdownTitle() {
        return By.xpath("//select[@id='titlefield']");
    }

    public static By ajaxLoader() {
        return By.xpath("//div[@class='ajax_loader' and @style='display: block;']");
    }
}
