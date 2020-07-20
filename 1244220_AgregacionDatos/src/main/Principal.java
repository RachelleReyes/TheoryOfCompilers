package main;

import java.io.FileNotFoundException;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		
		Symbol s;
		Scope currentScope = new GlobalScope();	
		
		StructSymbol str = new StructSymbol("A",new BuiltInTypeSymbol("struct"),currentScope);
		currentScope.define(str);
		currentScope = str;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("x",new BuiltInTypeSymbol("int")));
		
	}

}
