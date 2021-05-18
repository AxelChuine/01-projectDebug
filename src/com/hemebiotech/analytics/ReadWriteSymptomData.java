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
	private Integer counter;
	private List<String> symptoms;
	private List<String> symptomsDuplicate;
	private LinkedHashMap<String, Integer> symptomsCounter;
	private File resultOut;
	/**
	 * 
	 * @param inputFileName a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadWriteSymptomData (String inputFileName, String outputFileName) {
		this.line = "";
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.counter = 0;
		this.symptoms = new ArrayList<String>();
		this.symptomsDuplicate = new ArrayList<String>();
		this.symptomsCounter = new LinkedHashMap<>();
		this.resultOut = new File(this.outputFileName);
	}
	
	@Override
	public void getSymptoms() {
		this.symptoms = new ArrayList<String>();
		
		if (inputFileName != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(inputFileName));
				this.line = reader.readLine();
				
				while (this.line != null) {
					if(!this.symptoms.contains(this.line)) {
						this.symptomsCounter.put(this.line, 0);
						this.symptoms.add(this.line);
					}
					this.symptomsCounter.put(this.line, this.symptomsCounter.get(this.line)+1);
					this.line = reader.readLine();
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
