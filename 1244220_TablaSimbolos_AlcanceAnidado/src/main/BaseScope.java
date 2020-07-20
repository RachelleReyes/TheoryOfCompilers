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
	//public void define(Symbol sym) { symbols.put(sym.name, sym);}
	/*
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		if(s!=null) return s;					 //return it if in this scope
		if(enclosingScope!= null) {				 //have an enclosing scope?
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}*/
	
	public String toString() {return getScopeName()+ ":" + symbols;}


}
