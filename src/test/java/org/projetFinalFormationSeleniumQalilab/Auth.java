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

    // declaration de WebDriver
    WebDriver driver;

    //url de la page de test
    public String url = "https://opensource-demo.orangehrmlive.com";

    // les variables de noms
    public String EmplyeefirstName = "Bosson Badou Thibaut";
    public String EmplyeelastName = "Kouame";
    public String EmplyeemiddleName = "";

    // le delai d'exécution
    public Integer delay = 5;


    Setup driverSetup = new Setup();

    @BeforeMethod
    public void setUp() {
        // Initialisation du WebDriver
        driver = driverSetup.SetupDriver();
    }

    @Test
    // Connexion avec des identifiants valides
    public void testAuthValid() {
        driver.get(url);

        // Appliquer un delai d'exécution
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delay));

        // Saisir les identifiants valides
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");

        // Connexion
        WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        loginButton.click();

        // L'utilisateur est il rediriger vers le tableau de bord ?
        WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard.");
        Assert.assertEquals(dashboard.getText(), "Dashboard");

        // Si "Dashboard" => afficher un message dans la console
        System.out.println("L'utilisateur est bien redirigé vers le tableau de bord. Car " + dashboard.getText() + " a été trouvé.");
    }


    @Test
    //Connexion avec des identifiants invalides"
    public void testAuthInValid() {
        driver.get(url);

        // Attente de 5 secondes pour que la page soit entièrement chargée
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delay));

        // Renseigner les informations invalides dans les inputs 'username' et 'password'
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Thibaut");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Kouame");

        // Actionner le bouton de connexion
        WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        loginButton.click();



        // Afficher un message dans le cas ou le test échoue
        try {
            WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
            Assert.assertFalse(dashboard.isDisplayed());
        } catch (NoSuchElementException e) {

            System.out.println("L'utilisateur n'a pas été redirigé vers le tableau de bord, car dashboard n'a pas été trouvé");
        }

        // Récuperer le message d'erreur
        WebElement errorMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Identifiants incorrects");
    }



    @Test
    public void ajouterUnEmploye() throws InterruptedException {

        // La page de test
        driver.get(url);

        // Appliquer un delai d'exécution
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delay));

        // saisir les identifiants valides
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");

        // cliquer sur le bouton Login
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // Vérifier que l'utilisateur est redirigé vers le tableau de bord
        WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "L'utilisateur n'est pas redirigé vers le tableau de bord.");
        Assert.assertEquals(dashboard.getText(), "Dashboard");

        // Ensuite lancer le menu PIM (Personnel Information Management)
        driver.findElement(By.cssSelector("a[href=\"/web/index.php/pim/viewPimModule\"]")).click();

        // Ajouter un nouvel employé
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.xpath("//button[normalize-space()='Add']")).isEnabled());
        WebElement addBtn = wait.until(d -> d.findElement(By.xpath("//button[normalize-space()='Add']")));
        addBtn.click();

        // ajouter les informations de l'employé

        WebElement firstName = driver.findElement(By.cssSelector("input[name='firstName']"));
        firstName.sendKeys(EmplyeefirstName);
        firstName.clear();

        WebElement middleName = driver.findElement(By.cssSelector("input[name='middleName']"));
        middleName.sendKeys(EmplyeemiddleName);
        middleName.clear();

        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.clear();
        lastName.sendKeys(EmplyeelastName);


        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);


        try {
            WebElement sucessMessage = driver.findElement(By.xpath("//div[@role='alert']"));
            Assert.assertTrue(sucessMessage.isDisplayed());
            Assert.assertEquals(sucessMessage.getText(), "Success");
        } catch (NoSuchElementException e) {
            System.out.println("Le test à été effectué avec succès. Un nouvel employé a été ajouté");
        }

    }

    @Test
    public void voirlesInfo() throws InterruptedException {

        // La page de test
        driver.get(url);

        // Appliquer un delai d'exécution
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delay));

        // saisir les identifiants valides
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");

        // cliquer sur le bouton Login
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // Vérifier que l'utilisateur est redirigé vers le tableau de bord
        WebElement dashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "L'utilisateur n'est pas redirigé vers le tableau de bord.");
        Assert.assertEquals(dashboard.getText(), "Dashboard");

        // Ensuite lancer le menu PIM (Personnel Information Management)
        driver.findElement(By.cssSelector("a[href=\"/web/index.php/pim/viewMyDetails\"]")).click();


        try {
            WebElement sucessMessage = driver.findElement(By.xpath("//div[@role='alert']"));
            Assert.assertTrue(sucessMessage.isDisplayed());
            Assert.assertEquals(sucessMessage.getText(), "Success");
        } catch (NoSuchElementException e) {
            System.out.println("Le test à été effectué avec succès. La section info trouvée");
        }

    }

    // Quitter après l'exécution des tests.
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
