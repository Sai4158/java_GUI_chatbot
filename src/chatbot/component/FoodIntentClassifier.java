/*
 * chatbot.component is added for Assignment 3 (Language Understanding)
 * 
 * FoodIntentClassifier.java is added for Assignment 3 (Language
 * Understanding)
 */

package chatbot.component;

public class FoodIntentClassifier {

	private static String[] intentDictionary;

	public FoodIntentClassifier() {
		initializeIntentDictionary();
	}

	/**
	 * Create a dictionary of intents (under Food domain)  
	 */
	private void initializeIntentDictionary() {
		intentDictionary = new String[]{"FindFood", "OrderFood"};
		System.out.print("Intents: (");
		for(int i=0; i<intentDictionary.length; i++) {
			System.out.print(intentDictionary[i]);
			if(i != intentDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
	}

	/**
	 * Calculate the given message's score for each intent based on keywords.
	 * The chatbot will select the intent with the highest score.
	 * 
	 * @param nowInputText  An English message sent from the user.
	 * @return              An array of scores for each intent.
	 */
	private Double[] calculateIntentScores(String nowInputText) {
		Double[] scoreArray = new Double[intentDictionary.length];
		for(int i=0; i<scoreArray.length; i++) {
			scoreArray[i] = 0.0; // Initialize scores to 0.0
		}

		//============= Please Modify Here (begins) =============== 
		 // Check for general food-related keywords for finding food.
	    if (nowInputText.toLowerCase().contains("restaurant") || 
	        nowInputText.toLowerCase().contains("eat") ||
	        nowInputText.toLowerCase().contains("food place") ||
	        nowInputText.toLowerCase().contains("where can I eat")) {
	        scoreArray[0] = 1.0; // Score for "FindFood" intent.
	    }

	    // Check for keywords specifically associated with ordering food.
	    if (nowInputText.toLowerCase().contains("order") || 
	        nowInputText.toLowerCase().contains("delivery") ||
	        nowInputText.toLowerCase().contains("deliver food") ||
	        nowInputText.toLowerCase().contains("order a meal") ||
	        nowInputText.toLowerCase().contains("pizza") ||
	        nowInputText.toLowerCase().contains("burger") ||
	        nowInputText.toLowerCase().contains("get food")) {
	        scoreArray[1] = 1.0; // Increase the score for "OrderFood" intent.
	    }
		//============= Please Modify Here (ends) =============== 
		
		//do not change the following lines
		if(scoreArray.length!=intentDictionary.length) {
			System.err.println("The score array size does not equal to the intent array size.");
			System.exit(1);
		}
		for(Double nowValue: scoreArray) {
			if(nowValue==null) {
				System.err.println("The score array contains null values.");
				System.exit(1);
			}
		}
		return scoreArray;
	}

	/**
	 * Determine which intent has the highest score and return it.
	 * 
	 * @param nowInputText  An English message sent from the user.
	 * @return              The highest scoring intent name.
	 */
	public String getLabel(String nowInputText) {
		Double[] intentScores = calculateIntentScores(nowInputText);
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Intent Scores: (");
		for(int i=0; i<intentScores.length; i++) {
			System.out.print(intentScores[i].doubleValue());
			if(i != intentScores.length-1) {
				System.out.print(", ");
			}
			if(nowMaxScore == null || nowMaxIndex == -1 || intentScores[i].doubleValue() > nowMaxScore.doubleValue()) {
				nowMaxIndex = i;
				nowMaxScore = intentScores[i].doubleValue();
			}
		}
		System.out.println(")");
		return intentDictionary[nowMaxIndex];
	}
}
