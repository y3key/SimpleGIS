package simplegis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class WaterAreaCatalogTest {

	WaterAreaCatalog c;
	
	@Test
	void importCoords() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		c = new WaterAreaCatalog(s + "//maps//demo_map//");	
		assertTrue(c.getPaintStatus());		
		
		System.out.println("Test with corrupted map:");
		c = new WaterAreaCatalog(s + "//maps//err_map//");	
		assertFalse(c.getPaintStatus());
		
		System.out.println("Test with wrong path to map:");
		c = new WaterAreaCatalog(s + "//invalid_path//");	
		assertFalse(c.getPaintStatus());		
	}
}
