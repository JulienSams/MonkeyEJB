package monkeys;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class MonkeyIsland
 * @author Mickael Clavreul
 */
@Stateful
public class MonkeyIsland implements MIRemote {

	private Island myLand;
	
	@EJB
	CommunicationLocal comLocal;
	
	@PersistenceContext(unitName = "MonkeyDS")
	EntityManager manager;
	
	@EJB
	iConfiguration config;
	/**
     * Default constructor
     */
    public MonkeyIsland() {
    }

	@Override
	public void subscribe(String id) {
		newGame(id);
	}
	
	@Override
	public void disconnect(String id) {
		
	}

	private void newGame(String name) {
		Island island = manager.find(Island.class, Integer.valueOf(name));
		if (island != null) {
			myLand=island;
		} else {
			island = new Island();
			island.setMap(config.getMap());
			island.setId(Integer.valueOf(name));
			manager.merge(island);
		}
		comLocal.sendMap(island.getMap(), String.valueOf(island.getId()));
		}
}
