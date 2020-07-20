package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		//String cadena = null;
		//PseudoLexer lexer = new PseudoLexer();
		//cadena = leerArchivo();
		//PseudoParser parser = new PseudoParser(cadena);
		
		Symbol s;
		Scope currentScope = new GlobalScope();
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		
		currentScope.define(new VariableSymbol("i",new BuiltInTypeSymbol("int")));
		s = currentScope.resolve("float");
		if(s==null)
			System.out.println("Error");
		
		MethodSymbol m = new MethodSymbol("f",new BuiltInTypeSymbol("float"),currentScope);
		currentScope.define(m);
		currentScope = m; 
		//System.out.println(m);
		/*Local Variables (int x, float y) Method Scope*/
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("x",new BuiltInTypeSymbol("int")));
		
		s = currentScope.resolve("float");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("y",new BuiltInTypeSymbol("float")));
		 
		/*Local Variable => float i */
		s = currentScope.resolve("float");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("i",new BuiltInTypeSymbol("float")));
		
		/*Local Variable Block scope {float z = x+y; i=z}*/
		/*
		LocalScope l = new LocalScope(currentScope);
		currentScope = l;
		
		System.out.println(l);
		 */
		
		s = currentScope.resolve("float");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("z",new BuiltInTypeSymbol("float")));
		
		s = currentScope.resolve("x");
		if(s==null)
			System.out.println("Error");
		s = currentScope.resolve("y");
		if(s==null)
			System.out.println("Error");
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		s = currentScope.resolve("z");
		if(s==null)
			System.out.println("Error");
		/*
		currentScope = l.getEnclosingScope();
		System.out.println(l);
		
		l = new LocalScope(currentScope);
		currentScope = l;
		System.out.println(l);
		 */
		
		/*Local Variable Block scope {float z = i+1; i=z}*/
		s = currentScope.resolve("float");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("z",new BuiltInTypeSymbol("float")));
		
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		s = currentScope.resolve("z");
		if(s==null)
			System.out.println("Error");
		/*
		currentScope = l.getEnclosingScope();
		System.out.println(l);
		*/
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		currentScope = m.getEnclosingScope();
		//System.out.println(m);
		
		s = currentScope.resolve("void");
		if(s==null)
			System.out.println("Error");
		
		m = new MethodSymbol("g",new BuiltInTypeSymbol("float"),currentScope);
		currentScope.define(m);
		currentScope = m; 
		
		s = currentScope.resolve("f");
		if(s==null)
			System.out.println("Error");
		
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		currentScope = m.getEnclosingScope();
		//System.out.println(currentScope);
	
	}
	
	public static String leerArchivo() throws FileNotFoundException {
		String cadena = null;
		
		BufferedReader br = new BufferedReader(new FileReader("src/main/programa"));
		
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
