package chatbot.main;

import chatbot.infra.Chatbot;
import chatbot.infra.ChatbotGUI;

public class Main {

	public static void main(String[] args)  {
		
//		updated my name and bot name
		//create your chatbot (user name, bot name)
		Chatbot nowChatbot = new Chatbot("Sai", "ALEXA");
		
		//create a GUI that connects to your chatbot
		ChatbotGUI nowChatbotGUI = new ChatbotGUI(nowChatbot);

	}

}
