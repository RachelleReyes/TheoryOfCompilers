package main;

public class ClassSymbol extends ScopedSymbol implements Type{
	Scope enclosingScope, parentScope;
	Symbol sym;
	
	public ClassSymbol(String name, Type type, Scope enclosingScope,Scope parentScope) {
		super(name, type, enclosingScope);
		this.enclosingScope = enclosingScope;
		this.parentScope = parentScope;
	}

	@Override
	public String getScopeName() {return "local";}

	@Override
	public Scope getEnclosingScope() {return enclosingScope;}
	
	public Scope getParentScope() {return parentScope;}
	
	@Override
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);	
		
		if(s!=null) {
			System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;			 		 
		}
		
		if(getParentScope()!= null) {
			return getParentScope().resolve(name);
		}
		
		if(enclosingScope!= null) {		
			return enclosingScope.resolve(name); 
		}
		
		return null;
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}

}
