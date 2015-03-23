package couk.nucmedone.massivecyril.ui.forms;

import java.time.LocalDate;
import java.util.Calendar;

import couk.nucmedone.common.patient.Patient;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DemogForm extends Form {
 
	private DatePicker dob;
	private TextField nameField;
	private TextField idField;
	private TextField oidField;
	private TextField heightField;
	private TextField weightField;

	public DemogForm(){
		super();
	}

	@Override
	protected void init() {
	
		dob = new DatePicker();
		nameField = new TextField();
		idField = new TextField();
		oidField = new TextField();
		heightField = new TextField();
		weightField = new TextField();

		Text title = new Text("Demographic details");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(title, 0, row, 2, 1);
		
		dob.setShowWeekNumbers(false);
		
		addField("Name:", nameField);
		addField("Identifier:", idField);
		addField("Secondary IDs (ID1, ID2, ...):", oidField);
		addField("Height (cm):", heightField);
		addField("Weight (kg):", weightField);
		addField("DOB (dd/mm/yyyy):", dob);
		
		Button nextBtn = new Button("Next");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Patient patient = new Patient();
				
				LocalDate ld = dob.getValue();
				Calendar dobc = Calendar.getInstance();
				dobc.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				patient.setDateOfBirth(dobc);
				
				
			}
		});
		addButton(nextBtn);
					
	}
	
}