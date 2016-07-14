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
		ControllerBash controller=new ControllerBash();
		try {
			resultado=controller.ExecuteCommand(new prueba1());
		} catch (CommandExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IlegalCommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resultado);
	}

}
