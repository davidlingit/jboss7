package simple.utils;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import simple.constant.RemoteConstant;
import simple.ejb.cluster.StatefulRemote;
import simple.ejb.cluster.StatelessRemote;
//import org.jboss.ejb.client.ContextSelector;
//import org.jboss.ejb.client.EJBClientContext;
//import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
//import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public class LookupUtils {

	private final static Logger Log = Logger.getLogger(LookupUtils.class.getName());
	private static Context context = null;
	
	static {
	   if (context == null) {
		   try {
			  context = initContext();
			} catch (NamingException e) {
				Log.warning("Error in initialze jndi context " + e);
			}
	    }
	 //  initializeEJBClientContext();
	   
	}

	
	public static void setContext(Context context) {
		LookupUtils.context = context;
	}
	
	
	public static StatelessRemote lookupStateLessRemote() throws NamingException {
 	    Log.info(RemoteConstant.REMOTE_LESS_URL);
		return (StatelessRemote) context.lookup(RemoteConstant.REMOTE_LESS_URL);
    }
	
	
	public static StatefulRemote lookupStatefulRemote() throws NamingException {
 	    Log.info(RemoteConstant.REMOTE_FUL_URL);
		return (StatefulRemote) context.lookup(RemoteConstant.REMOTE_FUL_URL);
    }
	
	
    public static Context initContext() throws NamingException {
	     Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		 jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		 return new InitialContext(jndiProperties);
    }
    

}
