

public abstract class Parser {
	Lexer input; //from where do we get tokens
	Token lookahead; //the current lookahead token
	
	public Parser(Lexer input) {
		//super();
		this.input = input;
		lookahead = input.nextToken();
	}
	
	/** If lookahead token type matches x, consume & return else error*/
	public void match(int x) {
		if(lookahead.type == x) {
			consume(); 
			System.out.print(input.getTokenName(x) + " "); //I added this
		}
		else throw new Error("expecting " + input.getTokenName(x)+ ";found "+lookahead);
		
	}
	public void consume() {lookahead = input.nextToken();}
	
	
}
