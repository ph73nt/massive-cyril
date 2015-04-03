package couk.nucmedone.massivecyril.ui.forms;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalTimePicker;

public class InjectionForm extends Form {

	private TextField batch;
	private TextField lot;
	private DatePicker refDate;
	private FloatTextField empty;
	private FloatTextField full;
	private FloatTextField preAssay;
	private LocalTimePicker injTime;
	private FloatTextField postAssay;

	@Override
	protected void init() {

		batch = addTextField("Batch");
		lot = addTextField("Lot");
		refDate = addDateField("Reference date");
		empty = addFloatField("Empty syringe weight (g)");
		full = addFloatField("Full syringe weight (g)");
		preAssay = addFloatField("Pre-injection assay");
		injTime = addTimeField("Injection Time");
		postAssay = addFloatField("Post-injection assay");
						
	}
			
}
