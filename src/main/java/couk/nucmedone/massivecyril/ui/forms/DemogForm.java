package couk.nucmedone.massivecyril.ui.forms;

import java.time.LocalDate;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientListener;
import couk.nucmedone.common.patient.PatientName;

public class DemogForm extends Form {
 
	private DatePicker dob;
	private TextField familyNameField;
	private TextField givenNameField;
	private TextField middleNameField;
	private TextField prefixField;
	private TextField idField;
	private TextField oidField;
	private TextField heightField;
	private TextField weightField;
	
	private PatientListener patListener;
	
	public DemogForm(){
		super();
	}
	
	public void addPatientListener(PatientListener listener){
		patListener = listener;
	}
	
	@Override
	protected void init() {
	
		dob = new DatePicker();
		familyNameField = new TextField();
		givenNameField = new TextField();
		middleNameField = new TextField();
		prefixField = new TextField();
		idField = new TextField();
		oidField = new TextField();
		heightField = new FloatTextField();
		weightField = new FloatTextField();

		Text title = new Text("Demographic details");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(title, 0, row, 2, 1);
		
		dob.setShowWeekNumbers(false);
		
		addField("Title:", prefixField);
		addField("Given Name:", givenNameField);
		addField("Middle Names", middleNameField);
		addField("Family Name:", familyNameField);
		addField("Identifier:", idField);
		addField("Secondary IDs (ID1, ID2, ...):", oidField);
		addField("Height (cm):", heightField);
		addField("Weight (kg):", weightField);
		addField("DOB (dd/mm/yyyy):", dob);
		
		addButton(nextButton());
					
	}
	
	private Button nextButton(){
		
		Button nextBtn = new Button("Next");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Patient patient = new Patient();
				
				LocalDate ld = dob.getValue();
				Calendar dobc = Calendar.getInstance();
				dobc.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				patient.setDateOfBirth(dobc);
				
				final String height = heightField.getText();
				patient.setHeight(Double.parseDouble(height));
				
				final String weight = weightField.getText();
				patient.setWeight(Double.parseDouble(weight));
				
				final String id = idField.getText();
				patient.setPrimaryID(id);
				
				final String oid = oidField.getText();
				patient.setSecondaryID(oid);
				
				final String family = familyNameField.getText();
				final String given = givenNameField.getText();
				final String middle = middleNameField.getText();
				final String prefix = prefixField.getText();
				PatientName patNam = new PatientName(family, given, middle, prefix, null);
				patient.setPatientName(patNam);
				
				patListener.patientUpdate(patient);
				
			}
		});
		
		return nextBtn;
		
	}
	
}
