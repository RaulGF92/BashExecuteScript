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
 * Permite la ejecuci�n de servicios previamente configurados en diferentes bash's.
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
 * @version 2.0
 * 
 */
class ControllerBash {

	protected ControllerBash(){
		
	}
	
	public String ExecuteCommand(BashService servicio) throws CommandExecuteException,IlegalCommandException{
		
		/**
		 * ExecuteCommand
		 * 
		 * Modifica las ordenes para realizar una ejecuci�n en un entorno o en otro. Es la preparaci�n de la ejecuci�n
		 * 
		 * @version 2.0
		 */
		
		String response="";
		String serviceScript;
		String comand;
		List<String> args;
		Properties prop=this.getProperties();
		
		//preguntarse que Sistema operativo esta corriendo
		String osName = System.getProperty ( "os.name" );
		
		
		if(osName.contains("Windows")){
			serviceScript=servicio.winPathScriptService;
			args=servicio.args;
			
			//Estudio del comando
			checkCommand(servicio.serviceName, args);
			
			comand="powershell /C "+serviceScript+" "+args;
		}else{
			comand=servicio.unixPathScriptService;
			args=servicio.args;
			
			//Estudio del comando
			checkCommand(comand, args);
		}
		
		
		
		//Si llega aqui no hay ningun tipo de excepcion
		response=makeCommand(comand, args);
		
		//Si no devuelve nada genera excepci�n
		if(response=="")
			throw new CommandExecuteException();
		
		return response;
		
	}
	
	private String makeCommand(String command,List<String> args) throws CommandExecuteException{
		/**
		 * MakeCommand
		 * 
		 * Contruye un proceso nuevo que ejecuta el comando propocionado,a�adiendole
		 * la lista completa de todos los argumentos.
		 * 
		 * Devuelve la resoluci�n de la ejecucion para ser tratado. Si proporciona error o es incorrecto
		 * devuelve el fallo.
		 * 
		 * @version 2.0
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
	
	private Properties getProperties() throws IlegalCommandException{
		
		Properties prop=new Properties();
		
		//Si falla la lectura de properties lanza excepci�n error grave
		try {
			prop.load(new FileInputStream("properties/properties.prop"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IlegalCommandException("Can`t read the proporties file, try repair the properties load or properties syntax");
		}
		
		return prop;
	}
	
	private void checkCommand(String command, List<String> args)throws IlegalCommandException {
	 /**
	  * CheckCommand
	  * 
	  * Metodo que filtrara y comprobara si los comandos mandados son correctos.
	  * Tambien filtrara las posibilidades y las ordenes que se realizaran
	  * mediante un archivo properties.
	  * 
	  * @version 2.0
	  */
		
		Properties prop=this.getProperties();
		
		String tmp=prop.getProperty("servicePermited");
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
