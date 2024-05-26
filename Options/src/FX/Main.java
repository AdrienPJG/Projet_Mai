package FX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import Classes.*;
import BDD.*;

import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;

public class Main extends Application {
	
	public static boolean activation =false;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Connexion");

            // Chargement des étudiants et des options depuis les fichiers JSON
            List<Etudiant> etudiants = ChargerEtudiant.chargerEtudiantsDepuisJSON("etudiants.json");
            if (etudiants == null) {
                System.out.println("Erreur : Les étudiants n'ont pas pu être chargés."); // Message de débogage
                return; // Arrête l'exécution si les étudiants ne sont pas chargés
            }
            
            AuthentificationEtudiant authEtudiant = new AuthentificationEtudiant(etudiants);
            
            List<Option> options = RecupererOption.recupererOptionsDepuisJSON("options.json");
            if (options == null) {
                System.out.println("Erreur : Les options n'ont pas pu être chargées.");
                return;
            }

            // Création des boutons pour choisir entre étudiant et responsable
            Button etudiantButton = new Button("Etudiant");
            Button responsableButton = new Button("Responsable");

            // Création d'un conteneur pour les boutons
            GridPane buttonPane = new GridPane();
            buttonPane.setPadding(new Insets(20));
            buttonPane.setHgap(10);
            buttonPane.setVgap(10);
            buttonPane.add(etudiantButton, 0, 0);
            buttonPane.add(responsableButton, 1, 0);

            // Gestion des actions lorsque l'utilisateur clique sur les boutons
            etudiantButton.setOnAction(e -> {
                afficherInterfaceEtudiant(primaryStage, authEtudiant, options);
            });

            responsableButton.setOnAction(e -> {
                afficherInterfaceResponsable(primaryStage, options, etudiants);
            });

            // Création de la scène principale avec les boutons de choix
            Scene scene = new Scene(buttonPane, 400, 100);

            URL cssURL = getClass().getClassLoader().getResource("styles.css");
            if (cssURL == null) {
                System.out.println("Erreur : Fichier CSS non trouvé");
                return;
            }
            String css = cssURL.toExternalForm();
            scene.getStylesheets().add(css); // Ajout du fichier CSS à la scène

            // Affichage de la scène de connexion
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void afficherInterfaceEtudiant(Stage primaryStage, AuthentificationEtudiant authEtudiant, List<Option> options) {
        try {
            // Création de champs et boutons pour l'authentification de l'étudiant
            GridPane authPane = new GridPane();
            authPane.setPadding(new Insets(20));
            authPane.setHgap(10);
            authPane.setVgap(10);

            TextField emailField = new TextField();
            emailField.setPromptText("Email");
            emailField.getStyleClass().add("text-field");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Mot de passe");
            passwordField.getStyleClass().add("text-field");

            Button loginButton = new Button("Se connecter");
            loginButton.getStyleClass().add("button");

            Label feedbackLabel = new Label();

            loginButton.setOnAction(e -> {
                String email = emailField.getText();
                String password = passwordField.getText();
                Etudiant etudiant = authEtudiant.authentifier(email, password);

                if (etudiant != null) {
                    feedbackLabel.setText("Authentification réussie pour " + etudiant.getNom() + " " + etudiant.getPrenom());
                    EtudiantController etudiantController = new EtudiantController(etudiant);
                    EtudiantInterface etudiantInterface = new EtudiantInterface(authEtudiant, new EtudiantController(etudiant), primaryStage,options,etudiant,activation);
                    
                    Scene etudiantScene = new Scene(etudiantInterface.getRootPane(), 800, 600);
                    URL cssURL = getClass().getClassLoader().getResource("styles.css");
                    if (cssURL == null) {
                        System.out.println("Erreur : Fichier CSS non trouvé");
                        return;
                    }
                    String css = cssURL.toExternalForm();
                    etudiantScene.getStylesheets().add(css); // Ajout du fichier CSS à la scène
                    primaryStage.setScene(etudiantScene);
                } else {
                    feedbackLabel.setText("Authentification échouée. Veuillez réessayer.");
                }
            });

            authPane.add(new Label("Email:"), 0, 0);
            authPane.add(emailField, 1, 0);
            authPane.add(new Label("Mot de passe:"), 0, 1);
            authPane.add(passwordField, 1, 1);
            authPane.add(loginButton, 1, 2);
            authPane.add(feedbackLabel, 1, 3);

            Scene authScene = new Scene(authPane, 400, 200);
            URL cssURL = getClass().getClassLoader().getResource("styles.css");
            if (cssURL == null) {
                System.out.println("Erreur : Fichier CSS non trouvé");
                return;
            }
            String css = cssURL.toExternalForm();
            authScene.getStylesheets().add(css); // Ajout du fichier CSS à la scène
            primaryStage.setScene(authScene);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void afficherInterfaceResponsable(Stage primaryStage,List<Option> options, List<Etudiant> etudiants) {
        try {
            // Création de l'interface responsable (simulé)
            ResponsableOrientation responsable = new ResponsableOrientation("Alice", "Smith", "email2@example.com", "5678",etudiants ,options, "pass456");
            AuthentificationResponsable authResponsable = new AuthentificationResponsable(responsable);
            ResponsableInterface responsableInterface = new ResponsableInterface(authResponsable, new ResponsableController(responsable), primaryStage,responsable.getOption(),responsable.getEtudiants(),activation);

            Scene scene = new Scene(responsableInterface.getRootPane(), 800, 600);
            URL cssURL = getClass().getClassLoader().getResource("styles.css");
            if (cssURL == null) {
                System.out.println("Erreur : Fichier CSS non trouvé");
                return;
            }
            String css = cssURL.toExternalForm();
            scene.getStylesheets().add(css); // Ajout du fichier CSS à la scène
            
            primaryStage.setScene(scene);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
