package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class AnalyticsCounter {	
	public static void main(String args[]) throws Exception {
		ISymptomReaderWriter test = new ReadWriteSymptomData("symptoms.txt", "result.out");
		test.getSymptoms();
		test.writeInFile();
	}
}
