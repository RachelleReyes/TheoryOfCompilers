package main;

import java.util.ArrayList;

import main.PseudoLexer.Token;

/*Parser recursivo descendiente de tipo LL1*/
public class PseudoParser {
	ArrayList <Token> tokens,tokenList;
	int index = 0, tab = 0, lineaCodigo = 0;
	String name,type;
	SymbolTable table = new SymbolTable();
	
	ArrayList <Tuple> listTuples = new ArrayList<Tuple>(); 
	Tuple tuple;
	
	public PseudoParser(String input, ArrayList <Tuple> listTuples, SymbolTable table) {
		this.listTuples = listTuples;
		this.table = table;
		PseudoLexer lexer = new PseudoLexer();
		tokens = lexer.lex(input);
		programa();
	}
	
	private void programa() {
		match("INICIOPROGRAMA");
		declaraciones();
		enunciados();
		match("FINPROGRAMA");
		tokenList = new ArrayList<Token>();
		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"fin",tokenList,0,0);
		listTuples.add(tuple);
		//printTuple();
	}
	
	/*Tema de alcance*/
	private void declaraciones() {
		while(tokens.get(index).type.name().equals("DECLARAVARIABLE")) {
			declaracion();
		}
	}
	
	private void declaracion() {
		tokenList = new ArrayList<Token>();
		match("DECLARAVARIABLE");
		match("VARIABLE");
		tokenList.add(tokens.get(index-1));
		name = tokens.get(index-1).data;
		match("DOSPUNTOS");
		
		if(tokens.get(index).type.name().equals("ENTERO")) {
			match("ENTERO");
			tokenList.add(tokens.get(index-1));
			type = "int";
		}
		else if(tokens.get(index).type.name().equals("FLOTANTE")) {
			match("FLOTANTE");
			tokenList.add(tokens.get(index-1));
			type = "float";
		}
		
		table.define(new VariableSymbol(name,new BuiltInTypeSymbol(type),null));
		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"declaracion",tokenList,lineaCodigo+1,lineaCodigo+1);
		listTuples.add(tuple);
		
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
		tokenList = new ArrayList<Token>();
		table.resolve(tokens.get(index).data); 
		match("VARIABLE");
		tokenList.add(tokens.get(index-1));
		match("IGUAL");
		expresion();
		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"asignacion",tokenList,lineaCodigo+1,lineaCodigo+1);
		listTuples.add(tuple);
	}
	
	private void match(String name) {
		if(tokens.get(index).type.name().equals(name)) {
				index++;
			return;
		}else throw new Error("Expecting " +  name + " found " + tokens.get(index).type.name());
	}
	
	
	/*My functions*/
	private void expresion() {
		/*<Expresion> -> <Valor> operador-aritmetico <Valor> | <Valor>*/
		valor();
		if(tokens.get(index).type.name().equals("OPARITMETICO")) {
			match("OPARITMETICO");
			tokenList.add(tokens.get(index-1));
			valor();
		}
	}
	
	private void leer() {
		tokenList = new ArrayList<Token>();
		match("LEER");
		match("CADENA");
		tokenList.add(tokens.get(index-1));
		match("COMA");
		table.resolve(tokens.get(index).data); 
		match("VARIABLE");
		tokenList.add(tokens.get(index-1));
		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"leer",tokenList,lineaCodigo+1,lineaCodigo+1);
		listTuples.add(tuple);
	}
	
	private void escribir() {
		/*<Escribir> -> escribir cadena | escribir cadena, variable*/
		tokenList = new ArrayList<Token>();
		match("ESCRIBIR");
		match("CADENA");
		tokenList.add(tokens.get(index-1));
		if(tokens.get(index).type.name().equals("COMA")) {
			match("COMA");
			table.resolve(tokens.get(index).data); 
			match("VARIABLE");
			tokenList.add(tokens.get(index-1));
		}

		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"escribir",tokenList,lineaCodigo+1,lineaCodigo+1);
		listTuples.add(tuple);
	}
	
	private void si() {
		/*si <Comparacion> entonces <Enunciados> fin-si*/
		match("SI");
		comparacion();
		match("ENTONCES");
		enunciados();
		match("FINSI");
		setLineFalse(lineaCodigo);
	}
	
	private void mientras() {
		/*<Mientras> -> mientras <Comparacion> <Enunciados> fin-mientras*/
		match("MIENTRAS");
		comparacion();
		tab++;
		enunciados();
		match("FINMIENTRAS");
		setLineFalse(lineaCodigo);
	}
	
	private void comparacion() {
		/*<Comparacion> -> (<valor> operador-relacional <valor>)*/
		tokenList = new ArrayList<Token>();
		match("PARENTESISIZQ");
		valor();
		match("OPRELACIONAL");
		tokenList.add(tokens.get(index-1));
		valor();
		match("PARENTESISDER");
		lineaCodigo++;
		tuple = new Tuple(lineaCodigo,"comparacion",tokenList,lineaCodigo+1,lineaCodigo+1);
		listTuples.add(tuple);
	}
	
	private void valor() {
		/*<Valor> -> variable | numero*/
		if (tokens.get(index).type.name().equals("VARIABLE")) {
			table.resolve(tokens.get(index).data); //Check variable
			match("VARIABLE");
			tokenList.add(tokens.get(index-1));
		}else if (tokens.get(index).type.name().equals("NUMERO")) {
			match("NUMERO");
			tokenList.add(tokens.get(index-1));
		}
			
	}
	
	// Function that sets all the False lines inside the comparing blocks
	private void setLineFalse(int line) {
		int numTuple = lineaCodigo-1;
		while(numTuple>0) {
			if(listTuples.get(numTuple).getType().equals("comparacion") && listTuples.get(numTuple).getLineFalse()==listTuples.get(numTuple).getLineTrue()) {
				listTuples.get(numTuple).setLineFalse(line+1);
				
				if(!listTuples.get(lineaCodigo-2).getType().equals("comparacion")) {
					listTuples.get(lineaCodigo-1).setLineFalse(numTuple+1);
					listTuples.get(lineaCodigo-1).setLineTrue(numTuple+1);
				}
				break;
			}
			numTuple--;
		}
	}
	
	// Function that prints Tuple
	public void printTuple() {
		int i = 0;
		for(i = 0; i<listTuples.size(); i++) {
			System.out.println(listTuples.get(i).toString());
		}
	}
	
}
