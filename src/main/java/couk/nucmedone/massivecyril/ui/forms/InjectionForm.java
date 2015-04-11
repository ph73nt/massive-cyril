package couk.nucmedone.massivecyril.ui.forms;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import jfxtras.scene.control.LocalTimePicker;
import couk.nucmedone.massivecyril.labtest.Injection;
import couk.nucmedone.massivecyril.labtest.InjectionListener;

public class InjectionForm extends Form {

	private FloatTextField bkgCounts;
	private FloatTextField empty;
	private FloatTextField full;
	private DatePicker injDate;
	private LocalTimePicker injTime;
	private InjectionListener listener;
	private FloatTextField postAssay;
	private FloatTextField preAssay;

	@Override
	protected void init() {

		empty = addFloatField("Empty syringe weight (g)");
		full = addFloatField("Full syringe weight (g)");
		bkgCounts = addFloatField("Background counts");
		preAssay = addFloatField("Pre-injection assay");
		injDate = addDateField("Injection date");
		injTime = addTimeField("Injection Time");
		postAssay = addFloatField("Post-injection assay");
		addButton(nextButton());				
		
	}
	
	private Button nextButton(){
		
		Button nxtBtn = new Button("Next");
		
		nxtBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				// New injection object to store values
				Injection injection = new Injection();
				
				// Get the date part of the admin time
				LocalDate ld = injDate.getValue();
				Calendar injCal= Calendar.getInstance();
				injCal.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				
				// now the time part
				LocalTime lt = injTime.getLocalTime();
				injCal.set(Calendar.HOUR_OF_DAY, lt.getHour());
				injCal.set(Calendar.MINUTE, lt.getMinute());
				
				// Set time on the calendar object
				injection.setAdminTime(injCal);
				
				// Assay stuff
				injection.setBackgroundCounts(bkgCounts.getValue());
				injection.setPreCounts(preAssay.getValue());
				injection.setPostCounts(postAssay.getValue());
				
				//TODO: Use pre-definable method to set systematic error estimate on the weights
				final double emptyWeight = empty.getValue();
				injection.setEmptyWeight(emptyWeight, emptyWeight/1000);
				
				final double fullWeight = full.getValue();
				injection.setFullWeight(fullWeight, fullWeight/1000);

				//Inform injection listener of update
				listener.injectionUpdate(injection);
				
				//TODO: check standards match... or are new
				// TODO: Set enable/disable next button with field validation
				
			}
		});
		
		return nxtBtn;
		
	}

	public void addInjectionListener(InjectionListener listener) {
		
		this.listener = listener;
		
	}
			
}
