package org.projetFinalFormationSeleniumQalilab;

public class TextConstants {
    // URLs
    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

    // Textes d'authentification
    public static final String USERNAME = "Admin";
    public static final String PASSWORD = "admin123";
    public static final String FALSE_USERNAME = "Thibaut";
    public static final String FALSE_PASSWORD = "KOUAME";
    public static final String INVALID_CREDENTIALS_MESSAGE = "Identifiants incorrects";
    public static final String DASHBOARD_TEXT = "Dashboard";

    // Identifiant de l'employé
    public static final String EMPLOYEE_FIRST_NAME = "Bosson Badou Thibaut";
    public static final String EMPLOYEE_LAST_NAME = "Kouame";
    public static final String EMPLOYEE_MIDDLE_NAME = "";

    // Messages
    public static final String SUCCESS_MESSAGE = "Success";
    public static final String DASHBOARD_REDIRECT_MESSAGE = "L'utilisateur est bien redirigé vers le tableau de bord. Car %s a été trouvé.";
    public static final String DASHBOARD_NOT_FOUND_MESSAGE = "L'utilisateur n'a pas été redirigé vers le tableau de bord, car dashboard n'a pas été trouvé";
    public static final String EMPLOYEE_ADDED_MESSAGE = "Le test à été effectué avec succès. Un nouvel employé a été ajouté";
    public static final String INFO_SECTION_FOUND_MESSAGE = "Le test à été effectué avec succès. La section info trouvée";
    public static final String EMPLLOYEE_ADDED_MESSAGE = "Le test à été effectué avec succès. Un nouvel employé a été ajouté";
    
    // Délais
    public static final int IMPLICIT_WAIT_SECONDS = 5;
}
