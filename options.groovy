/**
 * Wrap a script and groovy jars to an executable jar.
 */
def cli = new CliBuilder()
cli.h( longOpt: 'help', required: false, 'show usage information' )
cli.m( longOpt: 'mainclass', argName: 'mainclass', required: false, args: 1, 'fully qualified main class' )
cli.c( longOpt: 'groovyc', required: false, 'Run groovyc' )

//--------------------------------------------------------------------------
def opt = cli.parse(args)
if (!opt) { println "No args passed"; return }
if (opt.h) { 
  cli.usage(); 
  return 
}

//def mainClass = opt.m
//def scriptBase = mainClass.replace( '.', '/' )
//def scriptFile = new File( scriptBase + '.groovy' )
//if (!scriptFile.canRead()) {
//   println "Cannot read script file: '${scriptFile}'"
//   return 
//}
