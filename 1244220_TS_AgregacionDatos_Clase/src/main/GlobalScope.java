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
		define(new BuiltInTypeSymbol("struct"));
		define(new BuiltInTypeSymbol("class"));
		define(new BuiltInTypeSymbol("public"));
	}
	
	public String getScopeName() {return "global";};	
	
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);			
		
		if(s!=null) {
			System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;					 		
		}
		if(enclosingScope!= null) {			
			return enclosingScope.resolve(name);
		}
		return null; //not found in this scope or there's no scope above
	}
}
