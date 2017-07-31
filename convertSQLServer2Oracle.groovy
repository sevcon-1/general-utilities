
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

def specFileL = new File(args[0]).collect {it};

def specOut = []
//specFileL.each {println it.replaceAll("([a-z])([A-Z]){1}","\$1_\$2") }
specFileL.each {specOut.add(it.replaceAll("([a-z])([A-Z]){1}","\$1_\$2")) }

specOut.each{println it}

StringSelection ss = new StringSelection(specOut.join("\r\n"))

//println ss

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);


