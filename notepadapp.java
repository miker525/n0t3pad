/**
 * n0t3p@d
 * by Mike Rosenberg
 * http://www.miker525.info/
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class notepadapp {
    //the necessities for the app
    static JFrame mainForm;
    static JTextArea textbox1 = new JTextArea(26, 60);
    //static JScrollPane scrolltxt = new JScrollPane(textbox1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    static JScrollPane scrolltxt = new JScrollPane(textbox1);
    static JMenuBar mnubar;
    static JMenu fileMenu, editMenu;
    static JMenuItem openFile, saveFile, exitApp, selectAll, copyText, cutText, pasteText, clearScreen;
    public static void makeForm()
    {
    	//form information
    	mainForm = new JFrame("n0t3p@d");
    	mainForm.setSize(500, 500);
    	mainForm.setResizable(false);
    	mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel pane = new JPanel();
    	//menu setup
    	mnubar = new JMenuBar();
    	fileMenu = new JMenu("File");
    	editMenu = new JMenu("Edit");
    	openFile = new JMenuItem("Open");
    	saveFile = new JMenuItem("Save");
    	exitApp = new JMenuItem("Exit");
    	fileMenu.add(openFile);
    	fileMenu.add(saveFile);
    	fileMenu.add(exitApp);
    	selectAll = new JMenuItem("Select All");
    	copyText = new JMenuItem("Copy");
    	cutText = new JMenuItem("Cut");
    	pasteText = new JMenuItem("Paste");
    	clearScreen = new JMenuItem("Clear Screen");
    	editMenu.add(selectAll);
    	editMenu.add(copyText);
    	editMenu.add(cutText);
    	editMenu.add(pasteText);
    	editMenu.add(clearScreen);
    	//add commands to Menu Items
    	exitApp.addActionListener(new CommandCenter());
    	exitApp.setActionCommand("exit");
    	openFile.addActionListener(new CommandCenter());
    	openFile.setActionCommand("open");
    	saveFile.addActionListener(new CommandCenter());
    	saveFile.setActionCommand("save");
    	clearScreen.addActionListener(new CommandCenter());
    	clearScreen.setActionCommand("clear");
    	copyText.addActionListener(new CommandCenter());
    	copyText.setActionCommand("copy");
    	cutText.addActionListener(new CommandCenter());
    	cutText.setActionCommand("cut");
    	pasteText.addActionListener(new CommandCenter());
    	pasteText.setActionCommand("paste");
    	selectAll.addActionListener(new CommandCenter());
    	selectAll.setActionCommand("select");
    	//add items to menu
    	mnubar.add(fileMenu);
    	mnubar.add(editMenu);
    	//textbox creation
      	textbox1.setEditable(true);
    	textbox1.setWrapStyleWord(true);
    	textbox1.setLineWrap(true);
    	textbox1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
    	scrolltxt.createHorizontalScrollBar();
       	//Pane information
    	pane.setLayout(new FlowLayout());
    	pane.setPreferredSize(new Dimension(100, 100));
    	//scrolltxt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrolltxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	//pane.add(scrolltxt, BorderLayout.CENTER);
    	pane.add(scrolltxt);
    	//finishing form setup
    	mainForm.setJMenuBar(mnubar);
    	mainForm.getContentPane().add(pane);
    	mainForm.setVisible(true);
    }
    public static void main(String[] args) {
    		makeForm();
        	System.out.println("Welcome to n0t3p@d");
    }
}
