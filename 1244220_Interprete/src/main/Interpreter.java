package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Interpreter {
	ArrayList <Tuple> listTuples = new ArrayList<Tuple>();
	SymbolTable table = new SymbolTable();
	
	public Interpreter(ArrayList<Tuple> listTuples, SymbolTable table) {
		this.listTuples = listTuples;
		this.table = table;
		
		interpret();
	}
	
	private void interpret(){
		int i = 0,valI1 = 0, valI2 = 0;
		float valF1 = 0, valF2 = 0;
		Symbol symbol;
		String value = null,operacion, result; 
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(listTuples.get(i).getType() != "fin") {
		     switch(listTuples.get(i).getType()) {
	             case "leer":
	            	 // Escribir el mensaje
	            	 System.out.println(listTuples.get(i).getList().get(0).data);   
	                 //Leer del teclado un valor
	            	 
					try {
						value = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	                 //Guardar el valor en la variable
	            	 symbol = table.resolve(listTuples.get(i).getList().get(1).data); 
	            	 symbol.setValue(value);
	                 i = listTuples.get(i).getLineTrue()-1;
	                 break;
	                 
	             case "escribir": 
	                 //Escribir el mensaje
	            	 System.out.print(listTuples.get(i).getList().get(0).data);
	                 //Escribir el valor en la variable
	            	 if(listTuples.get(i).getList().size()>1) {
	            		 symbol = table.resolve(listTuples.get(i).getList().get(1).data); 
	            		 System.out.println(symbol.value);
	            	 }
	            	 
	            	 i = listTuples.get(i).getLineTrue()-1;
	                 break;
	                 
	            // asginacion, revisar cuantos numeros. revisar si son variables o numeros literales
	             case "asignacion": 
	                 //Obtener los valores de la tabla de simbolos
	            	 if(listTuples.get(i).getList().size()>2) {
	            		 symbol = table.resolve(listTuples.get(i).getList().get(1).data); 
	            		 valF1 = Float.parseFloat(symbol.value);
	            		 
	            		 // Si es el segundo elemento es una variable
	            		 if(listTuples.get(i).getList().get(3).type.toString()!="NUMERO") {
	            			 symbol = table.resolve(listTuples.get(i).getList().get(3).data); 
			            	 valF2 = Float.parseFloat(symbol.value);
	            		 }else {
	            			 valF2 = Float.parseFloat(listTuples.get(i).getList().get(3).data);
	            		 }
	            			 
		            	 operacion = listTuples.get(i).getList().get(2).data;
	            		//Realizar la operacion 
	            		 result = operacion(valF1,operacion,valF2);
	            		 
			            //Guardar el resultado en la variable
	            		 symbol = table.resolve(listTuples.get(i).getList().get(0).data); 
	            		 symbol.setValue(result);
	            	 }else {
	            		 symbol = table.resolve(listTuples.get(i).getList().get(0).data); 
	            		 symbol.setValue(listTuples.get(i).getList().get(1).data);	
	            		 
	            	 }
	             
	            	 i = listTuples.get(i).getLineTrue()-1;
	                 break;

	            case "comparacion": 
	            	
	                 //Obtener los valores de la tabla de simbolos
	            	 symbol = table.resolve(listTuples.get(i).getList().get(0).data); 
            		 valF1 = Float.parseFloat(symbol.value);
            		 
            		 if(listTuples.get(i).getList().get(0).type != listTuples.get(i).getList().get(2).type) {
            			 valF2 = Float.parseFloat(listTuples.get(i).getList().get(2).data);
            		 }else {
            			 symbol = table.resolve(listTuples.get(i).getList().get(2).data); 
    	            	 valF2 = Float.parseFloat(symbol.value);
            		 }
            		 
	            	 operacion = listTuples.get(i).getList().get(1).data;
	            	 if(compare(valF1,operacion,valF2)== true)
	            		 i = listTuples.get(i).getLineTrue()-1;
	            	 else
	            		 i = listTuples.get(i).getLineFalse()-1;
                     break;
                     
	            case "declaracion": 
	            	i++;
                break;
		     }
		}
	}
	
	private String operacion(float val1,String symbol,float val2) {
		float resultado = 0;
		//OPARITMETICO("[*|/|+|-]"),
		switch(symbol) {
		case "+":
			resultado = val1 + val2;
		break;
		case "-":
			resultado = val1 - val2;
		break;
		case "*":
			resultado = val1 * val2;
		break;
		case "/":
			resultado = val1 / val2;
		break;	
	}
		
		return String.valueOf(resultado);
	}
	
	private boolean compare(float val1,String symbol,float val2) {
		boolean result = false;
		//OPRELACIONAL("<|>|==|<=|>=|!="),
		switch(symbol) {
    		case "<":
    			if(val1<val2)
    				result = true;
    		break;
    		case ">":
    			if(val1>val2)
    				result = true;
    		break;
    		case "==":
    			if(val1==val2)
    				result = true;
    		break;
    		case "<=":
    			if(val1<=val2)
    				result = true;
    		break;
    		case ">=":
    			if(val1>=val2)
    				result = true;
    		break;
    		case "!=":
    			if(val1!=val2)
    				result = true;
    		break;
		}
		return result;
	}
	
}
