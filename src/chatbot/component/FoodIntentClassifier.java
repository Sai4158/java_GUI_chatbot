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
        // Assign scores based on keyword relevance for food intents
        if (nowInputText.toLowerCase().contains("restaurant") || nowInputText.toLowerCase().contains("eat")) {
            scoreArray[0] = 1.0; // Score for "FindFood" intent
        }
        if (nowInputText.toLowerCase().contains("order") || nowInputText.toLowerCase().contains("delivery")) {
            scoreArray[1] = 1.0; // Score for "OrderFood" intent
        }
        //============= Please Modify Here (ends) =============== 

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
