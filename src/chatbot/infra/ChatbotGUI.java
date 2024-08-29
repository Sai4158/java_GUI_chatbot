package chatbot.infra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import chatbot.infra.Chatbot;

public class ChatbotGUI extends JFrame {

	static private Chatbot nowChatbot;
	
	static private JFrame nowGUIFrame;
	
	static private JTextField inputTextBox;
	static private JTextPane chatHistoryPane;
	static private JScrollPane scroll;
	
	static private JButton psuButton;
	
	public ChatbotGUI(Chatbot nowChatbot) {
		
		this.nowChatbot = nowChatbot;
		
		/*
		 * Task 1: Make the interface prettier!
		 * 
		 * As you can see, this graphical interface (GUI) is not pretty. Please
		 * modify the following codes to move the components around or change
		 * their appearances, such as color or size, to make the interface
		 * looks nicer. Please explain what you did in your video.
		 */
		
		//create the frame of chatbot
		nowGUIFrame = new JFrame();
		nowGUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		nowGUIFrame.setVisible(true);
		nowGUIFrame.setResizable(false);
		nowGUIFrame.setLayout(null);
		nowGUIFrame.setSize(700, 600);
		nowGUIFrame.setTitle("Chatbot "+nowChatbot.getBotName()+"");
		
		//create JTextPane
		chatHistoryPane = new JTextPane();
		nowGUIFrame.add(chatHistoryPane);
//		MADE THE PANNEL SIZE BIGGER
		chatHistoryPane.setSize(680, 400);
		chatHistoryPane.setLocation(2, 2);

		//create JTextField
		inputTextBox = new JTextField();
		nowGUIFrame.add(inputTextBox);
		inputTextBox.setSize(680, 30);
		inputTextBox.setLocation(2, 410);
		
		
		//	MADE background color of chat pane to light gray
        chatHistoryPane.setBackground(Color.DARK_GRAY); 
//		 MADE the input box background dark gray
		inputTextBox.setBackground(Color.DARK_GRAY);  
		
//		MADE the text box foreground yellow
        inputTextBox.setForeground(Color.YELLOW);  
        
		inputTextBox.addActionListener(new InputTextListener(inputTextBox, chatHistoryPane, this));
		
		//MADE THE UI LOOK BETTER
		chatHistoryPane.setBackground(Color.darkGray);
		
//		MADE THE BACKGROUND BLACK
		nowGUIFrame.getContentPane().setBackground(Color.BLACK);
		


		
		
		
		
		
		
		/* Task 2: Connect the "Send" button to the chatbot and show the
		 * response on the chat pane
		 * 
		 * For now, when the user clicks the "Send" button, the GUI only shows
		 * a message "Send" to the chat pane. Please modify the
		 * actionPerformed() method in the ButtonListener class so that the
		 * code will execute the following three steps each time the button
		 * is clicked:
		 *     (1) Pass the message that the user typed in the input box (i.e.,
		 *         the "user message") to the chatbot and receive its response
		 *         (i.e., the "bot response").
		 *     (2) Display "[USER NAME]: [user message]" in the chat pane.
		 *     (3) Display "[BOT NAME]: [bot response]" in the chat pane.
		 *     
		 * [Hint] You can take a look at the actionPerformed() in the
		 * InputTextListener class.
		 */
		
		//create JButton
		psuButton = new JButton("Send", null);
		nowGUIFrame.add(psuButton);
//		MADE THE BUTTON CENTERED
		psuButton.setBounds(275, 500, 140, 40);
		psuButton.addActionListener(new ButtonListener(inputTextBox, chatHistoryPane, this));
//		MADE THE BUTTON ORANGE THEMED
		psuButton.setBackground(Color.orange);
	}

	public ChatbotGUI() {
		
	}
	
	public Chatbot getChatbot() {
		return nowChatbot;
	}
	
	public static void appendToPane(JTextPane nowPane, String senderName, String message, Color color){
		
		String nowMsg = senderName+": "+message+"\n";
		
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = nowPane.getDocument().getLength();
        nowPane.setCaretPosition(len);
        nowPane.setCharacterAttributes(aset, false);
        nowPane.replaceSelection(nowMsg);
        
    }
	
	
}

class ButtonListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	private JTextField nowInputTextBox;
	
	//private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;
	
	public ButtonListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
//		Task 2: Connect the "Send" button to the chatbot and show the response on the chat pane
		
		// takes the user message from the input box
        String userMessage = nowInputTextBox.getText().trim();

        if (!userMessage.isEmpty()) {
         // Display user's message in the chat pane formatted as "User Name: message"
            ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), userMessage, Color.orange);

         // Get the chatbot's response based on the user message and display 
            String botResponse = chatbotUtil.getChatbot().getResponse(userMessage);
            
            ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), botResponse, Color.lightGray);

            // Clear the input box after sending the message
            nowInputTextBox.setText("");
        }
	}
	
}

class InputTextListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	
	private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;
	
	public InputTextListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, Color.orange);
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.lightGray);
		
		nowInputTextBox.setText("");
		
	}
		
}

