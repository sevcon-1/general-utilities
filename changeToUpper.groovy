//Created by DI Studio
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import org.apache.commons.cli.Option;
import java.awt.datatransfer.DataFlavor;

/*
Lebădă de vară Bütykös hattyú Cygnus olor A
2 Lebădă neagră Fekete hattyú Cygnus atratus E
3 Lebădă mică Kis hattyú Cygnus columbianus A
4 Lebădă de iarnă Énekes hattyú Cygnus cygnus A
5 Gâscă de semănătură Vetési lúd Anser fabalis A
6 Gâscă cu cioc scurt Rövidcsőrű lúd Anser brachyrhynchus A
7 Gârliă mare Nagy lilik Anser albifrons A
*/

data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
s = (String)data.getTransferData( DataFlavor.stringFlavor );
specFileL = s.split("\n").collect{it}

outL = []
specFileL.each{
    outL.add(it.replaceAll("([A-Z])", ",\$1"))
	//println out
}

StringSelection ss = new StringSelection(outL.join("\n"))

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
println "Definiton copied to clipboard"
