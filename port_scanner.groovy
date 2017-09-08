//
//Hacked by Dan Potter
//
//Polls the specified server every n seconds. Can be changed below where indicated.
//
import java.net.*;

    startPortRange=1433;
    stopPortRange=1433;
  
    // Change this for longer or shorter pause between pings. Value in milliseconds
    sleepTime=5000
  
    //
    // Uncomment or add in to change server
    //
    //ipAddress="NTMADEVODI01"; //Dev App Server
    //ipAddress="NTMASTGODI01"; //Staging App Server
    //ipAddress="NTMAODI01"; //Production App Server
    ipAddress="SBCIDPUATDB01"; //Production App Server
    

    tryCount = 0;

    connOK = 1;
    println("Starting port scan" );
while (connOK) {

    println "Scanning for: " + ipAddress;

    for(i in startPortRange..stopPortRange)
    {
        tryCount++;
        
        //if (tryCount==5) { break }
        
        println new Date()
        println ("Scanning port: " + i);
                
        try
        {
            Socket ServerSok = new Socket(ipAddress,i);
            
            // WHAT TO DO HERE IF SUCCESS?
            // Write key value into hash for agent and time
            
            // println("Port in use: " + i );
            println("Port in use: " +  i);
            //connOK=0;
            
            ServerSok.close();
        }
        catch (Exception e)
        {
            println( e );
        }
        
        // At given time flush heartbeat hash to table
       
    }
sleep(sleepTime) // Change this to alter polling period. Given in milliseconds.
}   
    //assert tryCount==5;

