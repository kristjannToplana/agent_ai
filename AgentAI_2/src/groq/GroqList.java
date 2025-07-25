package groq;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList; 

public class GroqList {

    private static final String API_KEY = "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"; // replace with env variable for safety
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    public ArrayList <String > titles ; 
    
    public static void main(String[] args) throws Exception {
    	String title = "The importance of the potato in the psychological wellbeing. " ; 
    	String prompt_system = "# Overview "
    			+ "You are a research assistant AI designed to generate five highly relevant and in-depth search topics based on a given search topic. Your goal is to break down the provided search topic into five key subtopics that will enable deep research and comprehensive understanding. "
    			+ " "
    			+ "## Instructions: "
    			+ "1) Break Down the Topic: Identify five distinct yet interconnected subtopics that, when researched, will provide a well-rounded understanding of the main search topic. "
    			+ "2) Ensure Depth and Relevance: Each search topic should be specific enough to allow deep exploration but broad enough to provide meaningful insights. "
    			+ "3) Avoid Redundancy: The five search topics should be unique and cover different facets of the main topic. "
    			+ " "
    			+ "## Output Format (JSON): "
    			+ "{ "
    			+ "  \"topic_1\": \"<First in-depth search topic>\", "
    			+ "  \"topic_2\": \"<Second in-depth search topic>\", "
    			+ "  \"topic_3\": \"<Third in-depth search topic>\", "
    			+ "  \"topic_4\": \"<Fourth in-depth search topic>\", "
    			+ "  \"topic_5\": \"<Fifth in-depth search topic>\" "
    			+ "} "
    			+ " "
    			+ "## Example Input & Output: "
    			+ "Input: "
    			+ "\"AI-Powered Business Automation\" "
    			+ " "
    			+ "Output "
    			+ "{ "
    			+ "  \"topic_1\": \"The Role of AI in Automating Business Processes\", "
    			+ "  \"topic_2\": \"AI-Driven Workflow Automation: Tools and Technologies\", "
    			+ "  \"topic_3\": \"Challenges and Ethical Considerations in AI Automation\", "
    			+ "  \"topic_4\": \"Case Studies: Successful AI Implementations in Business Automation\", "
    			+ "  \"topic_5\": \"The Future of AI Automation: Trends and Emerging Innovations\" "
    			+ "} "
    			+ " "
    			+ "## Final Notes "
    			+ "Your responses should be concise, relevant, and well-structured to guide in-depth research effectively. " ; 
    	String prompt_user = "The topic is : " + title ; 
    	
    	
    	GroqList client = new GroqList(); // Maybe its better to put the number of titles here 
    	client.sendChatMessage(prompt_system , prompt_user ) ; 
    	for (int i=0 ; i < client.titles.size() ; i ++ ) {
    		System.out.println (client.titles.get(i)) ; 
    		
    	}
        
    }
    
    /*
     * This is the one and only method to use here */
    public void sendChatMessage(String message_system , String message_user ) {
    	message_system = message_system.replace("\"", "\\\"");
    	message_user = message_user.replace("\"", "\\\"");
    	
    	
    	
        try {
//        	String jsonRequestBody = """
//                    {
//                      "messages": [{"system": "user", "content": "%s"}],
//                      "messages": [{"role": "user", "content": "%s"}],
//                      
//                      "model": "gemma2-9b-it"
//                      
//                    }
//                    """.formatted(message_system , message_user );
        	String jsonRequestBody = """
                    {
                      "messages": [{"role": "system", "content": "%s"} , 
                      {"role": "user", "content": "%s"}],
                      "model": "llama-3.3-70b-versatile"
                      
                      
                    }
                    """.formatted(message_system , message_user );
        	

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(API_URL))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + API_KEY)
                        .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                        .build();

                HttpClient client = HttpClient.newHttpClient();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String responce = response.body();
                    JSONGroqReader reader = new JSONGroqReader(responce ) ; 
                    reader.getAnswer() ; 
                    this.titles = reader.titles ; // This is to be used on the outside and not returned 
//                    return reader.content ; 
                    
                    
                } else {
                    throw new RuntimeException("Failed to get response from Groq API, status code: " + response.statusCode() + ", body: " + response.body());
                }
                
        }
        catch (Exception exception ) {
        	exception.printStackTrace() ; 
        	return ; 
        	
        	
        }
    }
    
    
    
}
