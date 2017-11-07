package simplegis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RailwayCatalogTest {

	RailwayCatalog c;
	
	@Test
	void importCoords() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		c = new RailwayCatalog(s + "//maps//demo_map//");	
		assertTrue(c.getPaintStatus());		
		
		c = new RailwayCatalog(s + "//maps//err_map//");	
		assertFalse(c.getPaintStatus());
		
		c = new RailwayCatalog(s + "//invalid_path//");	
		assertFalse(c.getPaintStatus());		
	}

}
