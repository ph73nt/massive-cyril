package couk.nucmedone.massivecyril.ui.forms;

import java.time.LocalDate;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.nuclides.Nuclides;
import couk.nucmedone.common.stock.Phial;
import couk.nucmedone.common.stock.PhialListener;
import couk.nucmedone.massivecyril.ui.forms.listeners.StockNextListener;

public class StockForm extends Form {

	private String batch;
	private TextField batchField;
	private Calendar expDate;
	private DatePicker expDatePicker;
	private DoublePlus initVol;
	private FloatTextField initVolField;
	private String lot;
	private TextField lotField;
	private String manufacturer;
	private TextField manField;
	private Nuclides nuclide;
	private String pharmaceutical;
	private TextField pharmField;
	private double refActivity;
	private FloatTextField refActField;
	private Calendar refDate;
	private DatePicker refDatePicker;
	
	private PhialListener pl;
	private StockNextListener nl;
	
	public void addPhialListener(PhialListener listener) {
		pl = listener;
	}
	
	public void addNextButtonListener(StockNextListener listener){
		nl = listener;
	}
	
	@Override
	protected void init() {
		
		batchField = addTextField("batch number");
		lotField = addTextField("Lot number");
		manField = addTextField("Manufacturer");
		pharmField = addTextField("Pharmaceutical");
		expDatePicker = addDateField("Expiry date");
		initVolField = addFloatField("Initial volume (ml)");
		refActField = addFloatField("Initial activity");
		refDatePicker = addDateField("Reference date");
		addButton(nextButton());	

	}

	private Button nextButton() {
		
		Button nxtBtn = new Button("Next");
		
		nxtBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Phial phial = new Phial();
				
				batch = batchField.getText();
				phial.setBatch(batch);
				
				lot = lotField.getText();
				phial.setLot(lot);
				
				manufacturer = manField.getText();
				phial.setManufacturer(manufacturer);
				
				pharmaceutical = pharmField.getText();
				phial.setPharmaceutical(pharmaceutical);
				
				initVol = new DoublePlus(initVolField.getValue(), 0.001);
				phial.setInitVol(initVol);
				
				refActivity = refActField.getValue();
				phial.setRefActivity(refActivity);
				
				// Get the expiry date
				LocalDate ld = expDatePicker.getValue();
				Calendar expCal= Calendar.getInstance();
				expCal.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				
				// Get activity reference date
				ld = refDatePicker.getValue();
				Calendar refCal = Calendar.getInstance();
				refCal.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				
				nl.StockNextPressed();
				
			}
			
		});
		
		return nxtBtn;
	}

}
