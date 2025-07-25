/*
 * This class is not used */


package tavily;
import kong.unirest.Unirest;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;





import org.apache.hc.client5.http.auth.* ; 


public class TavilySearch {
	String api_key = "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" ; 
	String query = "Latest iPhone model" ; 
	int numResults = 3 ; 
	
	/*
	 * This is the constructor where you have to give the api_key etc etc*/
	public TavilySearch (String api_key , String query , int numResults ) {
		this.api_key = api_key ; 
		this.query = query ; 
		this.numResults = numResults ; 
		
	}
	/*
	 * For this method you just call it and get a responce in the form of a query */
	public String getSearchData_2 () {
		try {
            URL url = new URL("https://api.tavily.com/search");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set HTTP method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + this.api_key);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON request body
            String requestBody ; 
            if (true ) {
            	requestBody = String.format("""
                		{
    					  "query": "%s",
    					  "auto_parameters": false,
    					  "topic": "general",
    					  "search_depth": "advanced",
    					  "chunks_per_source": 5,
    					  "max_results": %d,
    					  "time_range": null,
    					  "days": 7,
    					  "include_answer": true,
    					  "include_raw_content": false,
    					  "include_images": false,
    					  "include_image_descriptions": false,
    					  "include_favicon": false,
    					  "include_domains": [],
    					  "exclude_domains": [],
    					  "country": null
    					}
                		""", 
                		this.query , this.numResults ) ; 
            			// "include_raw_content": true,
            }
            else {
            	requestBody = String.format("""
                      {
                          "query": "%s",
                          "include_answer": true,
                          "num_results": %d
                      }
                      """, this.query, this.numResults);
            	
            }

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

//            System.out.println("Response Code: " + responseCode);
//            System.out.println("Search Results:\n" + content.toString());
//            
//            PrintStream output = new PrintStream (new File ("file.txt")) ; 
//            String string = "Search Results:\n" + content.toString() ; 
//            int len = string.length() ; 
//            for (int i=0 ;  i < len ; i ++ ) {
//            	output.print(string.charAt(i)) ; 
//            	if (i % 100 == 0 ) {
//            		output.print("\n") ; 
//            		
//            	}
//            	
//            }
            return content.toString() ; 
            
            

        } catch (Exception e) {
            e.printStackTrace();
            return null ; 
            
        }
		
	}
	
	public String getSearchData () {
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
                    """, this.query, this.numResults);

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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String api_key = "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" ; 
		String query = "Latest iPhone model" ; 
		int numResults = 3 ; 
		
		TavilySearch tavily = new TavilySearch(api_key , query, numResults ) ; 
		String output = tavily.getSearchData() ; 
		
		// output comes in the form of a json 
		System.out.println (output ) ; 
		
	}

}
