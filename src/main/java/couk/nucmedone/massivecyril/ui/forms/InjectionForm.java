package couk.nucmedone.massivecyril.ui.forms;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalTimePicker;

public class InjectionForm extends Form {

	@Override
	protected void init() {
		
		LocalTimePicker picker = new LocalTimePicker();
		addField("Injection Time", picker);

		FloatTextField vol = new FloatTextField();
		addField("Injection volume", vol);
		
		TextField batch = new TextField();
		addField("Batch", batch);
		
		TextField lot = new TextField();
		addField("Lot", lot);
		
		ArrayList<LocalTimePicker> sampleTimes = new ArrayList<>();
		addTime(sampleTimes);
		
		ArrayList<FloatTextField> emptyWeights = new ArrayList<>();
		addEmptyWeight(emptyWeights);
		
		ArrayList<FloatTextField> fullWeights = new ArrayList<>();
		addFullWeight(fullWeights);

	}

	private void addFullWeight(ArrayList<FloatTextField> fullWeights) {
		
		fullWeights.add(new FloatTextField());
		
	}

	private void addEmptyWeight(ArrayList<FloatTextField> emptyWeights) {
		
		FloatTextField emptyWeight = new FloatTextField();
		emptyWeights.add(emptyWeight);
		
	}

	private void addTime(ArrayList<LocalTimePicker> sampleTimes) {
		
		LocalTimePicker time = new LocalTimePicker();
		sampleTimes.add(time);
		
	}

}
