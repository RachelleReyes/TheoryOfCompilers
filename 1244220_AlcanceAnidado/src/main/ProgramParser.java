package main;

import java.util.ArrayList;

import main.PseudoLexer.Token;

public class ProgramParser {
	ArrayList<main.ProgramLexer.Token> tokens;
	int index = 0, tab = 0;
	String name,type;
	SymbolTable table = new SymbolTable();
	
	public  ProgramParser(String input) {
		ProgramLexer lexer = new ProgramLexer();
		tokens = lexer.lex(input);
		programa();
	}
	
	void programa() {
		
	}
	private void declaraciones() {
		while(tokens.get(index).type.name().equals("ENTERO") || tokens.get(index).type.name().equals("FLOTANTE")) {
			declaracion();
		}
	}
	
	private void declaracion() {
		if(tokens.get(index).type.name().equals("ENTERO")) {
			match("ENTERO");
			type = "int";
		}
		else if(tokens.get(index).type.name().equals("FLOTANTE")) {
			match("FLOTANTE");
			type = "float";
		}
		
		match("VARIABLE");
		name = tokens.get(index-1).data;
		
		match("DOSPUNTOS");
		
		if(tokens.get(index).type.name().equals("IGUAL")) {
			match("IGUAL");
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
	
	private void asignacion() {
		table.resolve(tokens.get(index).data); 
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
	
	
	private void expresion() {
		/*<Expresion> -> <Valor> operador-aritmetico <Valor> | <Valor>*/
		valor();
		if(tokens.get(index).type.name().equals("OPARITMETICO")) {
			match("OPARITMETICO");
			System.out.print(tokens.get(index-1).data);
			valor();
		}
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
	
}
