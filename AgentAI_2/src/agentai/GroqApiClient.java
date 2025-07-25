package agentai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GroqApiClient {

    private static final String API_KEY = "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"; // replace with env variable for safety
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        GroqApiClient client = new GroqApiClient();
        String response = client.sendChatMessage("Explain the importance of low latency LLMs");
        System.out.println("Response from Groq API:\n" + response);
        
        
    }
    
    public String sendChatMessage(String message) {
        try {
        	String jsonRequestBody = """
                    {
                      "messages": [{"role": "user", "content": "%s"}],
                      "model": "gemma2-9b-it"
                    }
                    """.formatted(message);
        	
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Failed to get response from Groq API, status code: " + response.statusCode() + ", body: " + response.body());
            }
            
        }
        catch (Exception exception ) {
        	exception.printStackTrace() ; 
        	return null ; 
        	
        }
    }
}
