package latex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File ; 

public class PdfLatexCompiler {
	String texFile = "file2.tex";
	public PdfLatexCompiler (String file_location ) {
		this.texFile = file_location ; 
		
	}
//	public void turn_to_pdf () {
//		try {
//            // Build the pdflatex command
//			
//            ProcessBuilder pb = new ProcessBuilder("pdflatex", (new File (texFile)).getAbsolutePath());
//            pb.redirectErrorStream(true); // merge error stream with output stream
//
//            // Start the process
//            Process process = pb.start();
//
//            // Read and print the output of the process
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // Wait for the process to finish and get the exit code
//            int exitCode = process.waitFor();
//            System.out.println("pdflatex exited with code " + exitCode);
//            
//
//            if (exitCode == 0) {
//                System.out.println("PDF generated successfully!");
//            } else {
//                System.out.println("There was an error compiling the LaTeX file.");
//            }
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//		
//		
//	}
	public void turn_to_pdf(String outputDirPath) {
	    try {
	        File tex = new File(texFile);  // texFile is your .tex path
	        String texAbsolutePath = tex.getAbsolutePath();
	        String texName = tex.getName();

	        // Ensure output directory exists
	        File outputDir = new File(outputDirPath);
	        if (!outputDir.exists()) {
	            outputDir.mkdirs();
	        }

	        // Build the pdflatex command with -output-directory
	        ProcessBuilder pb = new ProcessBuilder(
	            "pdflatex",
	            "-output-directory=" + outputDir.getAbsolutePath(),
	            texAbsolutePath
	        );

	        pb.redirectErrorStream(true);

	        // Start the process
	        Process process = pb.start();

	        // Read and print output
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }

	        int exitCode = process.waitFor();
	        System.out.println("pdflatex exited with code " + exitCode);

	        if (exitCode == 0) {
	            // Get the output PDF file path
	            String baseName = texName.replaceFirst("[.][^.]+$", ""); // remove .tex extension
	            File pdfFile = new File(outputDir, baseName + ".pdf");

	            if (pdfFile.exists()) {
	                System.out.println("PDF generated successfully at: " + pdfFile.getAbsolutePath());
	            } else {
	                System.out.println("PDF compilation reported success but PDF not found.");
	            }
	        } else {
	            System.out.println("There was an error compiling the LaTeX file.");
	        }

	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
    public static void main(String[] args) {
        // Path to your LaTeX file
        String texFile = "file2.tex";
        PdfLatexCompiler latex_test = new PdfLatexCompiler (texFile ) ; 
        latex_test.turn_to_pdf(texFile); 
        if (true ) {
        	return ; 
        	
        }
        

        try {
            // Build the pdflatex command
            ProcessBuilder pb = new ProcessBuilder("pdflatex", texFile);
            pb.redirectErrorStream(true); // merge error stream with output stream

            // Start the process
            Process process = pb.start();

            // Read and print the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish and get the exit code
            int exitCode = process.waitFor();
            System.out.println("pdflatex exited with code " + exitCode);

            if (exitCode == 0) {
                System.out.println("PDF generated successfully!");
            } else {
                System.out.println("There was an error compiling the LaTeX file.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
