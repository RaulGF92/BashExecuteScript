package es.raulgf92.bashexecutescript.exception;

public class IlegalCommandException extends Exception {

	/**
	 * IlegalCommandException
	 * Excepción que indica que el comando a ejecutar no es correcto o no esta disponible
	 * 
	 * @author raulgf
	 */
	private static final long serialVersionUID = 1L;
	
	public IlegalCommandException(){
		
	}
	
	public IlegalCommandException(String msg){
		super(msg);
	}
}
