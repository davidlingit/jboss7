package simple.constant;

import simple.ejb.cluster.StatefulRemote;
import simple.ejb.cluster.StatelessRemote;


/**
 * Remote interface jndi url
 * @author david.lin
 *
 */

public class RemoteConstant {

	public final static String CONTEXT_URL = "ejb:/cluster-web/";
	public final static String REMOTE_LESS_URL = CONTEXT_URL + "ClusteredStatelessBean!" + StatelessRemote.class.getName();
	public final static String REMOTE_FUL_URL = CONTEXT_URL + "ClusteredStatefulBean!" + StatefulRemote.class.getName() + "?stateful";


}
