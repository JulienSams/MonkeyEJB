package monkeys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ejb.Local;

@Local
public interface iConfiguration {
	
	public int[][] getMap();
	public int getEnergie();

}
