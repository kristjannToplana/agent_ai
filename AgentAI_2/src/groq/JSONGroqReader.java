package groq;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import com.google.gson.*; 


public class JSONGroqReader {
	public String content ; 
	public ArrayList <String > titles ; 
	
	
	
	public String json ; 
	public JSONGroqReader (String json ) {
		this.json = json ; 
		content = new String () ; 
		this.titles = new ArrayList<String>() ; 
		
		
	}
	public void getAnswer () {
		
		String json = this.json ; 
		Gson gson = new Gson();

        // Parse JSON string into JsonObject
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray choices = jsonObject.getAsJsonArray("choices");
        JsonObject index0 = choices.get(0 ).getAsJsonObject() ; 
        JsonObject message = index0.get("message").getAsJsonObject() ; 
        String content = message.get("content").getAsString() ; 
        content = content.substring(content.indexOf("{") , content.indexOf("}") + 1 ) ; 
        
        JSONGroqLister lister = new JSONGroqLister() ; 
        lister.get_topics(content, 5 );
        
        this.titles = lister.topics ; 
        
        this.content = content ; 
        
        
        
	}
	
	
	// This is how to use the class 
    public static void main(String[] args) {
    	
        String json = "{\r\n"
        		+ "  \"id\": \"chatcmpl-d8110e85-e69f-471d-9ea1-9b9ff0e2cd5\",\r\n"
        		+ "  \"object\": \"chat.completion\",\r\n"
        		+ "  \"created\": 1751363588,\r\n"
        		+ "  \"model\": \"gemma2-9b-it\",\r\n"
        		+ "  \"choices\": [\r\n"
        		+ "    {\r\n"
        		+ "      \"index\": 0,\r\n"
        		+ "      \"message\": {\r\n"
        		+ "        \"role\": \"assistant\",\r\n"
        		+ "        \"content\": \"Low latency LLMs are particularly important for several key reasons:\\n\\n**1. Real-Time Applications:**\\n\\n* **Chatbots and Conversational AI:** In real-time chat applications, users expect immediate responses. High latency can make the conversation feel sluggish and unresponsive, ruining the user experience. Low latency LLMs enable natural and fluid interactions.\\n* **Interactive Tools:** Tools that rely on LLMs for real-time feedback, like code completion, text summarization, or translation, benefit greatly from low latency. Faster responses allow for a smoother and more efficient workflow.\\n* **Live Content Generation:** Applications that generate content on the fly, like live captioning, real-time news summarization, or interactive storytelling, require low latency to keep up with the flow of information.\\n\\n**2. Resource Efficiency:**\\n\\n* **Reduced Server Load:** Low latency LLMs often require less computational resources to process queries, leading to reduced server load and lower energy consumption.\\n* **Faster On-Device Processing:**  Smaller and more efficient LLMs can be deployed on edge devices, enabling offline or low-bandwidth applications without the need for constant internet connectivity.\\n\\n**3. Improved User Experience:**\\n\\n* **Responsiveness:** Immediate responses make interactions with AI feel more natural and engaging.\\n* **Performance:** Faster processing enables more complex and demanding applications, leading to a richer user experience.\\n* **Accessibility:**  Low latency LLMs can be more accessible to users with disabilities, as they allow for real-time communication and understanding. \\n\\n**4. Enabling New Possibilities:**\\n\\n* **High-Frequency Trading:** Low latency can be crucial in financial markets, allowing algorithms to analyze data and make trades in milliseconds.\\n* **Autonomous Systems:**  Real-time decision-making is essential for autonomous vehicles, drones, and other robots. Low latency LLMs can facilitate faster and more reliable control.\\n\\n**Challenges in Achieving Low Latency:**\\n\\nDeveloping low latency LLMs presents several challenges, including:\\n\\n* **Model Size and Complexity:**  Larger models generally require more processing power and time, leading to higher latency.\\n\\nFinding the right balance between model size and performance is crucial.\\n\\n* **Hardware Limitations:**  Even with efficient algorithms, current hardware may not be able to handle the demands of real-time LLMs.\\n* **Data Processing and Optimization:**  Efficient data preprocessing and query handling are essential for minimizing latency.\\n\\n\\nDespite these challenges, significant progress is being made in the field of low latency LLMs, opening up exciting possibilities for a wide range of applications.\\n\"\r\n"
        		+ "      },\r\n"
        		+ "      \"logprobs\": null,\r\n"
        		+ "      \"finish_reason\": \"stop\"\r\n"
        		+ "    }\r\n"
        		+ "  ],\r\n"
        		+ "  \"usage\": {\r\n"
        		+ "    \"queue_time\": 0.19039710599999998,\r\n"
        		+ "    \"prompt_tokens\": 17,\r\n"
        		+ "    \"prompt_time\": 0.001915889,\r\n"
        		+ "    \"completion_tokens\": 524,\r\n"
        		+ "    \"completion_time\": 0.952727273,\r\n"
        		+ "    \"total_tokens\": 541,\r\n"
        		+ "    \"total_time\": 0.954643162\r\n"
        		+ "  },\r\n"
        		+ "  \"usage_breakdown\": null,\r\n"
        		+ "  \"system_fingerprint\": \"fp_10c08bf97d\",\r\n"
        		+ "  \"x_groq\": {\r\n"
        		+ "    \"id\": \"req_01jz2pf44nfncrp7c8hcpwq421\"\r\n"
        		+ "  }\r\n"
        		+ "}\r\n"
        		+ "" ; 
        

        JSONGroqReader reader = new JSONGroqReader (json ) ; 
        reader.getAnswer(); 
        
        System.out.println (reader.content ) ; // String 
        
        
        
        
    }
}

