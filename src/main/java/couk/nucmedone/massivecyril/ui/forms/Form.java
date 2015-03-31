package couk.nucmedone.massivecyril.ui.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public abstract class Form {

	protected GridPane grid;
	protected int row;
	protected int cols = 3;
	protected ArrayList<Control> fields = new ArrayList<Control>();

	
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
	
		// Keep track of fields for null/empty checking
		fields.add(field);
	}
	
	protected void addField(Collection<Control> fields, int startCol){
		
		row++;
		
		Iterator<Control> it = fields.iterator();
		
		while(it.hasNext()){
			Control control = it.next();
			grid.add(control, startCol++, row);
		}
		
	}
	
	public GridPane getForm(){
		return grid;
	}

	protected abstract void init();
}
