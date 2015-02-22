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

	private final DatePicker dob = new DatePicker();
	private final TextField nameField = new TextField();
	private final TextField idField = new TextField();
	private final TextField oidField = new TextField();
	private final TextField heightField = new TextField();
	private final TextField weightField = new TextField();

	public DemogForm(){
		super();
	}

	@Override
	protected void init() {
	
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
