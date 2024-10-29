/*
 * SlotFiller.java is added for Assignment 1-3 (Language Understanding)
 */

package chatbot.component;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlotFiller {

	/*
	 * Task 1: Build the Slot Filler (extractSlotValues() in SlotFiller.java)
	 * 
	 * [Input] 
	 * One user message (e.g., "What's the weather in State College?")
	 * 
	 * [Output]
	 * A hash table that contains a set of (key, value) tuples, where the "key"
	 * is the name of the slot (e.g., "location") and "value" is the extracted
	 * value (e.g., "State College").
	 * 
	 */
	public Hashtable<String, String> extractSlotValues(String nowInputText) {

		//initialize the hash table. You do not need to change this line of code.
		Hashtable<String, String> result = new Hashtable<String, String>();

       //-------------- Modify Code Here (Assignment 1-3) Begins ---------------
        
        // Define arrays for cities and time patterns
        String[] cityNames = {"State College", "Los Angeles", "New York City", "Tokyo"};
        for (String city : cityNames) {
            if (nowInputText.toLowerCase().contains(city.toLowerCase())) {
                result.put("Location", city);
            }
        }

        // Pattern to find time expressions in the input text
        Pattern timePattern = Pattern.compile("\\b(\\d{1,2}(:\\d{2})?(\\s*[ap]m)?)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = timePattern.matcher(nowInputText);
        if (matcher.find()) {
            result.put("Time", matcher.group());
        }

        //-------------- Modify Code Here (Assignment 1-3) Ends ---------------

		//return the result hash table. You do not need to change this part of code.
		return result;

	}

}
