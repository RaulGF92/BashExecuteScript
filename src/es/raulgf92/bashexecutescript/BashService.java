package es.raulgf92.bashexecutescript;

import java.util.ArrayList;
import java.util.List;

public abstract class BashService {
	
	String unixCommand;
	String winCommand;
	List<String> args;
	
	public BashService(String unixCommand,String winCommand,List<String>args){
		this.winCommand=winCommand;
		this.unixCommand=unixCommand;
		this.args=args;
	}
}
