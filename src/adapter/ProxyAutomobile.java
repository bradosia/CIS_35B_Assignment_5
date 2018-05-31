package adapter;

import model.*;
import exception.*;

public abstract class ProxyAutomobile {
	private static model.AutomobileTable automobileTable;
	private util.FileIO autoutil;

	protected ProxyAutomobile() {
		autoutil = new util.FileIO();
	}

	public void init() {
		// initialize the static automobile table
		automobileTable = new AutomobileTable(64);
	}

	public boolean updateOptionSetName(String automobileKey, String optionSetName, String nameNew) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			automobileObject.setOptionSetName(optionSetName, nameNew);
			returnValue = true;
		}
		return returnValue;
	}

	public boolean updateOptionPrice(String automobileKey, String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			automobileObject.setOptionSetOptionPrice(optionSetName, optionName, priceNew);
			returnValue = true;
		}
		return returnValue;
	}

	/* CreateAuto Implementation */
	public String buildAuto(String filename) {
		String returnValue = null;
		model.Automobile automobileObject = new model.Automobile();
		try {
			autoutil.read("FordZTW.txt", automobileObject);
			returnValue = automobileTable.insertWrapper(automobileObject);
		} catch (exception.AutoException e) {
			// double check that return value is null
			returnValue = null;
		}
		return returnValue;
	}

	public boolean printAuto(String automobileKey) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			returnValue = true;
			System.out.println(automobileObject.toString());
		}
		return returnValue;
	}

	public boolean serialize(String automobileKey, String fileName) {
		boolean returnValue = false;
		model.Automobile automobileObject;
		try {
			automobileObject = automobileTable.getByKey(automobileKey);
			autoutil.serialize(fileName, automobileObject);
			returnValue = true;
		} catch (exception.AutoException e) {
			// nothing
		}
		if (returnValue) {
			System.out.println("Serialized data is saved in " + fileName);
		} else {
			System.out.println("Automobile could not be serialized");
		}
		return returnValue;
	}

	public String deserialize(String fileName) {
		String returnValue = null;
		model.Automobile automobileObject = autoutil.deserialize(fileName);
		if (automobileObject != null) {
			System.out.println("Deserialized data read from " + fileName);
			returnValue = automobileTable.insertWrapper(automobileObject);
		} else {
			System.out.println("Automobile could not be deserialized");
		}
		return returnValue;
	}

	/* AutoChoice Implementation */
	public boolean setOptionChoice(String automobileKey, String optionSetName, String optionName) {
		boolean returnValue = false;
		model.Automobile automobileObject;
		automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			automobileObject.setOptionSetChoice(optionSetName, optionName);
			returnValue = true;
		}
		return returnValue;
	}

	public String getOptionChoice(String automobileKey, String optionSetName) {
		String returnValue = null;
		model.Automobile automobileObject;
		automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			returnValue = automobileObject.getOptionSetChoiceName(optionSetName);
		}
		return returnValue;
	}

	public Double getOptionChoicePrice(String automobileKey, String optionSetName) {
		Double returnValue = null;
		model.Automobile automobileObject;
		automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			returnValue = automobileObject.getOptionSetChoicePrice(optionSetName);
		}
		return returnValue;
	}
	
	public void Operation(int opnumber, String[] input) {
		// call some method in EditOption class
	}
	
	/*
	 * test
	 */
	//sync
	UpdateOption(operationno, threadno, auto, optionset, fromcolor, tocolor)
	// not sync
	UpdateOption(operationno, threadno, auto, optionset, fromcolor, tocolor)
	// Scalable scaleable = new BuildAuto();
	String input[] = {1,1,"FordWagonZTW","Color","Blue","HawaiianGold"}
	scaleable.operation(1, input);
	// another thread
	String input[] = {1,2,"FordWagonZTW","Color","Blue","CaliforniaGold"}
	scaleable.operation(1, input);
	
	//Lot of print statements should be added in relevant method of auto, optionset, nd option and its okay to do so for lab4
	
	// which method should be synchronized in lab4?
	1. model package? - significant performance repercusion?
		2. proxyauto - design wise not localized
		3. editoptions - opmethods that call other methods in the model package
		
		// how to use Hello.java in EditOption.java
		
		// Coffee is your auto
	
}