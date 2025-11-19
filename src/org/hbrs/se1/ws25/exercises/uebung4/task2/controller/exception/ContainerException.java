package org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception;

public class ContainerException extends Exception {
	
	private String modus;
	private Integer id;
	private ExceptionType type;
	
	public ContainerException(ExceptionType type  ) {
		this.type = type;
	}
 
	@Override
	public void printStackTrace() {
		
		if ( this.type == ExceptionType.DuplicateUserStory) {
			
			System.out.println("Die User-Story mit der ID " + this.id + " ist bereits vorhanden!");
			
		} else if ( this.type == ExceptionType.InfoCastException ) {
			
		    System.out.println("Die User-Story mit der ID " + this.id + " konnte nicht gecastet werden!");
			
		} else if (this.type == ExceptionType.NonExistentUserStory) {

            System.out.println("Die User-Story mit der ID " + this.id + " wurde nicht gefunden!");
        }
	} 

	public void addID(Integer id) {
		this.id = id;
	}
	
	public enum ExceptionType {
		InfoCastException, DuplicateUserStory, NonExistentUserStory
	}


}
