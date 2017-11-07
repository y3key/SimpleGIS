package simplegis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import extern.DPoint;

class StreetTest {

	Street street;
	int num = 5;
	final int max = 999999999;


	@BeforeEach
	void setUp() throws Exception {
		street = new Street(num);
	}


	@ParameterizedTest
	@ValueSource( ints = {-1, max} )
	void testGetCoordsInt(int number) {
		try {
			street.getCoords(number);
		} catch (Exception e) {
			Assertions.assertTrue(e.getClass() == ArrayIndexOutOfBoundsException.class);
			return;
		}
		fail("No Exception.");
		return;
	}

	@ParameterizedTest
	@ValueSource( ints = {-1, max} )
	void testSetCoords(int number) {
		try {
			street.setCoords(new DPoint(), number);
		} catch (Exception e) {
			Assertions.assertTrue(e.getClass() == ArrayIndexOutOfBoundsException.class);
			return;
		}
		fail("No Exception.");		
		return;
	}

	@Test
	void testSetLineColor() {
		try {
			Color c = street.getLineColor();
			int r = 1;
			int g = 1;
			int b = 1;
			street.setLineColor(r, g, b);
			c = street.getLineColor();
			String s = "Red=" + c.getRed() + ", expected: " + r;
			assertTrue(s,c.getRed() == r);
			s = "Green=" + c.getGreen() + ", expected: " + g;
			assertTrue(s,c.getGreen() == g);
			s = "Blue=" + c.getBlue() + ", expected: " + b;
			assertTrue(s,c.getBlue() == b);
			
			// Invalid R, G, B values should be ignored:
			r = c.getRed();
			g = c.getGreen();
			b = c.getBlue();
			street.setLineColor(-1, 0, 0);
			c = street.getLineColor();
			s = "Red=" + c.getRed() + ", expected: " + r;
			assertTrue(s,c.getRed() == r);
			s = "Green=" + c.getGreen() + ", expected: " + g;
			assertTrue(s,c.getGreen() == g);
			s = "Blue=" + c.getBlue() + ", expected: " + b;
			assertTrue(s,c.getBlue() == b);	
			
			r = c.getRed();
			g = c.getGreen();
			b = c.getBlue();
			street.setLineColor(0, 0, 256);
			c = street.getLineColor();
			s = "Red=" + c.getRed() + ", expected: " + r;
			assertTrue(s,c.getRed() == r);
			s = "Green=" + c.getGreen() + ", expected: " + g;
			assertTrue(s,c.getGreen() == g);
			s = "Blue=" + c.getBlue() + ", expected: " + b;
			assertTrue(s,c.getBlue() == b);			
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}

}
