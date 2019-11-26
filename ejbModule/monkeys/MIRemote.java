package monkeys;

import javax.ejb.Remote;

/**
 * @author Mickael Clavreul
 */
@Remote
public interface MIRemote {
	
	public void subscribe(String id);
	public void disconnect(String pId);
}
