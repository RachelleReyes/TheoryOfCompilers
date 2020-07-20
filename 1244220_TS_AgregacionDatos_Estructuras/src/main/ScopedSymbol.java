package main;

import java.util.HashMap;
import java.util.Map;

public abstract class ScopedSymbol extends Symbol implements Scope{
	Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	Scope enclosingScope;
	
	public ScopedSymbol(String name, Type type, Scope enclosingScope) {
		// TODO Auto-generated constructor stub
		super(name, type);
		//this.enclosingScope = enclosingScope;
	}
	
	public Map<String,Symbol> getSymbols(){return symbols;};
	
	public void define(Symbol sym) {
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}
	
}
