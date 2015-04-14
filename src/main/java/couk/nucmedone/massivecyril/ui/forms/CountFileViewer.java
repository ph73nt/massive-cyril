package couk.nucmedone.massivecyril.ui.forms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CountFileViewer extends Form {

	@Override
	protected void init() {

		TableView tv = new TableView<>();
		
		CountFile bum = new CountFile();
		
		ObservableList<char[]> data = FXCollections.observableArrayList();
		for(int i=0; i<bum.getRowCount(); i++){
			Row row = bum.getRow(i);
			data.add(row.getChars());
		}
		
		for(int i=0; i<bum.getColumnCount(); i++){
			TableColumn col = new TableColumn<>();
		}
		

	}
	
	class Row {
		
		final String rowData;
		
		public Row(String line, int columns){
			
			StringBuffer buffer = new StringBuffer(line);
			
			while(buffer.length() < columns){
				buffer.append(" ");
			}
			
			rowData = buffer.toString();
			
		}
		
		public char[] getChars(){
			return rowData.toCharArray();
		}
		
	}

	class CountFile {
		
		private int cols = 0;
		private int numLines = 0;
		List<String> lines = new ArrayList<String>();

		public CountFile() {
			String fn = "/home/neil/Documents/NucMedOne/Counter Intelligence/Example Data/V24/audit24/AUDT_R1.T";
			File file = new File(fn);
			FileReader fr = null;
			BufferedReader br = null;

			try {

				fr = new FileReader(file);
				br = new BufferedReader(fr);

				// Read line by line
				String line;
				while ((line = br.readLine()) != null) {
					lines.add(line);
					System.out.println(line);
					numLines++;
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
			while (it.hasNext()) {
				line = it.next();
				final int lineLength = line.length();
				cols = lineLength > cols ? lineLength : cols;
			}

		}
		
		public int getColumnCount(){
			return cols;
		}
		
		public Row getRow(int index){
			return new Row(lines.get(index), cols);
		}
		
		public int getRowCount(){
			return numLines;
		}

	}

}
