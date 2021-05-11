package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (inputFileName != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(inputFileName));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
