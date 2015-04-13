package couk.nucmedone.massivecyril.ui.forms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.TableView;

public class CountFileViewer extends Form {

	@Override
	protected void init() {

		String fn = "/home/neil/Documents/NucMedOne/Counter Intelligence/Example Data/V24/audit24/AUDT_R1.T";
		File file = new File(fn);
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lines = new ArrayList<String>();

		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);

			// Read line by line
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
				System.out.println(line);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// Now have a collection of lines to add to a table
		
		// Get number of columns
		Iterator<String> it = lines.iterator();
		String line;
		int len = 0;
		while (it.hasNext()){
			line = it.next();
			final int lineLength = line.length();
			len = lineLength > len ? lineLength : len;
		}
		
		TableView tv = new TableView<>();
		

	}

}
