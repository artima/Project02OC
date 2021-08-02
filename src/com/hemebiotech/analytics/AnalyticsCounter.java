package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {

	public static void main(String[] args) throws Exception {
		// first get input
		try {
			//Read symptoms' file
			BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"));
			// Extract the first line of the file
			String line = reader.readLine();
			// Create TreeMap object to arrange data in alphabetical order
			Map<String, Integer> symptomName = new TreeMap<>();

			// We continue the process until the last line
			while (line != null) {
				if (!line.isEmpty()) {
					if (symptomName.containsKey(line)) {
						symptomName.put(line, symptomName.get(line) + 1);
					} else {
						symptomName.put(line, 1);
					}
				}
				line = reader.readLine();    // get another symptom
			}

			System.out.println("There are " + symptomName.size() + " symptoms in the file");
			for (Map.Entry<String, Integer> pair : symptomName.entrySet()) {
				System.out.println("Number of symptom \"" + pair.getKey() + "\" : " + pair.getValue());
			}

			// next generate output
			FileWriter writer = new FileWriter("result.out");
			for (Map.Entry<String, Integer> pair : symptomName.entrySet()) {
				writer.write(pair.getKey() + " = " + pair.getValue() + "\n");
			}
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}
}
