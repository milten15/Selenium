import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
/*данный тест, проверяет на наличие в футере сайта двух ссылок
* открывает по ссылке ведущей в вк, и проверяет что она действительно открылась
*/
public class Untitled {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://blog.csssr.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled2() throws Exception {
        driver.get(baseUrl + "/qa-engineer/");
        try {
            assertEquals("Служба поиска талантов: hr@csssr.com", driver.findElement(By.cssSelector("nav > div")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("hr@csssr.com", driver.findElement(By.linkText("hr@csssr.com")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Следите за новыми вакансиями: vk.com/csssr", driver.findElement(By.xpath("//nav/div[2]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("vk.com/csssr", driver.findElement(By.linkText("vk.com/csssr")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.linkText("vk.com/csssr")).click();
        try {
            assertEquals("Подписчики", driver.findElement(By.cssSelector("span.header_label.fl_l")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Контакты", driver.findElement(By.cssSelector("#public_contacts > a.module_header > h3.header_top.clear_fix > span.header_label.fl_l")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
