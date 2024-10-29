package chatbot.infra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import chatbot.component.DomainClassifier;
import chatbot.component.WeatherIntentClassifier;
import chatbot.component.FoodIntentClassifier;
import chatbot.component.SlotFiller;




public class Chatbot {

	private String userName = "Sai";
	private String botName = "Alexa";

	private DomainClassifier nowDomainClassifier;
	private WeatherIntentClassifier weatherIntentClassifier;
	private FoodIntentClassifier foodIntentClassifier;
	private SlotFiller nowSlotFiller;


	public Chatbot(String userName, String botName) {

		this.userName = userName;
		this.botName = botName;

		// Initialize domain and intent classifiers
		this.nowDomainClassifier = new DomainClassifier();
		this.weatherIntentClassifier = new WeatherIntentClassifier();
		this.foodIntentClassifier = new FoodIntentClassifier();
		this.nowSlotFiller = new SlotFiller();

	}

	/*
	 * Task 3: Add a response in Chatbot.java to respond to user message
	 * 
	 * Please modify the getResponse() method in the Chatbot class to respond
	 * to three or more different user messages meaningfully. I provided one
	 * example in the getResponse().
	 */

	/**
	 * Processes user input, identifies domain and intent, and formulates a response.
	 */

	//	 Processes the user input to generate a response based on the detected domain and intent.
	//	 It also extracts relevant slot values from the input text.
	public String getResponse(String nowInputText) {
		System.out.println("--------------");
		System.out.println("User Message: " + nowInputText);

		String nowDomain = nowDomainClassifier.getLabel(nowInputText);
		System.out.println("Domain: " + nowDomain);

		String nowIntent = "";
		Hashtable<String, String> extractedSlotValues = new Hashtable<>();

		if (!nowDomain.equals("Other")) {
			if (nowDomain.equals("Weather")) {
				nowIntent = weatherIntentClassifier.getLabel(nowInputText);
			} else if (nowDomain.equals("Food")) {
				nowIntent = foodIntentClassifier.getLabel(nowInputText);
			}

			// Slot filling
			extractedSlotValues = nowSlotFiller.extractSlotValues(nowInputText);
			String slotsString = slotTableToString(extractedSlotValues);
			String nowResponse = "Domain = " + nowDomain + "; Intent = " + nowIntent + "; " + slotsString;

			System.out.println(botName + "'s Response: " + nowResponse);
			System.out.println("--------------");
			return nowResponse;
		}
		// Return a default message if the input does not match any known domain.
		return "This message is out of the domains of the chatbot.";
	}

	//	  Converts a Hashtable of extracted slot values into a formatted string for easy reading.

	private String slotTableToString(Hashtable<String, String> extractedSlotValues) {
		String result = " (";

		// Iterate through each slot-value pair to construct the output string.
		for (String key : extractedSlotValues.keySet()) {
			String value = extractedSlotValues.get(key);
			result += key + "=" + value + "; ";
		}
		result += ")";
		return result;
	}


	// Getters and setters for userName and botName
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}
}