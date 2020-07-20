package main;

public class Principal {

	public static void main(String[] args){
	
		Symbol s;
		Scope currentScope = new GlobalScope();
		
		// class A
		s = currentScope.resolve("class");
		if(s==null)
			System.out.println("Error");
		
		ClassSymbol classA = new ClassSymbol("A",new BuiltInTypeSymbol("class"),currentScope,null);
		currentScope.define(classA);
		currentScope = classA;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("x",new BuiltInTypeSymbol("int")));
		 
		MethodSymbol foo1 = new MethodSymbol("foo",new BuiltInTypeSymbol("void"),currentScope);
		currentScope.define(foo1);
		currentScope = foo1;
		
		currentScope = currentScope.getEnclosingScope();
		System.out.println(currentScope);
		currentScope = currentScope.getEnclosingScope();
		
		// class B
		s = currentScope.resolve("class");
		if(s==null)
			System.out.println("Error");
		
		ClassSymbol classB = new ClassSymbol("B",new BuiltInTypeSymbol("class"),currentScope,classA);
		currentScope.define(classB);
		currentScope = classB;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("y",new BuiltInTypeSymbol("int")));
		
		s = currentScope.resolve("foo");
		if(s==null)
			System.out.println("Error");
		
		MethodSymbol foo2 = new MethodSymbol("foo",new BuiltInTypeSymbol("void"),currentScope);
		currentScope.define(foo2);
		currentScope = foo2;
		
		s = currentScope.resolve("int");
		if(s==null)
			System.out.println("Error");
		currentScope.define(new VariableSymbol("z",new BuiltInTypeSymbol("int")));
		System.out.println(currentScope);
		
		s = currentScope.resolve("x");
		if(s==null)
			System.out.println("Error");
		
		s = currentScope.resolve("y");
		if(s==null)
			System.out.println("Error");
		
		currentScope = currentScope.getEnclosingScope();
		currentScope = currentScope.getEnclosingScope();
		System.out.println(currentScope);
	}

	
}
