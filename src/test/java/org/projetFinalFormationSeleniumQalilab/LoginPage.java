package org.projetFinalFormationSeleniumQalilab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.projetFinalFormationSeleniumQalilab.TextConstants;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localisation des classes
    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By errorMessage = By.xpath("//div[@role='alert']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TextConstants.IMPLICIT_WAIT_SECONDS));
    }
    
    public void navigateToLoginPage() {
        driver.get(TextConstants.BASE_URL);
    }

    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void loginWithValidCredentials() {
        navigateToLoginPage();
        enterUsername(TextConstants.USERNAME);
        enterPassword(TextConstants.PASSWORD);
        clickLoginButton();
    }

    public void loginWithInvalidCredentials() {
        navigateToLoginPage();
        enterUsername(TextConstants.FALSE_USERNAME);
        enterPassword(TextConstants.FALSE_PASSWORD);
        clickLoginButton();
    }
}
