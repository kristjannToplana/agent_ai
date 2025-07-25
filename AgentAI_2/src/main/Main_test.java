package main;

import java.io.PrintStream;
import java.util.ArrayList;

import groq.GroqList;

import java.io.File;
import java.io.FileNotFoundException;

import tavily.TavilyList;

public class Main_test {

	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		TavilyList tavily_test = new TavilyList
				(null , "Potatoes and their implact in ww2", 5) ; 
		tavily_test.getSearchData(); 
		
		try {
			PrintStream output = new PrintStream (new File ("file.txt")) ; 
			for (int i=0 ; i < tavily_test.title.size () ; i ++ ) {
				String title = tavily_test.title.get (i ) ; 
				String content_ = tavily_test.content.get (i ) ; 
				String content = content_ ; 
				
				
				
				output.println ("Title " + i + " - " + title ) ; 
				int count_words = 0 ; 
				
				for (int j=0 ; j < content.length() ; j ++ ) {
					if (count_words == 6 ) {
						output.print("\n");
						count_words = 0 ; 
						
					}
					else {
						if (content.charAt (j ) == ' ') {
							count_words ++ ; 
							
						}
						
					}
					output.print (content.charAt(j)) ; 
					
				}
//				output.println(content + "\n\n");
				output.println ("\n\n") ; 
				
				
			}
		}
		catch (Exception exception ) {
			// Mos bej asgje vl 
			
		}
		
		
		
		
		
	}
	public static String prompt = "# Overview "
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
	public static String title = "The importance of potatoes in mental health. " ; 
	
	public static void main (String [] args ) {
		int number_of_topics = 5 ; 
		
		GroqList groq_topics = new GroqList() ; 
		groq_topics.sendChatMessage(prompt , title ); 
		
		ArrayList <TavilyList > tavily_array = new ArrayList<TavilyList>() ; 
		
		for (int i=0 ; i < number_of_topics ; i ++ ) {
			TavilyList list = new TavilyList("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", 
					groq_topics.titles.get(i) , 5 ) ; 
			list.getSearchData(); 
			tavily_array.add(list) ; 
			
		}
		
		print (tavily_array , groq_topics ) ; 
		
	}
	public static void print (ArrayList<TavilyList> tavily_list , GroqList groq_list ) {
		try {
			PrintStream output = new PrintStream(new File ("file.txt")) ;
			for (int i=0  ; i < groq_list.titles.size () ; i ++ ) {
				output.println(String.format("Title %d : ", i ) + groq_list.titles.get(i));
				output.println ("Answer :: " + tavily_list.get(i).answer ) ; 
				for (int j=0 ; j < tavily_list.get(i).title.size () ; j ++ ) {
					output.println ("Nen titull :: " + tavily_list.get(i).title.get(j) ) ; 
					output.println ("Content :: " +  tavily_list.get(i).content.get(j ) ) ; 
					
				}
			}
			output.close () ; 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}


