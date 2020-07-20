package main;

public class LocalScope extends BaseScope{
	
	public LocalScope(Scope enclosingScope) {
		super(enclosingScope);
	}
	
	public String getScopeName() {return "local";};	
	public Scope getEnclosingScope() {return enclosingScope;}

	public void define(Symbol sym) { 
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		if(s!=null) {
			//System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;
		}					 //return it if in this scope
		if(enclosingScope!= null) {				 //have an enclosing scope?
			//System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}

}
