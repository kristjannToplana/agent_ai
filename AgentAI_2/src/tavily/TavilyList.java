package tavily;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class TavilyList {
	public String api_key ;  
	public String query ; 
	public int numresults ; 
	
	public String answer ; 
	public String response_time ; 
	
	public ArrayList <String > title ; 
	public ArrayList <String > content ; 
	public ArrayList <String > score ; 
	public ArrayList <String > link ; 
	
	
	TavilySearch tavily ; 
	/*
	 * You can leave the api_key null if you want */
	public TavilyList (String api_key , String query , int numresults ) { 
		this.api_key = api_key ; 
		this.query = query ; 
		this.numresults = numresults ; 
		
		
	}
	
	public void getSearchData () {
		String json = this.getSearchDataJson() ; 
		JSONReader reader = new JSONReader (json ) ; 
        reader.getAnswer(); 
        
        this.query = reader.query ; 
        this.answer = reader.answer ; 
        this.response_time = reader.response_time ; 
        this.title = reader.title ; 
        this.content = reader.content ; 
        System.out.println (reader.content ) ; 
        this.score = reader.score ; 
        this.link = reader.link ; 
        
        
        
//        System.out.println (reader.query ) ; // String 
//        System.out.println (reader.answer ) ; // String 
//        System.out.println (reader.response_time ); // String -> Integer.parseInt (theString )
//        System.out.println (reader.title ) ; // ArrayList <String >
//        System.out.println (reader.content ) ; // ArrayList <String >
//        System.out.println (reader.score ) ; // ArrayList <String >
	}
	
	public String getSearchDataJson () {
        try {
            String requestBody = String.format("""
                    {
                      "query": "%s",
                      "auto_parameters": false,
                      "topic": "general",
                      "search_depth": "basic",
                      "chunks_per_source": 3,
                      "max_results": %d,
                      "time_range": null,
                      "days": 7,
                      "include_answer": true,
                      "include_raw_content": true,
                      "include_images": false,
                      "include_image_descriptions": false,
                      "include_favicon": false,
                      "include_domains": [],
                      "exclude_domains": [],
                      "country": null
                    }
                    """, this.query, 5 );

            HttpRequest request = HttpRequest.newBuilder() 
            		.uri(URI.create("https://api.tavily.com/search"))
                    .header("Authorization", "Bearer " + this.api_key)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build() ;
            
            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Request failed with status: " + response.statusCode());
                System.err.println("Response: " + response.body());
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	
	
	public static void main (String [] args ) {
		TavilyList tavile = new TavilyList("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", 
				"Potatoes and their impact in ww2. ", 
				6) ; 
		tavile.getSearchData(); 
		
		System.out.println (tavile.query ) ; // String 
		System.out.println (tavile.answer ) ; // String 
		System.out.println (tavile.response_time ); // String -> Integer.parseInt (theString )
		System.out.println (tavile.title ) ; // ArrayList <String >
		System.out.println (tavile.content ) ; // ArrayList <String >
		System.out.println (tavile.score ) ; // ArrayList <String >
		
	}
	
	
	
	
}
