package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

	private String line;
	private String inputFileName;
	private String outputFileName;
	private List<String> symptoms;
	private List<String> symptomsDuplicate;
	private LinkedHashMap<String, Integer> symptomsCounter;
	private File resultOut;
	private BufferedWriter bw;
	/**
	 * 
	 * @param inputFileName a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadWriteSymptomData (String inputFileName, String outputFileName) {
		this.line = "";
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.symptoms = new ArrayList<String>();
		this.symptomsDuplicate = new ArrayList<String>();
		this.symptomsCounter = new LinkedHashMap<>();
		this.resultOut = new File(this.outputFileName);
	}
	
	@Override
	public void GetSymptoms() {
		this.symptomsDuplicate = new ArrayList<String>();
		
		if (inputFileName != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(inputFileName));
				String line = reader.readLine();
				
				while (line != null) {
					this.symptomsDuplicate.add(line);
					line = reader.readLine();
				}
				this.symptomsDuplicate.sort(Comparator.comparing(String::toString));
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void countSymptoms() {
		Integer vCount = 0;
		for(int i = 0; i < this.symptomsDuplicate.size(); i++) {
			if(!this.symptomsCounter.containsKey(this.symptomsDuplicate.get(i))) {
				vCount = 1;
				this.symptomsCounter.put(this.symptomsDuplicate.get(i), vCount);
			}
			this.symptomsCounter.put(this.symptomsDuplicate.get(i), vCount++);
		}
	}

	@Override
	public void eliminateDuplicate() {
		for(String key : this.symptomsDuplicate) {
			if(!this.symptoms.contains(key)) {
				this.symptoms.add(key);
			}
		}
	}

	@Override
	public void writeInFile() {
		try {
			this.bw = new BufferedWriter(new FileWriter(resultOut));
			for(String key : this.symptoms) {
				this.bw.write(key+" = "+this.symptomsCounter.get(key));
				this.bw.newLine();
			}
			this.bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
