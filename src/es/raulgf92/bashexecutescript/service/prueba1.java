package es.raulgf92.bashexecutescript.service;

import java.util.ArrayList;
import java.util.List;

import es.raulgf92.bashexecutescript.BashService;

public class prueba1 extends BashService {

	static String unixCommand="ls -l";
	static String winCommand="notepad";
	static List<String> args=new ArrayList<String>();
	
	public prueba1() {
		super(unixCommand, winCommand, args);
	}

}
