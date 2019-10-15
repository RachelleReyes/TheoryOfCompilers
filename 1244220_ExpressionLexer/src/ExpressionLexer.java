
public class ExpressionLexer extends Lexer{
	public static int NUMERO = 2;
	public static int OPARTIMETICO= 3;
	public static int PARENTESISIZQ = 4;
	public static int PARENTESISDER = 5;
	public static String[] tokenNames = {"n/a","<EOF>","NUMERO","OPARTIMETICO","PARENTESISIZQ","PARENTESISDER"};
	
	public String getTokenName(int x) { return tokenNames[x];}
	
	public ExpressionLexer(String input) {super(input);}
	boolean isNUMBER() {return c>= '0' && c <='9';}
	
	public Token nextToken() {
		while(c!=EOF) {
		
			switch(c) {
			case ' ': case '\t': case '\n': case '\r': WS(); continue;
			case '+': consume(); return new Token(OPARTIMETICO,"+");
			case '-': consume(); return new Token(OPARTIMETICO,"-");
			case '*': consume(); return new Token(OPARTIMETICO,"*");
			case '/': consume(); return new Token(OPARTIMETICO,"/");
			case '(': consume(); return new Token(PARENTESISIZQ,"(");
			case ')': consume(); return new Token(PARENTESISDER,")");
			default: 
				if(isNUMBER()) return NUMERO();
				throw new Error("invalid character: " +c);
			
			}
		}
		return new Token(EOF_TYPE,"<EOF");
	}
	
	/*NUMERO : ('0' ... '9') // NUMERO is a sequence of >=1 number*/
	Token NUMERO() {
		StringBuilder buf =  new StringBuilder();
		do {buf.append(c); consume(); }while(isNUMBER());
			return new Token(NUMERO, buf.toString());
	}
	
	//WS: ('' | '\t' | '\n' '\r')* ; // ignore any whitespace
	void WS() {
		while(c== ' ' || c=='\t' || c=='\n' || c=='\r' ) consume();
	}
}
