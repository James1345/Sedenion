package com.alexandria.euclid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * A simple Read,Evaluate,Print Loop (REPL). 
 * 
 * Passes the Evaluate to an external parser class (so can in theory be applied to many projects).
 * Remembers previous command (can be accessed with VK_UP)
 * 
 * @author James
 *
 */
public class REPL extends JFrame implements KeyListener{


	private static final long serialVersionUID = 2410469638305799759L;

	private JEditorPane output = new JEditorPane();
	private JTextField input = new JTextField();

	private JScrollPane outputPanel = new JScrollPane(output);
	
	private JPanel rootPanel = new JPanel();
	
	private String outputStr = "";
	private Vector<String> inputStr = new Vector<String>();
	private int previous = -1;
	
	private static String INPUTMARKER = "i>";
	private static String OUTPUTMARKER = "o>";
	
	private Parser parser;

	
	public REPL(Parser p){
		
		output.setEditable(false);
		input.addKeyListener(this);
		
		outputPanel.setPreferredSize(new Dimension(300, 300));
		outputPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		rootPanel.setLayout(new BorderLayout());
		rootPanel.add(BorderLayout.CENTER, outputPanel);
		rootPanel.add(BorderLayout.SOUTH, input);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Get the size of the screen
		this.setLocation((int)(0.2*dim.width), (int)(0.1*dim.height));//set position
		
		this.parser = p;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("REPL");
		this.setContentPane(rootPanel);
		this.pack();
		this.setVisible(true);

	}
	

		

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP: 
			 //set input to previous command and decrement number of previous marker
			if (previous >= 0){
				input.setText(inputStr.elementAt(previous));
				previous -= 1;
			}
			break;
		case KeyEvent.VK_DOWN:
			//set input to next command (or "") and increment marker
			if (previous < inputStr.size()-2){
				input.setText(inputStr.elementAt(previous+2));
				previous += 1;
			}
			else if (previous < inputStr.size()-1){
				input.setText("");
				previous += 1;
			}
			break;
		case KeyEvent.VK_ENTER:
			//Add input to list of inputs and set previous marker to max
			inputStr.add(input.getText());
			
			outputStr = outputStr + inputStr.lastElement() + "\n" 
			+ parser.parse(inputStr.lastElement()) + "\n";
			
			output.setText(outputStr);
			input.setText("");
			previous = inputStr.size()-1;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

}
