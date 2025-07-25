package main;

import java.io.PrintStream;
import java.util.ArrayList;

import groq.GroqList;
import latex.LatexHandler;
import latex.PdfLatexCompiler;
import prompts.Prompt;
import sendemail.EmailSender;

import java.io.File; 

import tavily.TavilyList;

public class Main {
	public String email ; 
	public String title ; 
	
	public static void main(String[] args) {
		String title = "Potatoes and their implact in ww2" ; 
		int number_of_topics = 5 ; 
		GroqList groq_topics = new GroqList() ; 
		groq_topics.sendChatMessage(Prompt.PLAN_TOPICS , title ); 
		
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
		
		PdfLatexCompiler compiler = new PdfLatexCompiler(String.format("%s.tex", title.replace(" ", "_"))) ; 
		compiler.turn_to_pdf("all_the_things_about_the_stuff\\files\\");
		
		
		// Sending the email 
		final String fromEmail = "rrok.toplana2@gmail.com";         // replace with your email
        final String password = "htho lpov skhj zqbj";       // app password if 2FA is enabled
        final String toEmail = "kristjan.toplana20@gmail.com";    // recipient email
        
        EmailSender sender_test = new EmailSender(toEmail) ; 
        sender_test.send_email_with_attachment("Your File", "This is your file. ", 
        		String.format("all_the_things_about_the_stuff\\files\\%s.pdf"
        		, title.replace(" ", "_")));
        
		
		
	}
	public Main (String email , String title ) { 
		this.email = email ; 
		this.title = title ; 
		
		
	}
	
	public void do_something () { 
		int number_of_topics = 5 ; 
		
		GroqList groq_topics = new GroqList() ; 
		groq_topics.sendChatMessage(Prompt.PLAN_TOPICS ,  this.title ); 
		
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
		
		PdfLatexCompiler compiler = new PdfLatexCompiler(String.format("all_the_things_about_the_stuff\\files\\%s.tex", title.replace(" ", "_"))) ; 
		compiler.turn_to_pdf("all_the_things_about_the_stuff\\files\\");
		
		
		// Sending the email 
		final String fromEmail = "rrok.toplana2@gmail.com";         // replace with your email
        final String password = "htho lpov skhj zqbj";       // app password if 2FA is enabled
        final String toEmail = "kristjan.toplana20@gmail.com";    // recipient email
        
        EmailSender sender_test = new EmailSender(toEmail) ; 
        sender_test.send_email_with_attachment("Your File", "This is your file. ", String.format("all_the_things_about_the_stuff\\files\\%s.pdf", title.replace(" ", "_")));
        
	}
}


