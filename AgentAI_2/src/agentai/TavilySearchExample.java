package agentai;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import tavily.JSONReader;

import java.io.File; 

public class TavilySearchExample {

    public static void main(String[] args) {
        String apiKey = "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";  // Replace with your Tavily API key
        String query = "Latest iPhone model";
        int numResults = 3;

        try {
            URL url = new URL("https://api.tavily.com/search");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set HTTP method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON request body
            String requestBody = String.format("""
                {
                    "query": "%s",
                    "include_answer": true,
                    "num_results": %d
                }
                """, query, numResults);

            // Send the request
            try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                out.writeBytes(requestBody);
                out.flush();
            }

            // Read the response
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }

            in.close();
            connection.disconnect();

            System.out.println("Response Code: " + responseCode);
            // This is the JSON that is returns 
            System.out.println("Search Results:\n" + content.toString());
            
            // This is a class implemented by me 
            JSONReader reader = new JSONReader(content.toString() ) ; 
            reader.getAnswer() ; 
            
            
            System.out.println (reader.query ) ; // String 
            System.out.println (reader.answer ) ; // String 
            System.out.println (reader.response_time ); // String -> Integer.parseInt (theString )
            System.out.println (reader.title ) ; // ArrayList <String >
            System.out.println (reader.content ) ; // ArrayList <String >
            System.out.println (reader.score ) ; // ArrayList <String >
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
