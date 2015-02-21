package couk.nucmedone.massivecyril.ui.forms;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public abstract class Form {

	protected GridPane grid;
	protected int row;
	protected int cols = 3;
	
	public Form() {
		
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		ColumnConstraints cc = new ColumnConstraints();
		cc.setHalignment(HPos.RIGHT);
		grid.getColumnConstraints().add(cc);
		grid.getColumnConstraints().add(cc);
		
		init();
		
	}

	protected void addButton(Button button){
//		HBox hbox = new HBox(button);
//		hbox.setAlignment(Pos.CENTER_RIGHT);
		grid.add(button, 1, ++row);
	}
	
	protected void addField(String label, Control field){
		addField(label, field, cols);
	}

	protected void addField(String label, Control field, int colSpan) {
		
		Label theLabel = new Label(label);
		grid.add(theLabel, 0, ++row);
		grid.add(field, 1, row, colSpan, 1);
	
	}
	
	public GridPane getForm(){
		return grid;
	}

	protected abstract void init();
}
