package couk.nucmedone.massivecyril.ui.forms;

import javafx.scene.control.TextField;

public class FloatTextField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {

		if (validate(text)) {
			super.replaceText(start, end, text);
		}

	}

	@Override
	public void replaceSelection(String text) {

		if (validate(text)) {
			super.replaceSelection(text);
		}

	}

	private boolean validate(String text) {

		boolean result = false;

		// Can't have mulitple dots!
		if (text.matches("\\.")) {
			
			String txt = getCharacters().toString();
			result = !txt.contains(".");
			
		} else {
			
			result = "".equals(text) || text.matches("[0-9]");
			
		}

		return result;

	}

}
