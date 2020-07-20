package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		String cadena, archivo = "src/main/pseudoCodigo";
		cadena = leerArchivo(archivo);
		ArrayList <Tuple> listTuples = new ArrayList<Tuple>();
		SymbolTable table = new SymbolTable();
		
		PseudoParser parser = new PseudoParser(cadena,listTuples,table);
		//System.out.println(listTuples);
		//parser.printTuple();
		Interpreter interpreter = new Interpreter(listTuples,table);
	}
	
	private static String leerArchivo(String archivo) throws FileNotFoundException {
		String cadena = null;
		//PseudoLexer lexer = new PseudoLexer();
		BufferedReader br = new BufferedReader(new FileReader(archivo));
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
		return cadena;
		
	}

}
