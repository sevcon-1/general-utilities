import org.apache.commons.cli.Option;
/**
 * Wrap a script and groovy jars to an executable jar.
 */
def cli = new CliBuilder()
//cli.h( longOpt: 'help', required: false, 'show usage information' )
//cli.m( longOpt: 'mainclass', argName: 'mainclass', required: false, args: 1, 'fully qualified main class' )
//cli.c( longOpt: 'groovyc', required: false, 'Run groovyc' )
//cli.f( longOpt: 'arguments', args: Option.UNLIMITED_VALUES, required: true, valueSeparator: ',' as char,'Unlimited arguments, separated by a comma')

// Can also populate cli as so:
cli.with {
            h longOpt: 'help', required: false, 'show usage information'
            f longOpt: 'arguments', args: Option.UNLIMITED_VALUES, required: true, valueSeparator: ',' as char,
                    'Unlimited arguments, separated by a comma'
        }

//--------------------------------------------------------------------------
def opt = cli.parse(args)
if (!opt) { println "No args passed"; return }
if (opt.h) { 
  cli.usage(); 
  return 
}

if (opt.f) {
    fName = opt.getOptionValues("f")
    fName.each {println it}
}


//Example manipulation and parsing
//def mainClass = opt.m
//def scriptBase = mainClass.replace( '.', '/' )
//def scriptFile = new File( scriptBase + '.groovy' )
//if (!scriptFile.canRead()) {
//   println "Cannot read script file: '${scriptFile}'"
//   return 
//}