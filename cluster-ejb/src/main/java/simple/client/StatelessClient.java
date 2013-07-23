package simple.client;

import java.util.logging.Logger;

import simple.ejb.cluster.StatelessRemote;
import simple.utils.LookupUtils;

public class StatelessClient {

	private static StatelessRemote ejbReference = null;
	private final static Logger LOG = Logger.getLogger(StatelessClient.class
			.getName());

	
	public String getNodeName() {
		
		if (ejbReference == null) {
			try {
				ejbReference = LookupUtils.lookupStateLessRemote();
			} catch (Exception e) {
				LOG.warning(e.getMessage());
			}
		}
		return ejbReference.getNodeName();

	}
	
	public static void main(String args[]) throws InterruptedException {

		StatelessClient client = new StatelessClient();
        while (true) {
          Thread.sleep(3000);
		  LOG.info("Name : " + client.getNodeName());
        }

	}
	

}
