package couk.nucmedone.massivecyril.ui.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import jfxtras.scene.control.LocalTimePicker;

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
	
	protected void addField(Collection<Control[]> fields, int startCol){
		
		Iterator<Control[]> it = fields.iterator();
		
		while(it.hasNext()){
			Control control[] = it.next();
			grid.add(control[0], startCol++, ++row);
			grid.add(control[1], startCol++, row);
			grid.add(control[2], startCol, row);
		}
		
	}
	
	protected DatePicker addDateField(String name){
		
		DatePicker dp = new DatePicker();
		addField(name, dp);
		return dp;
		
	}
	
	protected FloatTextField addFloatField(String name){
		
		FloatTextField ftf = new FloatTextField();
		addField(name, ftf);
		return ftf;
		
	}
	
	protected TextField addTextField(String name){
		
		TextField tf = new TextField();
		addField(name, tf);
		return tf;
		
	}
	
	protected LocalTimePicker addTimeField(String name){
		
		LocalTimePicker tp = new LocalTimePicker();
		addField(name, tp);
		return tp;
		
	}

	
	public GridPane getForm(){
		return grid;
	}

	protected abstract void init();
}
