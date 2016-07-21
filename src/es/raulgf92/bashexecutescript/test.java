package es.raulgf92.bashexecutescript;

import java.io.FileInputStream;
import java.util.Properties;

import es.raulgf92.bashexecutescript.exception.CommandExecuteException;
import es.raulgf92.bashexecutescript.exception.IlegalCommandException;
import es.raulgf92.bashexecutescript.service.prueba1;


public class test {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resultado = "";
		prueba1 prueba=new prueba1();
		prueba.start();
		while(prueba.isAlive()){
		}
		System.out.println(prueba.response);
	}

}
