
import java.awt.datatransfer.StringSelection;
import sun.awt.datatransfer.ClipboardTransferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

msgBox = {titleBar, message -> JOptionPane.showMessageDialog(null, message, titleBar, JOptionPane.INFORMATION_MESSAGE)}


data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

/* -- is context string type ? */
boolean bIsText = ( ( data != null ) && ( data.isDataFlavorSupported( DataFlavor.stringFlavor ) ) );

/* If string then do do action */
if (bIsText) {
    s = (String)data.getTransferData( DataFlavor.stringFlavor );
    l = s.split("\n").collect{it}
    newL = []
    
    0.step l.size(), 2, {
        newL.add(l[it])
    }
	// Replace to clipboard
    StringSelection ss = new StringSelection(newL.join("\n"))
    
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
     // Print OK    
    msgBox("Information", "Selection has been copied to clipboard")
    
} else {
        msgBox("Error", "Clipboard contents are not string datatype")
}
