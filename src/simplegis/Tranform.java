package simplegis;

import extern.*;

/**
 * City class for handling map objects of type City. Copyright: Copyright (c)
 * 2002
 * 
 * @author Stefan Friese
 * @version 1.1
 */

public class Tranform {

	ObjectCatalog myObjects = new ObjectCatalog();

	WorldToScreenTransform factor = new WorldToScreenTransform(myObjects.getTopLeft().x, myObjects.getBottomRight().y,
			myObjects.getBottomRight().x, myObjects.getTopLeft().y, 100, 50, 800, 500);

}