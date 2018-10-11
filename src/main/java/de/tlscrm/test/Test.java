package de.tlscrm.test;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@RequestScoped 
public class Test {

	public void logClick(ActionEvent event) {
		System.out.println("Click!");
	}
}
