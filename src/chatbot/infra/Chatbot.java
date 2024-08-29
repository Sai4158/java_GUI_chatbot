package chatbot.infra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Chatbot {
	
	private String userName = "Sai";
	private String botName = "Alexa";
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
	}
	
	/*
	 * Task 3: Add a response in Chatbot.java to respond to user message
	 * 
	 * Please modify the getResponse() method in the Chatbot class to respond
	 * to three or more different user messages meaningfully. I provided one
	 * example in the getResponse().
	 */
	
	public String getResponse(String nowInputText) {
		
//		ADDED A CUSTOM RESPONSE!!
		String nowResponse="Please try again, I don't understand";
		if(nowInputText.toUpperCase().equals("WE ARE")) {
			nowResponse = "Penn State!";
			
//		ADDED CUSTOM STAEMENTS!!
		}if(nowInputText.equalsIgnoreCase("hi")) {
			nowResponse = "Hello!";
		}else if (nowInputText.equalsIgnoreCase("hello")) {
		    nowResponse = "Hii!";
		} 
		else if (nowInputText.equalsIgnoreCase("how are you")) {
		    nowResponse = "I'm doing well, thanks for asking!";
		} 
		
		else if (nowInputText.equalsIgnoreCase("what is your name")) {
		    nowResponse = "My name is TestBot!";
		} 
		
		else if (nowInputText.equalsIgnoreCase("help")) {
		    nowResponse = "How can I assist you today?";
		} 
		
		else if (nowInputText.equalsIgnoreCase("goodbye")) {
		    nowResponse = "Goodbye! Have a great day!";
		} 
		
		else if (nowInputText.equalsIgnoreCase("what can you do")) {
		    nowResponse = "I can chat with you and provide information!";
		} 
		
		else if (nowInputText.equalsIgnoreCase("tell me a joke")) {
		    nowResponse = "Why don't skeletons fight each other? They don't have the guts!";
		} 
		
		else if (nowInputText.equalsIgnoreCase("thank you")) {
		    nowResponse = "You're welcome!";
		}
		else if (nowInputText.equalsIgnoreCase("alexa")) {
		    nowResponse = "Yes, I am here";
		}
		
		
//		Changed the default text here 
		System.out.println("--------------");
		System.out.println("Sai's Message: "+nowInputText);
		
		System.out.println("Alexa's Response: "+nowResponse);
		System.out.println("--------------");

		return nowResponse;
		
	}
	
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
