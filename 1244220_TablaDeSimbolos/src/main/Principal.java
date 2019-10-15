package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		String cadena = null;
		//PseudoLexer lexer = new PseudoLexer();
		BufferedReader br = new BufferedReader(new FileReader("src/main/pseudoCodigo"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = null;
			
				line = br.readLine();
				
				while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    cadena = sb.toString();
			    br.close();
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		PseudoParser parser = new PseudoParser(cadena);
	}

}
