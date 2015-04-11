package couk.nucmedone.massivecyril.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import couk.nucmedone.massivecyril.ui.forms.DemogForm;
import couk.nucmedone.massivecyril.ui.forms.InjectionForm;
import couk.nucmedone.massivecyril.ui.forms.StockForm;

public class UI extends Application {

	private GridPane grid;
	private final GFRControl gfrc;

	public UI() {
		super();
		
		// Initialise a control intermediary for GFRs 
		gfrc = new GFRControl();
		
		final DemogForm demogForm = new DemogForm();
		demogForm.addPatientListener(gfrc);
		grid = demogForm.getForm();
		
		final StockForm stockform = new StockForm();
		stockform.addPhialListener(gfrc);
		grid = stockform.getForm();
		
		final InjectionForm injectionForm = new InjectionForm();
		injectionForm.addInjectionListener(gfrc);
		grid = injectionForm.getForm();
		
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
