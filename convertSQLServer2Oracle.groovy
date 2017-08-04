
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import org.apache.commons.cli.Option;

/*
Args:
	0 - filename
*/

def cli = new CliBuilder()
cli.with {
    h longOpt: 'help', required: false, 'show usage information' 
	x longOpt: 'exclusions', args: Option.UNLIMITED_VALUES, required: false, valueSeparator: ',' as char,'Exclude datatypes from conversion'
	//r longOpt: 'remapdatatype', args: Option.UNLIMITED_VALUES, required: false, valueSeparator: ',' as char,'Overwrites datatype mapping with custom value'
}


def opt = cli.parse(args)
if (!opt) { println "No args passed"; return }
if (opt.h) { 
  cli.usage(); 
  return 
}

//--------------------------------------------------------------------------

//args.each {println it}


def conversions = [:]

conversions = [
               'bit' : "NUMBER(1)",
               'decimal' : "NUMBER",
               'float' : "NUMBER",
			   'int' : "NUMBER",
               'nvarchar' : "VARCHAR2",
               'datetime' : "timestamp",
              ]


exludesL = opt.getOptionValues("x")
if (exludesL) {
    exludesL.each { conversions.remove(it) }
}

conversions.each { println it }
			  
//println ">>>>>>>>>>>>>RETURNING<<<<<<<<<<<<<<<<<"
//return;
			  
			  
//conversions.each{k, v -> println "key ${k} is of value ${v}"}


def specFileL = new File(args[0]).collect {it};

def List specOut = []

// Replace square brackets
specFileL.each {specOut.add(it.replaceAll("\\[dbo\\]\\.","").replaceAll("[\\[|\\]]", ""))}
specFileL = specOut

// Replace SQL Server datatypes
conversions.each{k, v -> 
    specOut = []

    //specFileL.each {println it}
    specFileL.each {specOut.add(it.replaceAll("\\s${k}(\\(|\\s)", " ${v}\$1"))}
    specFileL = specOut
}

// Clear up (max) definitions
specOut = []
specFileL.each {specOut.add(it.replaceAll("\\(max\\)", "(4000)"))}
specFileL = specOut


//specOut.each {println it}

//println ">>>>>>>>>>>>>RETURNING<<<<<<<<<<<<<<<<<"
//return;

// Change camel case to undescore separated
specOut = []
specFileL.each {specOut.add(it.replaceAll("([a-z])([A-Z]){1}","\$1_\$2")) }

specOut.each{println it}

// Copy to clipboard
StringSelection ss = new StringSelection(specOut.join("\n"))

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
println "Definiton copied to clipboard"

