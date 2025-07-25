package tavily;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import com.google.gson.*; 


public class JSONReader {
	public String query ; 
	public String answer ; 
	public String response_time ; 
	
	public ArrayList <String > title ; 
	public ArrayList <String > content ; 
	public ArrayList <String > score ; 
	public ArrayList <String > link ; 
	
	
	public String json ; 
	public JSONReader (String json ) {
		this.json = json ; 
		title = new ArrayList<String>() ; 
		content = new ArrayList<String>() ; 
		score = new ArrayList<String>() ; 
		link = new ArrayList<String>() ; 
		
		
	}
	public void getAnswer () {
		this.title.clear(); 
		this.content.clear(); 
		this.score.clear(); 
		this.link.clear(); 
		
		
		String json = this.json ; 
		Gson gson = new Gson();

        // Parse JSON string into JsonObject
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        // Extract "query" and "answer"
        this.query = jsonObject.get("query").getAsString();
        this.answer = jsonObject.get("answer").getAsString();
        this.response_time = jsonObject.get("response_time").getAsString(); 
        

//        System.out.println("Query: " + query);
//        System.out.println("Answer: " + answer);

        // Extract "results" array
        JsonArray results = jsonObject.getAsJsonArray("results");

//        System.out.println("\nResults titles:");
//        int A = results.size () ; 
        
        for (int i = 0; i < results.size(); i++) {
        	// 
            JsonObject result = results.get(i).getAsJsonObject();
            // String title = result.get("title").getAsString();
            this.title.add(result.get("title").getAsString()); 
            this.content.add(result.get("content").getAsString()); 
            this.score.add(result.get("score").getAsString()); 
            this.link.add(result.get("score").getAsString()) ; 
            
            
//            System.out.println("- " + title);
        }
        
        
	}
	
	
	// This is how to use the class 
    public static void main(String[] args) {
    	
        String json = "{"
            + "\"query\":\"Latest iPhone model\","
            + "\"follow_up_questions\":null,"
            + "\"answer\":\"The latest iPhone model is the iPhone 16e, released on February 28, 2025. It follows the iPhone 16 series, which includes the iPhone 16, iPhone 16 Plus, iPhone 16 Pro, and iPhone 16 Pro Max.\","
            + "\"images\":[],"
            + "\"results\":["
            + "  {"
            + "    \"title\":\"What Is the Newest iPhone? the Latest Apple Phones You Can Buy\","
            + "    \"url\":\"https://www.businessinsider.com/guides/tech/what-is-the-newest-iphone?op=1\","
            + "    \"content\":\"The newest iPhone is the iPhone 16e, released on February 28, 2025. It is followed by the iPhone 16 series...\","
            + "    \"score\":0.8482825,"
            + "    \"raw_content\":null"
            + "  },"
            + "  {"
            + "    \"title\":\"iPhone 17: Release date, rumors, specs, price of Apple's 2025 iPhone ...\","
            + "    \"url\":\"https://www.macworld.com/article/2393967/iphone-17-release-date-specs-features-rumors.html\","
            + "    \"content\":\"Below you can find links to our reviews of all iPhone models...\","
            + "    \"score\":0.47387698,"
            + "    \"raw_content\":null"
            + "  }"
            + "],"
            + "\"response_time\":2.79"
            + "}";

        JSONReader reader = new JSONReader (json ) ; 
        reader.getAnswer(); 
        
        System.out.println (reader.query ) ; // String 
        System.out.println (reader.answer ) ; // String 
        System.out.println (reader.response_time ); // String -> Integer.parseInt (theString )
        System.out.println (reader.title ) ; // ArrayList <String >
        System.out.println (reader.content ) ; // ArrayList <String >
        System.out.println (reader.score ) ; // ArrayList <String >
        
        
        
    }
}
