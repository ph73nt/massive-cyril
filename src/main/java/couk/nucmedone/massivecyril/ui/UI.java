package couk.nucmedone.massivecyril.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import couk.nucmedone.massivecyril.ui.forms.DemogForm;

public class UI extends Application {

	private GridPane grid;

	public UI() {
		super();
		grid = (new DemogForm()).getForm();
	}

	@Override
	public void start(Stage primaryStage) {
						
		Scene scene = new Scene(grid, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
