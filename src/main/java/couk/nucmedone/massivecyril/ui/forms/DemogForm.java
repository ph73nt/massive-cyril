package couk.nucmedone.massivecyril.ui.forms;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DemogForm extends Form {

	public DemogForm(){
		super();
	}

	@Override
	protected void init() {
	
		Text title = new Text("Demographic details");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(title, 0, row, 2, 1);
		
		TextField nameField = new TextField();
		addField("Name:", nameField);

		TextField idField = new TextField();
		addField("Identifier:", idField);
	
		TextField oidField = new TextField();
		addField("Secondary IDs (ID1, ID2, ...):", oidField);

		DatePicker dob = new DatePicker();
		dob.setShowWeekNumbers(false);
		addField("DOB (dd/mm/yyyy):", dob);
		
		TextField heightField = new TextField();
		addField("Height (cm):", heightField);
		
		TextField weightField = new TextField();
		addField("Weight (kg):", weightField);
		
		Button nextBtn = new Button("Next");
		addButton(nextBtn);
					
	}
	
}
