package main;

import java.util.HashMap;
import java.util.Map;

public class MethodSymbol extends Symbol implements Scope{
	Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	Scope enclosingScope;
	String name;	  //All symbols at least have a name
	Type type;
	
	public MethodSymbol(String name, Type type, Scope enclosingScope) {
		super(name, type);
		this.enclosingScope = enclosingScope;
	}

	public String getScopeName() { return "local";}
	public Scope getEnclosingScope() { return enclosingScope; }
	public void define(Symbol sym) { 
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		if(s!=null) {
			return s;					 //return it if in this scope
		}
		if(enclosingScope!= null) {				 //have an enclosing scope?
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}
}
