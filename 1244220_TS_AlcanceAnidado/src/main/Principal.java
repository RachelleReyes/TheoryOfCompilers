package main;

import java.io.FileNotFoundException;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		
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
		LocalScope local1 = new LocalScope(currentScope);
		currentScope = local1;
		System.out.println(local1);
		 
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
		
		currentScope = currentScope.getEnclosingScope();
		System.out.println(local1);
		
		LocalScope local2 = new LocalScope(currentScope);
		currentScope = local2;
		System.out.println(local2);
		
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
		
		currentScope = currentScope.getEnclosingScope();
		System.out.println(local2);
		
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		currentScope = currentScope.getEnclosingScope();
		
		s = currentScope.resolve("void");
		if(s==null)
			System.out.println("Error");
		
		MethodSymbol m2 = new MethodSymbol("g",new BuiltInTypeSymbol("float"),currentScope);
		currentScope.define(m2);
		currentScope = m2; 
		
		s = currentScope.resolve("f");
		if(s==null)
			System.out.println("Error");
		
		s = currentScope.resolve("i");
		if(s==null)
			System.out.println("Error");
		
		currentScope = currentScope.getEnclosingScope();
		System.out.println(currentScope);
	
	}

}
