package simple.ejb.cluster;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

@Stateful
@Clustered
@Remote(StatefulRemote.class)
public class ClusteredStatefulBean implements StatefulRemote {

	private final static Logger LOG = Logger
			.getLogger(ClusteredStatefulBean.class.getName());

	private  int counter=0;

	public int getCount()  {
		LOG.info("invoke getCounter()" );
		counter++;
		try {
			LOG.info("start to sleep ... ...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOG.info("problem in waiting ");
		}
		LOG.info("inner count " + counter);
		return counter;
	}
	
	@PostConstruct
    public void init() {
           System.out.println("EJB inited!");
    }

	@PrePassivate
	public void passivate() {
		LOG.info("passivate ejb component: " + this);
	}

	@PostActivate
	public void activate() {
		LOG.info("activate ejb component: " + this);
	}

}
