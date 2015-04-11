package couk.nucmedone.massivecyril.ui;

import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientListener;
import couk.nucmedone.common.stock.Phial;
import couk.nucmedone.common.stock.PhialListener;
import couk.nucmedone.massivecyril.labtest.Injection;
import couk.nucmedone.massivecyril.labtest.InjectionListener;

public class GFRControl implements InjectionListener, PatientListener, PhialListener {

	private Injection injection;
	private Patient patient;
	private Phial phial;
	
	public GFRControl(){
		
	}
	
	public void addPatient(Patient patient){
		this.patient = patient;
	}

	@Override
	public void injectionUpdate(Injection injection) {
		
		this.injection = injection;
		
	}

	@Override
	public void patientUpdate(Patient patient) {
		
		if(this.patient == null){
			addPatient(patient);
		}
		
	}

	@Override
	public void phialUpdate(Phial phial) {
		
		this.phial = phial;
		
	}
	
}
