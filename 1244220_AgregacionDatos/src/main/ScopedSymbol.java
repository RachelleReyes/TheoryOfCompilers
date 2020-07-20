package main;

public abstract class ScopedSymbol extends Symbol implements Scope{

	Scope enclosingScope;
	public ScopedSymbol(String name, Type type, Scope enclosingScope) {
		// TODO Auto-generated constructor stub
		super(name, type);
		this.enclosingScope = enclosingScope;
	}

	
	
}
