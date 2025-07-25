package latex;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import groq.GroqList;
import string_fix.FixLatex;
import tavily.TavilyList;

public class LatexHandler {
	public String title ; 
	public GroqList groq_list ; 
	public ArrayList <TavilyList > tavily_array ; 
	
	public static String getPrompt (String title ) {
		String prompt = "# Overview "
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
				+ "Your responses should be concise, relevant, and well-structured to guide in-depth research effectively. "
				+ "The topic is : "; 
		return prompt ; 
		
	}
	public static void main (String [] args ) {
		String title = "Potatoes and their implact in ww2" ; 
		String prompt_system = getPrompt (title ) ; 
		int number_of_topics = 5 ; 
		
		GroqList groq_topics = new GroqList() ; 
		groq_topics.sendChatMessage(prompt_system , title ); 
		
		ArrayList <TavilyList > tavily_array = new ArrayList<TavilyList>() ; 
		
		for (int i=0 ; i < number_of_topics ; i ++ ) {
			TavilyList list = new TavilyList("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", 
					groq_topics.titles.get(i) , 5 ) ; 
			list.getSearchData(); 
			tavily_array.add(list) ; 
			
		}
		
		LatexHandler handler = new LatexHandler (title , groq_topics , tavily_array ) ; 
		String file_location = 
				String.format("all_the_things_about_the_stuff\\files\\%s.tex", 
						title.replace(" ", "_") ) ; 
		handler.write_in_latex(file_location ); 
		
		
	}
	public LatexHandler (String title , GroqList groq_list , ArrayList<TavilyList > tavily_array ) {
		this.title = title ; 
		this.groq_list = groq_list ; 
		this.tavily_array = tavily_array ; 
		
	}
	public void write_in_latex (String file_location ) {
		
		try {
			
			PrintStream output = new PrintStream (new File (file_location )) ; 
			String string ; 
			string = "\\documentclass[12pt]{article}\r\n"
					+ "\\usepackage{geometry}\r\n"
					+ "\\geometry{a4paper, margin=1in}\r\n"
					+ "\\usepackage{parskip} % for paragraph spacing\r\n"
					+ "\\usepackage{titlesec}\r\n"
					+ "\\usepackage[T1]{fontenc}\r\n"
					+ "\\usepackage[utf8]{inputenc}\r\n"
					
					+ "\r\n"
					+ "\r\n"
					+ "\\title{"+ FixLatex.replace(this.title) + "}\r\n"
					+ "\\author{}\r\n"
					+ "\\date{}\r\n"
					+ "\r\n"
					+ "\\begin{document}\r\n"
					+ "\r\n"
					+ "\\maketitle\r\n"
					+ ""
					+ "" ; 
			
			output.print(string );
			
			for (int i=0  ; i < groq_list.titles.size () ; i ++ ) {
				output.println();
				
				String title = String.format("Title %d : ", i ) + FixLatex.replace(groq_list.titles.get(i)) ; // section title 
				output.print("\\section{" + FixLatex.replace(title) + "}\r\n" + FixLatex.replace(tavily_array.get(i).answer )); // section introduction 
				
				for (int j=0 ; j < tavily_array.get(i).title.size () ; j ++ ) {
					output.print("\\subsection{" + FixLatex.replace(tavily_array.get(i).title.get(j) )
							+ "}\r\n" + FixLatex.replace(tavily_array.get(i).content.get(j )) ); // section introduction 
					
				}
			}
			
			
			
			string = "\\end{document}" ; 
			output.print (string ) ; 
			
			output.close () ; 
			
//			PdfLatexCompiler compiler = new PdfLatexCompiler(file_location ) ; 
//			compiler.turn_to_pdf();
			
		}
		
		catch (Exception exception ) {
			// Mos bej asgje vl 
			
		}
	}
	
}
