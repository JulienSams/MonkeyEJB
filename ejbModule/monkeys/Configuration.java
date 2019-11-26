package monkeys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class Configuration implements iConfiguration {
	
	
	public static int [][] map;
	public static int energie;
	public static final String MAP = "MONKEYS_MAP";

	/**
	    * Charge la liste des propriétés contenu dans le fichier spécifié
	    *
	    * @param filename le fichier contenant les propriétés
	    * @return un objet Properties contenant les propriétés du fichier
	    */
	   public Properties load(String filename) throws IOException, FileNotFoundException{
		   
	      Properties properties = new Properties();
	      
	      InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("monkeys.properties");
	      
	      try{
	         properties.load(is);
	         
	         int width= properties.getProperty(MAP).replaceAll("\"", "").split(";").length;
		        int length = properties.getProperty(MAP).replaceAll("\"", "").split(";")[0].split(",").length;
		        map = new int[width][length];
		         for (int i=0; i<width;i++) {
		        	 for (int j=0; j<length;j++) {
		        		 map[i][j] = Integer.parseInt(properties.getProperty(MAP).replaceAll("\"", "").split(";")[i].split(",")[j]); 
		        		 //System.out.println("map ["+i+"]["+j+"] : "+map[i][j]);
		        		 }
		         }
		     energie = Integer.parseInt(properties.getProperty("PIRATE_MAX_ENERGIE").replaceAll("\"", ""));
	         return properties;
	     

	      }

	              finally{

	         is.close();

	      }

	   }

	
	@Override
	public int[][] getMap(){
		// TODO Auto-generated method stub
		try {
			this.load("monkeys.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.map;
	}

	@Override
	public int getEnergie() {
		// TODO Auto-generated method stub
		return this.energie;
	}
}
