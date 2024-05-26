package FX;

import Classes.*;
import FX.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class EtudiantInterface {

    private Etudiant etudiant;
    private Pane rootPane;
    private EtudiantController etudiantController;
    private Stage primaryStage;
    private List<Option> options;
    private VBox choixPrefPane;// Liste pour stocker les options charg�es depuis la base de donn�es
    private boolean activation;

    public EtudiantInterface(AuthentificationEtudiant AuthEtu,EtudiantController etudiantController, Stage primaryStage, List<Option> options,Etudiant etudiant, boolean activation) {
        this.etudiant = etudiant;
        this.etudiantController = etudiantController;
        this.primaryStage = primaryStage;
        this.options = options;
        this.activation = activation;
        createContent();
    }
    
    private static int counter=1;
    
    private void createContent() {
        rootPane = new GridPane();
        rootPane.setPadding(new Insets(20));

        Label moyenneLabel = new Label("Moyenne: "+ etudiantController.visualiserMoyenne()+"  ");
        Label nomLabel = new Label("Nom: " + etudiantController.visualiserNom()+"  ");
        Label PrenomLabel = new Label("Prenom: " + etudiantController.visualiserPrenom());
        
        choixPrefPane = new VBox();
        choixPrefPane.setSpacing(100);
        // Bouton pour visualiser les options
        Button voirOptionsButton = new Button("Voir les options");
        voirOptionsButton.setOnAction(e -> {
            etudiantController.visualiserOptions(options);
        });

        // Bouton pour d�finir les voeux (simulation avec options sp�cifiques pour l'exemple)
        Button trierVoeuxButton = new Button("Trier voeux");
        trierVoeuxButton.setOnAction(event -> {
        	if(activation) {
	            // Logique pour afficher et trier les options des voeux pour l'�tudiant
	            Dialog<String> triDialog = new Dialog<>();
	            triDialog.setTitle("Options � trier");
	            triDialog.getDialogPane().getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
	
	
	            VBox optionsToSortBox = new VBox();
	            optionsToSortBox.setSpacing(10);
	            optionsToSortBox.setId("optionsPane");
	            
	            List<Option> orderedOptions = new ArrayList<>();
	
	            // Ajouter les options associ�es � l'�tudiant pour trier
	            for (Option option : options) {
	                if (option.getFili�re().equals(etudiant.getFili�re())) {
	                    Button optionButton = new Button(option.getNom());
	                    optionButton.getStyleClass().add("optionButton");
	                    Label numLabel = new Label();
	                    numLabel.setStyle("-fx-padding: 0 10 0 0;");
	                    optionButton.setGraphic(numLabel);
	
	                    // Logique pour trier les options
	                    
	                    optionButton.setOnAction(e -> {
	                        if (!orderedOptions.contains(option)) {
	                            orderedOptions.add(option);
	                            optionButton.setText(counter + ". " + option.getNom()); // Ajouter le num�ro � c�t� du nom de l'option
	                            counter++; 
	                        } else {
	                            orderedOptions.remove(option);
	                            optionButton.setText(option.getNom());
	                            counter--;// Remettre le nom de l'option
	                        }
	                    });
	
	                    optionsToSortBox.getChildren().add(optionButton);
	                }
	            }
	            Button validerVoeuxButton = new Button("Valider");
	            validerVoeuxButton.setId("validerVoeuxButton");
	            validerVoeuxButton.setOnAction(e -> {
	            	counter = 1;
	            	List<String> nomOrderedOptions = new ArrayList<>();
	            	nomOrderedOptions = Option.transformerOptionsEnNoms(orderedOptions);
	            	etudiantController.mettreAJourListeVoeu(nomOrderedOptions);
	                System.out.println("Les voeux ont �t� valid�s avec succ�s.");
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Voeux valid�s");
	                alert.setHeaderText(null);
	                alert.setContentText("Les voeux ont �t� valid�s avec succ�s.");
	                alert.showAndWait();
	                
	                Stage stage = (Stage) validerVoeuxButton.getScene().getWindow();
	                stage.close();
            });
            
            optionsToSortBox.getChildren().add(validerVoeuxButton);
            triDialog.getDialogPane().setContent(optionsToSortBox);
            triDialog.showAndWait().ifPresent(ignored -> {
                System.out.println("Panneau de tri des voeux affich�");
                
            });
            }
            else {
            	System.out.println("La s�l�ction n'a pas �t� activ�");
            }
        });


        // Bouton pour consulter les r�sultats d'orientation
        Button consulterResultatsButton = new Button("Consulter les r�sultats");
        consulterResultatsButton.setOnAction(e -> {
            etudiantController.consulterResultatOrientation();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("R�sultats d'orientation consult�s");
            alert.setHeaderText(null);
            alert.setContentText("Les r�sultats d'orientation ont �t� consult�s avec succ�s.");
            alert.showAndWait();
        });
        
        Button deconnexionButton = new Button("Se d�connecter");
        deconnexionButton.setOnAction(e -> {
            // Cr�e une nouvelle instance de la classe Main pour r�initialiser la vue de connexion
            Main newMain = new Main();
            // Affiche la vue de connexion
            newMain.start(primaryStage);
        });
        
        // Ajout des �l�ments � la grille de la fen�tre
        ((GridPane) rootPane).add(moyenneLabel, 0, 0);
        ((GridPane) rootPane).add(nomLabel, 1, 0);
        ((GridPane) rootPane).add(PrenomLabel, 2, 0);
        ((GridPane) rootPane).add(voirOptionsButton, 0, 1);
        ((GridPane) rootPane).add(trierVoeuxButton,0,2);
        ((GridPane) rootPane).add(consulterResultatsButton, 0, 3);
        ((GridPane) rootPane).add(deconnexionButton, 0, 4);
        
        String css = getClass().getResource("/styles.css").toExternalForm();
        if (css == null) {
            System.out.println("Erreur : Fichier CSS non trouv�");
            return;
        }
        rootPane.getStylesheets().add(css);
    }

    public Pane getRootPane() {
        return rootPane;
    }
}