package main;

public class LocalScope extends BaseScope{
	
	public LocalScope(Scope enclosingScope) {
		super(enclosingScope);
	}
	
	public String getScopeName() {return "local";};	
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		System.out.println("Resolve " + name + " en el alcance " + getScopeName());
		
		if(s!=null) {
			return s;
		}					 					 //return it if in this scope
		if(enclosingScope!= null) {				 //have an enclosing scope?
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}

}
