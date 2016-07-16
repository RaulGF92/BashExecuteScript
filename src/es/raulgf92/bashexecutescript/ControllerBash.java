package es.raulgf92.bashexecutescript;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	public String ExecuteCommand(BashService servicio) throws CommandExecuteException,IlegalCommandException{
		/**
		 * ExecuteCommand
		 * 
		 * Modifica las ordenes para realizar una ejecución en un entorno o en otro. Es la preparación de la ejecución
		 * 
		 * @version 1.0
		 */
		String response="";
		String comand;
		List<String> args;
		
		//preguntarse que Sistema operativo esta corriendo
		String osName = System.getProperty ( "os.name" );
		
		
		if(osName.contains("Windows")){
			comand=servicio.winCommand;
			args=servicio.args;
			
			//Estudio del comando
			checkCommand(comand, args);
			
			comand="CMD /C "+servicio.winCommand;
		}else{
			comand=servicio.unixCommand;
			args=servicio.args;
			
			//Estudio del comando
			checkCommand(comand, args);
		}
		
		
		
		//Si llega aqui no hay ningun tipo de excepcion
		response=makeCommand(comand, args);
		
		//Si no devuelve nada genera excepción
		if(response=="")
			throw new CommandExecuteException();
		
		return response;
		
	}
	
	private String makeCommand(String command,List<String> args) throws CommandExecuteException{
		/**
		 * MakeCommand
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
		String response="";
		ProcessBuilder processBuilder;
		Process process;
		
		
		
		
			String commandComplete=command+"";
			for(int i=0;i<args.size();i++){
				commandComplete.concat("");
				commandComplete.concat(args.get(i));
			}
			
			try{

				process = Runtime.getRuntime().exec(commandComplete); 
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				String line;
				while ((line = br.readLine()) != null) {
					response += line +"\n";
				}
				
			}catch(Exception e){
				e.printStackTrace();
				throw new CommandExecuteException();
			}
		    System.out.println("Program terminated execute");
		
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
			if(commandPermited[i].compareTo(command)==0){
				equal=true;
			}
		}
		if(!equal){
			throw new IlegalCommandException("The command send to execute don't have the permision to execute.");
		}
	}
}
