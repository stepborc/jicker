/*
 * 
 */
package org.jicker.jgoodies.looks;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jeta.forms.components.panel.FormPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class LooksTest.
 */
public class LooksTest extends JFrame {

	/**
	 * Instantiates a new looks test.
	 */
	public LooksTest(){
		FormPanel panel = new FormPanel( "main.jfrm" );      
		getContentPane().add( panel );
		// set the size and location of this frame      
		setSize( 600, 500 );      
		setLocation( 200, 100 );      
		setVisible( true );
	}
	
	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		LooksTest l = new LooksTest();
		l.addWindowListener( new WindowAdapter()	 
		{	    public void windowClosing( WindowEvent evt )	    
		{	       System.exit(0);	    }	 });

	}

}
