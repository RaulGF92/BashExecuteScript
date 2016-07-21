package es.raulgf92.bashexecutescript;

import java.util.ArrayList;
import java.util.List;

import es.raulgf92.bashexecutescript.exception.CommandExecuteException;
import es.raulgf92.bashexecutescript.exception.IlegalCommandException;

public abstract class BashService extends Thread {
	/**
	 * BashService
	 * 
	 * @author Raulgf92
	 * 
	 * Clase abstracta que sirve como estructura inicial para la realizacion de un servicio para la ejecución.
	 * Esta clase sera necesaria heredarla para la ejecución posterior de servicios. Ya que sera solicitada por un usuario.
	 * 
	 * @version 1.0
	 */
	String serviceName;
	String unixPathScriptService;
	String winPathScriptService;
	List<String> args;
	
	public BashService(String serviceName,String unixCommand,String winCommand,List<String>args){
		this.winPathScriptService=winCommand;
		this.unixPathScriptService=unixCommand;
		this.serviceName=serviceName;
		this.args=args;
	}
	
	
	//Metodo a implementar por cada servicio. En el se debera estudiar lo que devuelve el script 
	public abstract String parseScriptResponse();
	
	public String executeService(){
		/**
		 * Execute Service
		 * 
		 * Metodo que ejecuta la clase Controller bash. Este pasa el directorio del script asi como los argumentos.
		 * debera capturar todos los fallos posibles en la ejecución del servicio. El fallo en la ejecución del mismo
		 * no esta considerado como fallo y debera ser estudiado por la función parseScriptResponse. Que debera ser 
		 * implementado por el usuario. De no ser asi. El programa devolvera sola las ejecuciones sin tratar.
		 * 
		 * @version 1.0
		 */
		String response="";
		
		ControllerBash execute=new ControllerBash();
		try {
			response=execute.ExecuteCommand(this);
		} catch (CommandExecuteException | IlegalCommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	
}
