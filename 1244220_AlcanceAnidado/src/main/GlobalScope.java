package main;

public class GlobalScope extends BaseScope {
	
	public GlobalScope() {
		super(null);
		initTypeSystem();
	}
	
	protected void initTypeSystem() {
		define(new BuiltInTypeSymbol("int"));
		define(new BuiltInTypeSymbol("float"));
		define(new BuiltInTypeSymbol("void"));
	}
	
	public String getScopeName() {return "global";};	
	
	public void define(Symbol sym) { 
		symbols.put(sym.name, sym);
		System.out.println("Define " + sym.name + " en el alcance " + getScopeName());
	}
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			 //look in this scope
		
		if(s!=null) {
			System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;					 		//return it if in this scope
		}
		if(enclosingScope!= null) {				 //have an enclosing scope?
			
			return enclosingScope.resolve(name); //check enclosing scope
		}
		return null; //not found in this scope or there's no scope above
	}
}
