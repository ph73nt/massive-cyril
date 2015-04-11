package couk.nucmedone.massivecyril.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import couk.nucmedone.massivecyril.ui.forms.DemogForm;
import couk.nucmedone.massivecyril.ui.forms.InjectionForm;
import couk.nucmedone.massivecyril.ui.forms.StockForm;
import couk.nucmedone.massivecyril.ui.forms.listeners.InjectNextListener;
import couk.nucmedone.massivecyril.ui.forms.listeners.StockNextListener;

public class UI extends Application implements StockNextListener, InjectNextListener {

	private GridPane grid;
	private final GFRControl gfrc;
	private final DemogForm demogForm;
	private final StockForm stockform;
	private final InjectionForm injectionForm;
	private Stage stage;

	public UI() {
		super();
		
		// Initialise a control intermediary for GFRs 
		gfrc = new GFRControl();
		
		demogForm = new DemogForm();
		demogForm.addPatientListener(gfrc);
		grid = demogForm.getForm();
		
		stockform = new StockForm();
		stockform.addPhialListener(gfrc);
		stockform.addNextButtonListener(this);
		grid = stockform.getForm();
		
		injectionForm = new InjectionForm();
		injectionForm.addInjectionListener(gfrc);
		
	}

	@Override
	public void start(Stage primaryStage) {
						
		Scene scene = new Scene(grid, 800, 600);
		stage = primaryStage;
		stage.setScene(scene);
		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void InjectNextPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StockNextPressed() {
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				grid = injectionForm.getForm();
				Scene scene = new Scene(grid, 800, 600);
				stage.setScene(scene);
			}
			
		});
	}
}
