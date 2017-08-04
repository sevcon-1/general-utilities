
import java.awt.datatransfer.StringSelection;
import sun.awt.datatransfer.ClipboardTransferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;


data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
println "Definiton copied to clipboard"
//println data.getClass()
//Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

/* -- get clipboard context */

//Transferable data = clipboard.getContents(null);

/* -- is context string type ? */

boolean bIsText = ( ( data != null ) && ( data.isDataFlavorSupported( DataFlavor.stringFlavor ) ) );

s = (String)data.getTransferData( DataFlavor.stringFlavor );
//println s
l = s.split("\n").collect{it}
newL = []
//l.eachWithIndex {line, index -> println "List number: ${index} Line is: ${line}"}
0.step l.size(), 2, {
    newL.add(l[it])
}

//newL.each {println it}	

//println s
//StringSelection ss = new StringSelection(s.join("\n"))
// Copy to clipboard
StringSelection ss = new StringSelection(newL.join("\n"))

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
println "Definiton copied to clipboard"

println "Finishing!!"
