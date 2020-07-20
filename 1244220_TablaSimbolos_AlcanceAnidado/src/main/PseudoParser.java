package main;

import java.util.ArrayList;

import main.PseudoLexer.Token;
/*Parser recursivo descendiente de tipo LL1*/
public class PseudoParser {
	ArrayList <Token> tokens;
	int index = 0, tab = 0;
	String name,type;
	SymbolTable table = new SymbolTable();
	
	public PseudoParser(String input) {
		PseudoLexer lexer = new PseudoLexer();
		tokens = lexer.lex(input);
		programa();
	}
	
	private void programa() {
		match("INICIOPROGRAMA");
		System.out.println("void main(){");		
		if (tokens.get(index).type.name().equals("DECLARAVARIABLE")) {
			declaraciones();
		}
		enunciados();
		match("FINPROGRAMA");
		System.out.println("}");
		
	}
	
	/*Tema de alcance*/
	private void declaraciones() {
		while(tokens.get(index).type.name().equals("DECLARAVARIABLE")) {
			declaracion();
		}
	}
	
	private void declaracion() {
		match("DECLARAVARIABLE");
		//table.resolve(tokens.get(index).data); //Check variable
		match("VARIABLE");
		name = tokens.get(index-1).data;
		match("DOSPUNTOS");
		
		if(tokens.get(index).type.name().equals("ENTERO")) {
			match("ENTERO");
			type = "int";
		}
		else if(tokens.get(index).type.name().equals("FLOTANTE")) {
			match("FLOTANTE");
			type = "float";
		}
		
		System.out.print(" "+ type);
		System.out.println(" " + tokens.get(index-3).data + ";");
		
		table.define(new VariableSymbol(name,new BuiltInTypeSymbol(type)));
		/*
		 tabla.define(new VariableScope(-,-));
		 En cada parte donde se hace mencion de una variable 
		 Hacer un resolve para revisar si existe el dato en la table
		 */
		
	}
	
	
	private void enunciados() {
		enunciado();
		if (tokens.get(index).type.name().equals("FINPROGRAMA") || tokens.get(index).type.name().equals("FINSI") || tokens.get(index).type.name().equals("FINMIENTRAS")) 
			return; 
		enunciados();
	}
	
	private void enunciado() {
		if (tokens.get(index).type.name().equals("VARIABLE")) { asignacion(); return; }
		if (tokens.get(index).type.name().equals("LEER")) { leer(); return; }
		if (tokens.get(index).type.name().equals("ESCRIBIR")) { escribir(); return; }
		if (tokens.get(index).type.name().equals("SI")) { si(); return; }
		if (tokens.get(index).type.name().equals("MIENTRAS")) { mientras(); return; }
		
	}
	
	private void asignacion() {
		tabulador(tab);
		table.resolve(tokens.get(index).data); //Check variable		
		match("VARIABLE");
		System.out.print(" "+ tokens.get(index-1).data);
		match("IGUAL");
		System.out.print(tokens.get(index-1).data);
		expresion();
		System.out.println(";");
	}
	
	private void match(String name) {
		if(tokens.get(index).type.name().equals(name)) {
				index++;
			return;
			/*Validacion del index*/
		}else throw new Error("Expecting " +  name + " found " + tokens.get(index).type.name());
	}
	
	
	/*My functions*/
	private void expresion() {
		/*<Expresion> -> <Valor> operador-aritmetico <Valor> | <Valor>*/
		valor();
		if(tokens.get(index).type.name().equals("OPARITMETICO")) {
			match("OPARITMETICO");
			System.out.print(tokens.get(index-1).data);
			valor();
		}
	}
	
	private void leer() {
		tabulador(tab);
		match("LEER");
		match("CADENA");
		System.out.println(" puts(" + tokens.get(index-1).data + ");");
		match("COMA");
		tabulador(tab);
		table.resolve(tokens.get(index).data); //Check variable
		System.out.print(" scanf(\"%d\",&");
		match("VARIABLE");
		System.out.print(tokens.get(index-1).data);
		System.out.println(");");
	}
	
	private void escribir() {
		/*<Escribir> -> escribir cadena | escribir cadena, variable*/
		tabulador(tab);
		match("ESCRIBIR");
		System.out.print(" printf(");
		match("CADENA");
		System.out.println(tokens.get(index-1).data + ");");
		if(tokens.get(index).type.name().equals("COMA")) {
			tabulador(tab);
			System.out.print(" printf(\"");
			System.out.print("%d\"");
			match("COMA");
			System.out.print(tokens.get(index-1).data);
			table.resolve(tokens.get(index).data); //Check variable
			match("VARIABLE");
			System.out.println(tokens.get(index-1).data + ");");
		}
	}
	
	private void si() {
		/*si <Comparacion> entonces <Enunciados> fin-si*/
		tabulador(tab);
		match("SI");
		System.out.print(" if");
		comparacion();
		System.out.println("{");
		tab++;
		match("ENTONCES");
		enunciados();
		match("FINSI");
		tab--;
		System.out.println(" }");
		
	}
	private void mientras() {
		/*<Mientras> -> mientras <Comparacion> <Enunciados> fin-mientras*/
		match("MIENTRAS");
		System.out.print(" while");
		comparacion();
		System.out.println("{");
		tab++;
		enunciados();
		match("FINMIENTRAS");
		tab--;
		System.out.println(" }");
	}
	
	private void comparacion() {
		/*<Comparacion> -> (<valor> operador-relacional <valor>)*/
		match("PARENTESISIZQ");
		System.out.print("(");
		valor();
		match("OPRELACIONAL");
		System.out.print(tokens.get(index-1).data);
		valor();
		match("PARENTESISDER");
		System.out.print(")");
	}
	
	private void valor() {
		/*<Valor> -> variable | numero*/
		
		if (tokens.get(index).type.name().equals("VARIABLE")) {
			table.resolve(tokens.get(index).data); //Check variable
			match("VARIABLE");
			System.out.print(tokens.get(index-1).data);
		}else if (tokens.get(index).type.name().equals("NUMERO")) {
			match("NUMERO");
			System.out.print(tokens.get(index-1).data);
		}
			
	}
	
	private void tabulador(int cant) {
		for(int i = 0; i<cant; i++) {
			System.out.print("\t");
		}
		
	}
}
