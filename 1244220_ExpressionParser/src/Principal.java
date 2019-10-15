import java.util.Scanner;

public class Principal {
	
	public static void main(String args[]) {
		
		/* 
		// Prueba que sólo imprime los tokens
		ExpressionLexer lexer = new ExpressionLexer("9+18-23*(54/6)122");
		Token t = lexer.nextToken();
		while(t.type != Lexer.EOF_TYPE) {
			System.out.println(t);
			t = lexer.nextToken();
		}
		System.out.println(t); //EOF
		*/
		
		/*Prueba del Parser */
		//ExpressionLexer lexer = new ExpressionLexer("(9 + 10)- 12 / 5 * 5"); 
		//ExpressionLexer lexer = new ExpressionLexer("10"); 
		//ExpressionLexer lexer = new ExpressionLexer("10+1"); 
		//ExpressionLexer lexer = new ExpressionLexer("(10/1)*12-32/12-(4)");
		
		//String cadena = "9+18-23*(54/6)-23";
		String cadena = getInput();
		ExpressionLexer lexer = new ExpressionLexer(cadena);
		ExpressionParser parser = new ExpressionParser(lexer);
		parser.expression(); //begin parsing at rule expression
	}
	
	static String getInput() {
		String cadena; 
		Scanner in = new Scanner(System.in);
	    System.out.println("Ingrese una expresion"); 
	 	cadena = in.nextLine(); 
		return cadena;
	}
	
	
}
