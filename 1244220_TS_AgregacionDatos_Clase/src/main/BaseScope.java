package main;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseScope implements Scope{
	Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	Scope enclosingScope;
	
	public BaseScope(Scope es) {
		enclosingScope = es;
	}
	
	public String getScopeName() { return "";}
	public Scope getEnclosingScope() { return enclosingScope; }
	
	public void define(Symbol sym) { 
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}


}
