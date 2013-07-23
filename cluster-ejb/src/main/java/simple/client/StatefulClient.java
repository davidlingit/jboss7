package simple.client;

import java.util.logging.Logger;

import simple.ejb.cluster.StatefulRemote;
import simple.utils.LookupUtils;

public class StatefulClient {

	private static StatefulRemote ejbReference = null;
	private final static Logger LOG = Logger.getLogger(StatefulClient.class
			.getName());

	
	public int getCount() {
		int count = 0;
		if (ejbReference == null) {
			try {
				ejbReference = LookupUtils.lookupStatefulRemote();
			} catch (Exception e) {
				LOG.warning(e.getMessage());
			}
		}
		count = ejbReference.getCount();
		return count;
	}
	
	public static void main(String args[]) throws InterruptedException {

		StatefulClient client = new StatefulClient();
		while (true) {
			Thread.sleep(3000);
			LOG.info("count : " + client.getCount());
		}

	}
	

}
