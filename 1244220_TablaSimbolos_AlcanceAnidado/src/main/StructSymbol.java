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
	public String getScopeName() { return "local"; }

	@Override
	public Scope getEnclosingScope() { return enclosingScope; }

	@Override
	public void define(Symbol sym) {
		//System.out.println(symbols);
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}

	@Override
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			
		if(s!=null) {
			//System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;			 		 
		}
		if(enclosingScope!= null) {				 
			//System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return enclosingScope.resolve(name); 
		}
	
		//System.out.println("HOLAAA");
		return null;
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}
	
}
