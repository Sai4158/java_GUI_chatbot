package chatbot.infra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import chatbot.component.DomainClassifier;
import chatbot.component.WeatherIntentClassifier;
import chatbot.component.FoodIntentClassifier;



public class Chatbot {
	
	private String userName = "Sai";
	private String botName = "Alexa";
	
	private DomainClassifier nowDomainClassifier;
    private WeatherIntentClassifier weatherIntentClassifier;
    private FoodIntentClassifier foodIntentClassifier;
    
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
	
		// Initialize domain and intent classifiers
        this.nowDomainClassifier = new DomainClassifier();
        this.weatherIntentClassifier = new WeatherIntentClassifier();
        this.foodIntentClassifier = new FoodIntentClassifier();
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
    public String getResponse(String nowInputText) {
        System.out.println("--------------");
        System.out.println("User Message: " + nowInputText);
        
        String nowDomain = nowDomainClassifier.getLabel(nowInputText);
        System.out.println("Domain: " + nowDomain);
        
        String nowIntent = "";
        
        if (!nowDomain.equals("Other")) { // In-domain message
            if (nowDomain.equals("Food")) { // Food domain
                nowIntent = foodIntentClassifier.getLabel(nowInputText);
            } else if (nowDomain.equals("Weather")) { // Weather domain
                nowIntent = weatherIntentClassifier.getLabel(nowInputText);
            } else { // This shouldn't happen
                System.err.println("Domain name is incorrect!");
                System.exit(1);
                return null;
            }
        } else { // Out-of-domain message
            return "This message is out of the domains of the chatbot.";
        }
        
        System.out.println("Intent: " + nowIntent);
        String nowResponse = "Domain = " + nowDomain + "; Intent = " + nowIntent;
        
        System.out.println(botName + "'s Response: " + nowResponse);
        System.out.println("--------------");
        return nowResponse;
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