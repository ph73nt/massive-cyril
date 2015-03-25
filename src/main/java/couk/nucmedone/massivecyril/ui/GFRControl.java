package couk.nucmedone.massivecyril.ui;

import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientListener;

public class GFRControl implements PatientListener {

	private Patient patient;
	
	public GFRControl(){
		
	}
	
	public void addPatient(Patient patient){
		this.patient = patient;
	}

	@Override
	public void patientUpdate(Patient patient) {
		
		if(this.patient == null){
			addPatient(patient);
		}
		
	}
	
}
