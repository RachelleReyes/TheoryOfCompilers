package main;

public class Principal {

	public static void main(String[] args){
	
		Symbol s;
		Scope currentScope = new GlobalScope();
		// struct A
		StructSymbol strA = new StructSymbol("A",new BuiltInTypeSymbol("struct"),currentScope);
		currentScope.define(strA);
		currentScope = strA;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("x",new BuiltInTypeSymbol("int")));
		 
		// struct B
		StructSymbol strB = new StructSymbol("B",new BuiltInTypeSymbol("struct"),currentScope);
		currentScope.define(strB);
		currentScope = strB;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("y",new BuiltInTypeSymbol("int")));
		s = currentScope.resolve("y");
		currentScope = currentScope.getEnclosingScope();
		
		// B b
		s = currentScope.resolve("B");
		currentScope.define(new VariableSymbol("b",new BuiltInTypeSymbol("B")));
		
		// struct C
		StructSymbol strC = new StructSymbol("C",new BuiltInTypeSymbol("struct"),currentScope);
		currentScope.define(strC);
		currentScope = strC;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("z",new BuiltInTypeSymbol("int")));
		currentScope = currentScope.getEnclosingScope();
		
		//C c
		s = currentScope.resolve("C");
		currentScope.define(new VariableSymbol("c",new BuiltInTypeSymbol("C")));
		currentScope = currentScope.getEnclosingScope();
		//A a
		s = currentScope.resolve("A");
		currentScope.define(new VariableSymbol("a",new BuiltInTypeSymbol("A")));
		
		//void f
		s = currentScope.resolve("void");
		MethodSymbol m = new MethodSymbol("f",new BuiltInTypeSymbol("void"),currentScope);
		currentScope.define(m);
		currentScope = m; 
		
		// struct D;
		StructSymbol strD = new StructSymbol("D",new BuiltInTypeSymbol("struct"),currentScope);
		currentScope.define(strD);
		currentScope = strD;
	
		// int i;
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("i",new BuiltInTypeSymbol("int")));
		//currentScope = currentScope.getEnclosingScope();
		
		// D d
		s = currentScope.resolve("D");
		currentScope.define(new VariableSymbol("d",new BuiltInTypeSymbol("D")));
		
		//d.i = a.b.y;
		s = currentScope.resolve("d");
		if(s==null)
			System.out.println("Error");
		else {
			s = currentScope.resolve("i");
		}
		
		s = currentScope.resolve("a");
		if(s==null)
			System.out.println("Error");
		else {
			s = currentScope.resolve("b");
			s = currentScope.resolve("y");
		}
		
		currentScope = currentScope.getEnclosingScope();
		//System.out.println(currentScope);
	}

	
}
