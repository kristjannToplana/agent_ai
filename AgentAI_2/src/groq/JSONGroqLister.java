package groq;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import com.google.gson.*; 



public class JSONGroqLister {
	ArrayList <String > topics ; 
	
	public JSONGroqLister () {
		this.topics = new ArrayList<String>() ; 
		
	}
	
	public void get_topics (String json , int number_of_topis ) {
		
		this.topics.clear(); 
//		String json = this.json ; 
		Gson gson = new Gson(); 

        // Parse JSON string into JsonObject
		json = json.replace("```json", "") ; 
		json = json.replace("```", "") ; 
		
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        
        for (int i=0  ; i < number_of_topis ; i ++ ) {
        	this.topics.add(jsonObject.get(String.format ("topic_%d" , i+1 )).getAsString() ) ; 
        	
        }
        
        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json = "{\\n\"topic_1\": \"The Potato as a Symbol of Sustenance and Security in Cultural Psychology\",\\n\"topic_2\": \"Nutritional Composition of Potatoes and its Impact on Mood and Cognitive Function\",\\n\"topic_3\": \"The Role of Potato-Based Traditions and Rituals in Social Cohesion and Psychological Comfort\",\\n\"topic_4\": \"Cultural Representations of the Potato in Art, Literature, and Film: Exploring its Psychological Significance\",\\n\"topic_5\": \"Investigating Potential Psychological Benefits of Incorporating Potatoes into Diets:  A Cross-Cultural Perspective\" \\n} \\n" ; 
		JSONGroqLister lister = new JSONGroqLister () ; 
		lister.get_topics(json, 5 );
		
		for (int i=0 ; i < 5 ; i ++ ) {
			System.out.println (lister.topics.get(i) ) ; 
			
		}
	}

}
