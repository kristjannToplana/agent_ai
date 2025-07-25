package string_fix;

public class FixLatex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
//	public static String[][] letters = {
//	    // Row 0: Characters that need escaping in LaTeX
//	    {
//	        "\\",    // Backslash
//	        "{",     // Left curly brace
//	        "}",     // Right curly brace
//	        "#",     // Hash
//	        "$",     // Dollar
//	        "%",     // Percent
//	        "&",     // Ampersand
//	        "_",     // Underscore
//	        "~",     // Tilde
//	        "^",     // Caret
//	        "<",     // Less than (can be problematic in math mode)
//	        ">",     // Greater than
//	        "|",     // Vertical bar
//	        "\"",    // Double quote (for special cases)
//	        "'",     // Single quote (curly quotes can break)
//	        "`",     // Grave accent
//	        "–",     // En dash
//	        "—",     // Em dash
//	        "“",     // Left double quotation mark
//	        "”",     // Right double quotation mark
//	        "‘",     // Left single quotation mark
//	        "’",     // Right single quotation mark
//	        "…",     // Ellipsis
//	        "·",     // Middle dot
//	        "•",     // Bullet
//	        "«",     // Left-pointing double angle
//	        "»",     // Right-pointing double angle
//	        "©",     // Copyright
//	        "®",     // Registered trademark
//	        "™",     // Trademark
//	        "°",     // Degree symbol
//	        "€",     // Euro
//	        "£",     // Pound
//	        "¥",     // Yen
//	        " "      // Non-breaking space (U+00A0)
//	    },
//	    // Row 1: Corresponding LaTeX-safe representations
//	    {
//	        "\\textbackslash{}",  // Backslash
//	        "\\{",                // Left curly brace
//	        "\\}",                // Right curly brace
//	        "\\#",                // Hash
//	        "\\$",                // Dollar
//	        "\\%",                // Percent
//	        "\\&",                // Ampersand
//	        "\\_",                // Underscore
//	        "\\textasciitilde{}", // Tilde
//	        "\\textasciicircum{}",// Caret
//	        "\\textless{}",       // Less than
//	        "\\textgreater{}",    // Greater than
//	        "\\textbar{}",        // Vertical bar
//	        "\\textquotedbl{}",   // Double quote
//	        "\\textquotesingle{}",// Single quote
//	        "\\textasciigrave{}", // Grave accent
//	        "--",                 // En dash
//	        "---",                // Em dash
//	        "``",                 // Left double quote
//	        "''",                 // Right double quote
//	        "\\ldots{}",          // Ellipsis
//	        "\\cdot{}",           // Middle dot
//	        "\\textbullet{}",     // Bullet
//	        "\\guillemotleft{}",  // Left-pointing double angle
//	        "\\guillemotright{}", // Right-pointing double angle
//	        "\\textcopyright{}",  // Copyright
//	        "\\textregistered{}", // Registered trademark
//	        "\\texttrademark{}",  // Trademark
//	        "\\textdegree{}",     // Degree symbol
//	        "\\euro{}",           // Euro (requires euro package)
//	        "\\pounds{}",         // Pound
//	        "\\yen{}",            // Yen
//	        "~"                   // Non-breaking space
//	    }
	    public static String[][] letters = {
    	    // Characters that cause issues in LaTeX
    	    {
    	        "\\", "{", "}", "#", "$", "%", "&", "_", "~", "^", // LaTeX special
    	        "â", "ê", "î", "ô", "û",  // French accented
    	        "ä", "ë", "ï", "ö", "ü", "ÿ", // Umlaut
    	        "á", "é", "í", "ó", "ú", "ý", // Acute
    	        "à", "è", "ì", "ò", "ù",      // Grave
    	        "ã", "ñ", "õ",               // Tilde
    	        "å", "ø", "ç",               // Other
    	        "ß", "Æ", "æ", "Œ", "œ"
    	    },
    	    // Their LaTeX-safe replacements
    	    {
    	        "\\textbackslash{}", "\\{", "\\}", "\\#", "\\$", "\\%", "\\&", "\\_", "\\textasciitilde{}", "\\textasciicircum{}",
    	        "\\^a", "\\^e", "\\^\\i{}", "\\^o", "\\^u",
    	        "\\\"a", "\\\"e", "\\\"\\i{}", "\\\"o", "\\\"u", "\\\"y",
    	        "\\'a", "\\'e", "\\'\\i{}", "\\'o", "\\'u", "\\'y",
    	        "\\`a", "\\`e", "\\`\\i{}", "\\`o", "\\`u",
    	        "\\~a", "\\~n", "\\~o",
    	        "\\aa{}", "\\o{}", "\\c{c}",
    	        "\\ss{}", "\\AE{}", "\\ae{}", "\\OE{}", "\\oe{}"
    	    }
    	};
    
	public static String replace (String input ) {
//		for (int i=0 ; i < input.length() ; i ++ ) {
//			if (input.charAt(i) == 'â') {
//				int A = 1 ; 
//				
//			}
//		}
		String output = new String (input ) ; 
		for (int i=0 ; i < 10 ; i ++ ) {
			output = output.replace(letters[0][i ], letters[1][i ]) ; 
//			output = output.replace(letters[0][i ], "-") ; 
			
		}
		output = replace_non_ascii(output ) ; 
		return output ; 
		
	}
	
	public static String replace_non_ascii (String input ) {
		String output = new String () ; 
		
		for (char c : input.toCharArray()) {
		    if (c > 127) {
		        output += "-" ; 
		        
		    }
		    else {
		    	output += c ; 
		    	
		    }
		}
		
		return output ; 
		
	}
}
