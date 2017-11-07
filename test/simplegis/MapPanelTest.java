package simplegis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapPanelTest {

	MapPanel mapPanel;
	
	@BeforeEach
	void setUp() throws Exception {
		mapPanel = new MapPanel();
	}


	@Test
	void testCalcScale() {
		mapPanel.getMapLogic().getObjectCatalog().setTopLeftCoords(0, 0);
		mapPanel.getMapLogic().getObjectCatalog().setBottomRightCoords(0, 0);
		mapPanel.calcScale();
		Assertions.assertTrue(mapPanel.getScale() == 0);

		mapPanel.getMapLogic().getObjectCatalog().setTopLeftCoords(11.0, 52.0);
		mapPanel.getMapLogic().getObjectCatalog().setBottomRightCoords(11.5, 51.5);
		mapPanel.calcScale();
		Assertions.assertTrue(mapPanel.getScale() == 34.22921629335869);		
		
		mapPanel.getMapLogic().getObjectCatalog().setTopLeftCoords(11.327082, 52.374191);
		mapPanel.getMapLogic().getObjectCatalog().setBottomRightCoords(11.927082, 51.924191);
		mapPanel.calcScale();
		Assertions.assertTrue(mapPanel.getScale() == 40.73083442755885);	
	}
	
	@Test
	public void testZoom() {	
		mapPanel.zoom(1);
		// no screen
		Assertions.assertTrue(mapPanel.getScreenXmax() + mapPanel.getScreenXmin() == 0);
		Assertions.assertTrue(mapPanel.getScreenYmax() + mapPanel.getScreenYmin() == 0);
	}

}
