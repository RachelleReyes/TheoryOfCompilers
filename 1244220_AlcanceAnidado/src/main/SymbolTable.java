package main;

import java.util.*;

public class SymbolTable implements Scope{ //single-scope symtab
	Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	public SymbolTable() {initTypeSystem();}
	protected void initTypeSystem() {
		define(new BuiltInTypeSymbol("int"));
		define(new BuiltInTypeSymbol("float"));
	}
	//Satisfy Scope interface
	public String getScopeName() {return "global";};	
	public Scope getEnclosingScope() {return null;}	
	public void define(Symbol sym) {symbols.put(sym.name,sym);} 	
	//public Symbol resolve(String name) {return symbols.get(name);}
	
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);
		if(s!=null) return s;
		else throw new Error("\n Error: '" + name+ "' undeclared");
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}
	
}
