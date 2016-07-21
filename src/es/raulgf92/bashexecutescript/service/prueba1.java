package es.raulgf92.bashexecutescript.service;

import java.util.ArrayList;
import java.util.List;

import es.raulgf92.bashexecutescript.BashService;

public class prueba1 extends BashService {

	static String nameService="ls";
	static String unixScriptPATH="/var/BashExecuteScript/servicios/prueba1/prueba1";
	static String winScriptPATH="C:\\\\BashExecuteScript\\servicios\\prueba1\\prueba.ps1";
	static List<String> args=new ArrayList<String>();
	
	public String response="";
	
	public prueba1() {
		super(nameService,unixScriptPATH, winScriptPATH, args);
	}

	@Override
	public String parseScriptResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void run(){
		response=super.executeService();
	}

}
