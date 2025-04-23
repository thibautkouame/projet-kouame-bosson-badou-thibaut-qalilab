package org.projetFinalFormationSeleniumQalilab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Setup {

    public WebDriver SetupDriver() {
        ChromeOptions options = new ChromeOptions();

        // démarrer le navigateur en plein écran
        options.addArguments("--start-maximized");

        // créer une instance de ChromeDriver
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
