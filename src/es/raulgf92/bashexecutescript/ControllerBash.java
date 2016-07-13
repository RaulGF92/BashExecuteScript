package es.raulgf92.bashexecutescript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import es.raulgf92.bashexecutescript.exception.*;

/**
 * ControllerBash
 * 
 * 
 * Permite la ejecución de servicios previamente configurados en diferentes bash's.
 * 
 * Entradas:
 * 		-Comando correcto
 * 		-Comando incorrecto --> {@link IlegalCommandException}
 * 
 * Salidas:
 *		- Correcto --> String
 *		- Incorrecto --> String
 *		- Error --> {@link CommandExecuteException} 
 *
 * @author Raulgf
 * @version 1.0
 * 
 */
class ControllerBash {

	protected ControllerBash(){
		
	}
	
	public String ExecuteCommand(String command,List<String> args) throws CommandExecuteException,IlegalCommandException{
		/**
		 * ExecuteCommand
		 * 
		 * Contruye un proceso nuevo que ejecuta el comando propocionado,añadiendole
		 * la lista completa de todos los argumentos.
		 * 
		 * Devuelve la resolución de la ejecucion para ser tratado. Si proporciona error o es incorrecto
		 * devuelve el fallo.
		 * 
		 * @version 1.0
		 * 
		 */
		
		//Estudio del comando
		checkCommand(command,args);
		
		String response="";
		ProcessBuilder pb;
		Process p;
		
		for(int i=0;i<12;i++)
			System.out.println("Estoy trabajando muy muy duro :3");
		
		
		//Si no devuelve nada genera excepción
		if(response=="")
			throw new CommandExecuteException();
		
		return response;
	}

	private void checkCommand(String command, List<String> args)throws IlegalCommandException {
	 /**
	  * CheckCommand
	  * 
	  * Metodo que filtrara y comprobara si los comandos mandados son correctos.
	  * Tambien filtrara las posibilidades y las ordenes que se realizaran
	  * mediante un archivo properties.
	  * 
	  * @version 1.0
	  */
		Properties prop=new Properties();
		
		//Si falla la lectura de properties lanza excepción error grave
		try {
			prop.load(new FileInputStream("properties/properties.prop"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IlegalCommandException("Can`t read the proporties file, try repair the properties load or properties syntax");
		}
		
		String tmp=prop.getProperty("commandPermited");
		tmp=tmp.substring(0, tmp.length()-1);
		String[] commandPermited=tmp.split(",");
		
		boolean equal=false;
		for(int i=0;i<commandPermited.length;i++){
			if(commandPermited[i]==command){
				equal=true;
			}
		}
		if(!equal)
			throw new IlegalCommandException("The command send to execute don't have the permision to execute.");
		
	}
}
