package org.projetFinalFormationSeleniumQalilab;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Auth {

    WebDriver driver;
    private LoginPage loginPage;
    Setup driverSetup = new Setup();

    @BeforeMethod
    public void setUp() {
        driver = driverSetup.SetupDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testAuthValid() {
        loginPage.loginWithValidCredentials();
        Assert.assertTrue(loginPage.isDashboardDisplayed());
        System.out.println(String.format(TextConstants.DASHBOARD_REDIRECT_MESSAGE, TextConstants.DASHBOARD_TEXT));
    }

    @Test
    public void testAuthInValid() {
        loginPage.loginWithInvalidCredentials();
        Assert.assertFalse(loginPage.isDashboardDisplayed());
        System.out.println(TextConstants.DASHBOARD_NOT_FOUND_MESSAGE);
        Assert.assertEquals(loginPage.getErrorMessage(), TextConstants.INVALID_CREDENTIALS_MESSAGE);
    }

    @Test
    public void ajouterUnEmploye() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TextConstants.IMPLICIT_WAIT_SECONDS));

        // faire appel à notre fonction Login
        loginPage.loginWithValidCredentials();

        WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "L'utilisateur n'est pas redirigé vers le tableau de bord.");
        Assert.assertEquals(dashboard.getText(), TextConstants.DASHBOARD_TEXT);

        driver.findElement(By.cssSelector("a[href=\"/web/index.php/pim/viewPimModule\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.xpath("//button[normalize-space()='Add']")).isEnabled());
        WebElement addBtn = wait.until(d -> d.findElement(By.xpath("//button[normalize-space()='Add']")));
        addBtn.click();

        WebElement firstName = driver.findElement(By.cssSelector("input[name='firstName']"));
        firstName.sendKeys(TextConstants.EMPLOYEE_FIRST_NAME);
        firstName.clear();

        WebElement middleName = driver.findElement(By.cssSelector("input[name='middleName']"));
        middleName.sendKeys(TextConstants.EMPLOYEE_MIDDLE_NAME);
        middleName.clear();

        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.clear();
        lastName.sendKeys(TextConstants.EMPLOYEE_LAST_NAME);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);

        try {
            WebElement sucessMessage = driver.findElement(By.xpath("//div[@role='alert']"));
            Assert.assertTrue(sucessMessage.isDisplayed());
            Assert.assertEquals(sucessMessage.getText(), TextConstants.SUCCESS_MESSAGE);
        } catch (NoSuchElementException e) {
            System.out.println(TextConstants.EMPLLOYEE_ADDED_MESSAGE);
        }
    }

    @Test
    public void voirlesInfo() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TextConstants.IMPLICIT_WAIT_SECONDS));

        // faire appel à notre fonction Login
        loginPage.loginWithValidCredentials();

        WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "L'utilisateur n'est pas redirigé vers le tableau de bord.");
        Assert.assertEquals(dashboard.getText(), TextConstants.DASHBOARD_TEXT);

        driver.findElement(By.cssSelector("a[href=\"/web/index.php/pim/viewMyDetails\"]")).click();

        try {
            WebElement sucessMessage = driver.findElement(By.xpath("//div[@role='alert']"));
            Assert.assertTrue(sucessMessage.isDisplayed());
            Assert.assertEquals(sucessMessage.getText(), TextConstants.SUCCESS_MESSAGE);
        } catch (NoSuchElementException e) {
            System.out.println(TextConstants.INFO_SECTION_FOUND_MESSAGE);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
