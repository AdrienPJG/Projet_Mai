package FX;

import Classes.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class ResponsableInterface {

        private AuthentificationResponsable authentificationResponsable;
        private Pane rootPane;
        private	ResponsableController responsableController;
        private Stage primaryStage;
        private List<Option> options;
        private List<Etudiant> etudiants;
        private boolean activation;

        public ResponsableInterface(AuthentificationResponsable authentificationResponsable, ResponsableController responsableController, Stage primaryStage,List<Option> options, List<Etudiant> etudiants, boolean activation) {
            this.authentificationResponsable = authentificationResponsable;
            this.responsableController = responsableController;
            this.primaryStage = primaryStage;
            this.options = options;
            this.etudiants = etudiants;
            this.activation = activation;
            createContent();
        }
        private void createContent() {
	        rootPane = new GridPane();
	        rootPane.setPadding(new Insets(20));
	        ((GridPane) rootPane).setHgap(10);
	        ((GridPane) rootPane).setVgap(10);
	        TextField emailField = new TextField();
	        emailField.setPromptText("Email");
	
	        PasswordField passwordField = new PasswordField();
	        passwordField.setPromptText("Mot de passe");
	
	        Button loginButton = new Button("Se connecter");
	        loginButton.setOnAction(e -> {
	            String email = emailField.getText();
	            String password = passwordField.getText();
	            if (authentificationResponsable.authentifier(email, password)) {
	                // Authentification r�ussie : charger l'interface Barman
	                chargerResponsableInterface();
	            } else {
	                // Authentification �chou�e : afficher un message d'erreur
	                System.out.println("Authentification �chou�e. Veuillez r�essayer.");
	            }
	        });
	
	        ((GridPane) rootPane).add(emailField, 0, 0);
	        ((GridPane) rootPane).add(passwordField, 0, 1);
	        ((GridPane) rootPane).add(loginButton, 0, 2);
	    }

	    private void chargerResponsableInterface() {
	    	rootPane.getChildren().clear();
	    	

	    	Button lancerButton = new Button("Remplir Voeux");
	        lancerButton.setOnAction(e -> {
	        	responsableController.RemplissageAleatoire();
	        });
	        
	        GridPane.setConstraints(lancerButton, 0, 1);
	    	
	    	Button GaleShapleyButton = new Button("Lancer la s�l�ction");
	        GaleShapleyButton.setOnAction(e -> {
	        	Map<String,Option> map = new HashMap<String,Option>();
	        	for (Option option : options) {
	        		map.put(option.getNom(), option);
	            }
	        	GaleShapley galeShapley = new GaleShapley(map,etudiants);
	        	galeShapley.executer();
	        	responsableController.afficherOptionAdmis();
	        });
	        
	        GridPane.setConstraints(GaleShapleyButton, 0, 2);
	      
	        Button deconnexionButton = new Button("Se d�connecter");
	        deconnexionButton.setOnAction(e -> {
	            // Cr�e une nouvelle instance de la classe Main pour r�initialiser la vue de connexion
	            Main newMain = new Main();
	            // Affiche la vue de connexion
	            newMain.start(primaryStage);
	        });
	        
		      
	        Button statsButton = new Button("Statistiques");
	        statsButton.setOnAction(e -> {
	        	responsableController.afficherStatistiques();
	        });
	        
	        GridPane.setConstraints(statsButton, 0, 3);
	        
	        Button activerButton = new Button("Activer la s�l�ction");
	        activerButton.setOnAction(e -> {
	        	Main.activation = true;
	        	System.out.println("La s�l�ction a �t� activ� pour les �tudiants.");
	        });
	        
	        GridPane.setConstraints(activerButton, 0, 4);
	        
	        Button desactiverButton = new Button("Desactiver la s�l�ction");
	        desactiverButton.setOnAction(e -> {
	        	Main.activation = false;
	        	System.out.println("Arr�t de la s�l�ction pour les �tudiants.");
	        });
	        
	        GridPane.setConstraints(desactiverButton, 0, 5);

	        // Ajoute le bouton de d�connexion � l'interface du Client
	        ((GridPane) rootPane).add(lancerButton, 0, 1);
	        ((GridPane) rootPane).add(GaleShapleyButton, 0, 2);
	        ((GridPane) rootPane).add(deconnexionButton, 0, 6);
	        ((GridPane) rootPane).add(statsButton, 0, 3);
	        ((GridPane) rootPane).add(activerButton, 0, 4);
	        ((GridPane) rootPane).add(desactiverButton, 0, 5);
	
	        rootPane.getChildren().addAll(GaleShapleyButton,deconnexionButton);
	    }
	
	    public Pane getRootPane() {
	        return rootPane;
	    }
}