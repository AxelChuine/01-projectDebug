package com.hemebiotech.analytics;

import java.util.List;

/**
 * Anything that will read symptom data from a source
 * The important part is, the return value from the operation, which is a list of strings,
 * that may contain many duplications
 * 
 * The implementation does not need to order the list
 * 
 */
public interface ISymptomReaderWriter {
	/**
	 * If no data is available, return an empty List
	 * 
	 * @return a raw listing of all Symptoms obtained from a data source, duplicates are possible/probable
	 */
	public void GetSymptoms ();
	
	/**
	 * @type void
	 * method that fills an arraylist with the symptoms of the file symptoms.txt
	 */
	public void countSymptoms();
	
	/**
	 * @type void
	 * method that eliminates duplicate symptoms.
	 */
	public void eliminateDuplicate();
	
	/**
	 * @type void
	 * method that write in a file of our choosing
	 */
	public void writeInFile();
}
