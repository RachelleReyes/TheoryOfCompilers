
public class ExpressionParser extends Parser{
	public ExpressionParser(Lexer input) {super(input);}
	
	public void expression2(){
		match(ExpressionLexer.PARENTESISIZQ); expression(); 
		
		if(lookahead.type==ExpressionLexer.PARENTESISDER)
			match(ExpressionLexer.PARENTESISDER);
		else throw new Error("expecting PARENTESISDER; found " + lookahead);
	}
	
	public void expression(){
		element();
		while(lookahead.type==ExpressionLexer.OPARTIMETICO) {
			match(ExpressionLexer.OPARTIMETICO); element();
		}
	}
	
	/** element : name | expression  ; // element is number or nested expression */
	void element() {
		if(lookahead.type==ExpressionLexer.NUMERO) match(ExpressionLexer.NUMERO);
		else if (lookahead.type==ExpressionLexer.PARENTESISIZQ) expression2();
		else throw new Error("expecting number or expression; found " + lookahead);
	}
}