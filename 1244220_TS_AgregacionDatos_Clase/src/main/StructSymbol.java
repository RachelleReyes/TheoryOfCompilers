package main;

public class StructSymbol extends ScopedSymbol implements Type{
	//Map<String,Symbol> symbols = new HashMap<String,Symbol>();
	Scope enclosingScope;
	Symbol sym;
	
	public StructSymbol(String name, Type type,Scope enclosingScope) {
		super(name, type,enclosingScope);
		this.enclosingScope = enclosingScope;
	}

	@Override
	public String getScopeName() { return "local"; }
	@Override
	public Scope getEnclosingScope() { return enclosingScope; }
	
	@Override
	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);	
		
		if(s!=null) {
			System.out.println("Resolve " + name + " en el alcance " + getScopeName());
			return s;			 		 
		}
		
		if(enclosingScope!= null) {	
			return enclosingScope.resolve(name); 
		}
		return null;
	}
	
	public String toString() {return getScopeName()+ ":" + symbols;}
	
}
