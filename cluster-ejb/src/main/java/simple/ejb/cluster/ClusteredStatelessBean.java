package simple.ejb.cluster;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.Clustered;

@Stateless
@Clustered
@Remote(StatelessRemote.class)
public class ClusteredStatelessBean implements StatelessRemote{
	private final static Logger LOG = Logger.getLogger(ClusteredStatelessBean.class.getName());
	private  int nodeId =0;

	public String getNodeName() {
		LOG.info("invoke getNodeName()");
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");

			return jbossNodeName != null ? jbossNodeName : InetAddress
					.getLocalHost().getHostName();

		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int getNoteId() {
		nodeId ++;
		return nodeId;
	}

}
