package main;

import java.util.HashMap;
import java.util.Map;

public class StructSymbol extends ScopedSymbol implements Type{
	Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	Scope enclosingScope;
	Symbol sym;
	
	public StructSymbol(String name, Type type,Scope enclosingScope) {
		super(name, type,enclosingScope);
	}

	@Override
	public String getScopeName() { return null; }

	@Override
	public Scope getEnclosingScope() { return enclosingScope; }

	@Override
	public void define(Symbol sym) {
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}

	@Override
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		if(s!=null) {
			System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;					 //return it if in this scope
		}
		if(enclosingScope!= null) {				 //have an enclosing scope?
			//System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}
	
}
