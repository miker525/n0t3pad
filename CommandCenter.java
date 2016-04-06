/**
 * n0t3p@d menubutton commands
 * by Mike Rosenberg
 * http://www.miker525.info/
 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class CommandCenter implements ActionListener{
    public CommandCenter() {
    }
    
    public void actionPerformed(java.awt.event.ActionEvent ae) 
    {
    	 if(ae.getActionCommand().equals("exit"))
    	 {
    	 	System.exit(0);
    	 }
    	 if(ae.getActionCommand().equals("open"))
    	 {
    	 	JFileChooser jfc = new JFileChooser();
    	 	jfc.setDialogTitle("Open a Text File");
    	 	int returnVal = jfc.showOpenDialog(notepadapp.mainForm);
        	if (returnVal == JFileChooser.APPROVE_OPTION) 
        	{
        		File txtfile = jfc.getSelectedFile();
        		readTextFile(txtfile);
        		
        	}
       	 }
       	 if(ae.getActionCommand().equals("save"))
       	 {
       	 	saveToTextFile();
       	 }
       	 if(ae.getActionCommand().equals("clear"))
       	 {
       	 	notepadapp.textbox1.setText("");
       	 }
       	 if(ae.getActionCommand().equals("select"))
       	 {
       	 	selectAll(notepadapp.textbox1);
       	 }
     	 if(ae.getActionCommand().equals("copy"))
     	 {
     	 	copyText();
     	 }   	
    	 if(ae.getActionCommand().equals("paste"))
    	 {
    	 	pasteText();
    	 }
    	 if(ae.getActionCommand().equals("cut"))
    	 {
    	 	cutText();
    	 }
       
    }
    
    public void readTextFile(File textFile)
    {
    	int X;
    	String fileContents = "";
    	try
    	{
    		FileInputStream ins = new FileInputStream(textFile);
    		while((X=ins.read()) != -1)
    		{
    			fileContents=fileContents+(char)X;
    		}
    		ins.close();
    		notepadapp.textbox1.append(fileContents);
    	}
    	catch(FileNotFoundException fnfe)
    	{
    		JOptionPane.showMessageDialog(notepadapp.textbox1, fnfe, "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	catch(IOException ioe)
    	{
    		JOptionPane.showMessageDialog(notepadapp.textbox1, ioe, "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public void saveToTextFile()
    {
    	try
    	{
    		JFileChooser dlg = new JFileChooser();
    		dlg.setDialogTitle("Save Text File");
    		if (JFileChooser.APPROVE_OPTION == dlg.showSaveDialog(notepadapp.mainForm))
    		{
    			FileOutputStream output = new FileOutputStream(dlg.getSelectedFile().getPath()+".txt");
    			DataOutputStream doutput = new DataOutputStream(output);
    			doutput.writeUTF(notepadapp.textbox1.getText());
    			doutput.flush();
    			doutput.close();
    			output.close();
    			System.out.println("File Written Successfully");
    			JOptionPane.showMessageDialog(notepadapp.mainForm, "Your File '" + dlg.getSelectedFile().getName() + "' has Successfully been Saved", "File Saved Successfully", JOptionPane.OK_OPTION);
    		}
    	}
    	catch(IOException ioe)
    	{
    		JOptionPane.showMessageDialog(notepadapp.mainForm, ioe, "Error", JOptionPane.OK_OPTION);
    	}
    
    	
    }
    public void selectAll(JTextArea jjbox)
    {
    	jjbox.selectAll();
    }
    public void copyText()
    {
    int start=notepadapp.textbox1.getSelectionStart(); 
    int end=notepadapp.textbox1.getSelectionEnd(); 
    String s=notepadapp.textbox1.getText(); 
    String aString=s.substring(start,end); 
    Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	Transferable transferableText=new StringSelection(aString);
	systemClipboard.setContents(transferableText,null);
    }
	public void pasteText()
	{ 
	Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    int start=notepadapp.textbox1.getSelectionStart(); 
    String startText=notepadapp.textbox1.getText().substring(0, start); 
    String endText=notepadapp.textbox1.getText().substring(start); 
    String res=startText+getClipboardContents()+endText; 
    notepadapp.textbox1.setText(res); 
    }
    public void cutText()
    {
    int start=notepadapp.textbox1.getSelectionStart(); 
    int end=notepadapp.textbox1.getSelectionEnd(); 
    String s=notepadapp.textbox1.getText(); 
    String aString=s.substring(start,end); 
    Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	Transferable transferableText=new StringSelection(aString);
	systemClipboard.setContents(transferableText,null);
	notepadapp.textbox1.replaceSelection("");
    } 

	//Method created by stackoverflow.com
	public String getClipboardContents() {
    String result = "";
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText =
      (contents != null) &&
      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
    ;
    if ( hasTransferableText ) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException ex){
        //highly unlikely since we are using a standard DataFlavor
        System.out.println(ex);
        ex.printStackTrace();
      }
      catch (IOException ex) {
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
  }

}