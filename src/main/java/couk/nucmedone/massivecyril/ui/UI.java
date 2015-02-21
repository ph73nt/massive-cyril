package couk.nucmedone.massivecyril.ui;

import couk.nucmedone.massivecyril.ui.forms.DemogForm;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UI extends Application {

	private GridPane grid;
//	private int row;

	public UI() {
		super();
		grid = (new DemogForm()).getForm();
//		row = 0;
	}

	@Override
	public void start(Stage primaryStage) {
		
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25, 25, 25, 25));
//		
//		Text title = new Text("Details");
//		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//		grid.add(title, 0, row, 2, 1);
//		
//		TextField nameField = new TextField();
//		addDemog("Name:", nameField);
//
//		TextField idField = new TextField();
//		addDemog("Identifier:", idField);
//	
//		TextField oidField = new TextField();
//		addDemog("Secondary IDs (ID1, ID2, ID3...)", oidField);
//
//		DatePicker dob = new DatePicker();
//		dob.setShowWeekNumbers(false);
//		addDemog("DOB (dd/mm/yyyy):", dob);
				
		Scene scene = new Scene(grid, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

//	private void addDemog(String label, Control field){
//		addDemog(label, field, 3);
//	}
//
//	private void addDemog(String label, Control field, int colSpan) {
//		
//		Label theLabel = new Label(label);
//		grid.add(theLabel, 0, ++row);
//		grid.add(field, 1, row, colSpan, 1);
//	
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
