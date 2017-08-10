//Created by Dan P
import oracle.odi.core.persistence.transaction.support.DefaultTransactionDefinition; 
import oracle.odi.runtime.agent.RuntimeAgent;
import oracle.odi.core.OdiInstance;
import oracle.odi.core.security.Authentication ;
import oracle.odi.core.config.OdiInstanceConfig;
import oracle.odi.core.config.MasterRepositoryDbInfo ;
import oracle.odi.core.config.WorkRepositoryDbInfo ;
import oracle.odi.core.config.PoolingAttributes;

import groovy.sql.Sql;


import java.util.Collection;
import java.io.*;

// These are to be passed as parameters
    String workRep = "NDMISD_DWR1";
    Url="jdbc:oracle:thin:@NDBTSBCIDVDB01.hq.ntma.ie:1521/ODIDEV2"
    Driver="oracle.jdbc.OracleDriver"
    Master_User="NDMISD_MSTR"
    Master_Pass="il0veOdi"
    Work_User="NDMISD_DWR1"
    Odi_User="SUPERVISOR"
    Odi_Pass="Miracle42"
	
	odi_operator = "ODI_REPO_OPERATOR"
	odiop_pass = "0Ofpo9Vk26G3gYX2EUre"


	msgSummary = []
	banner = {msg->
	            msgSummary.add(msg)
                0.upto(0, {println " "})
                println "MSG#>>>>> ${msg}"
                0.upto(0, {println " "})
	}
    
    banner.call("Connecting to Master and Work")
    MasterRepositoryDbInfo masterInfo = new MasterRepositoryDbInfo(Url, Driver, Master_User,Master_Pass.toCharArray(), new PoolingAttributes());
    WorkRepositoryDbInfo workInfo = new WorkRepositoryDbInfo(workRep, new PoolingAttributes());

    banner.call("Authenticating OdiInstance")
    OdiInstance odiInstance=OdiInstance.createInstance(new OdiInstanceConfig(masterInfo,workInfo));
    
    Authentication auth = odiInstance.getSecurityManager().createAuthentication(Odi_User,Odi_Pass.toCharArray());
    odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth);
    
    banner.call("OdiInstance authenticated")
    
    banner.call("Connecting to Agent")
	RuntimeAgent agent = new RuntimeAgent(odiInstance, Odi_User, Odi_Pass.toCharArray())
	
    //sessInfo = agent.getSessionInformation()
    //println sessInfo
    
	banner.call("Fetching live sessions")
   
    banner.call("SQL Connection to db")
	def sql = Sql.newInstance (Url, odi_operator , odiop_pass)
	
	def sessL = []
	getLiveCount = {
	                return {
					        def liveSessions = []
					        sql.eachRow('SELECT SESS_NO FROM ndmisd_dwr1.vw_running_sessions') { row ->
                                //println "${row.SESS_NO}"
	                        	//v = row.SESS_NO as Long
	                            liveSessions.add(row.SESS_NO as Long)
                            }
							return liveSessions
					}
	}
	
	sessL = getLiveCount()
	banner("Number of live sessions is: ${sessL().size()}")
	 
    stop = oracle.odi.runtime.agent.invocation.StopType.valueOf('IMMEDIATE')

    sessL().each {
	            banner "Stopping session ${it}"
				agent.stopSession(it, stop)
	}
	
	banner("Number of live sessions is: ${sessL().size()}")
	
	//def con = new Sql(ds)

    sessL = []
	
    println "Message Summary"
	msgSummary.eachWithIndex{v,i -> println "${i}: ${v}"}
		
	banner.call(">>>> END OF SCRIPT <<<<")
	