package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Simple brute force implementation
 *
 */
public class ReadWriteSymptomData implements ISymptomReaderWriter {

	private String inputFileName;
	private String outputFileName;
	private List<String> symptoms;
	private LinkedHashMap<String, Integer> symptomsCounter;
	/**
	 * 
	 * @param inputFileName a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadWriteSymptomData (String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.symptoms = new ArrayList<String>();
		this.symptomsCounter = new LinkedHashMap<>();
	}
	
	@Override
	public void getSymptoms() {
		this.symptoms = new ArrayList<String>();
		
		if (inputFileName != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(inputFileName));
				String line = reader.readLine();
				
				while (line != null) {
					if(!this.symptoms.contains(line)) {
						this.symptomsCounter.put(line, 0);
						this.symptoms.add(line);
					}
					this.symptomsCounter.put(line, this.symptomsCounter.get(line)+1);
					line = reader.readLine();
				}
				this.symptoms.sort(Comparator.comparing(String::toString));
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeInFile() {
		try {
			String resultOut = this.outputFileName;
			BufferedWriter bw = new BufferedWriter(new FileWriter(resultOut));
			for(String key : this.symptoms) {
				bw.write(key+" = "+this.symptomsCounter.get(key));
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
