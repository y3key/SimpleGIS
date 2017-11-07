/*----------------    FILE HEADER  ------------------------------------------

This file is an Extension of 

WorldToScreenTransform of deegree
Copyright (C) 2001 by:
EXSE, Department of Geography, University of Bonn

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
---------------------------------------------------------------------------*/


package extern;

import java.awt.geom.*;

/**
 * the class <code>WorldToScreenTransform</code>  defines a transformation
 * to a linear coordinat system with its orgin on top/left. this can be
 * used for realising a screen mapping of geometries.
 */

public class WorldToScreenTransform 
{

    private double qx = 0;
    private double qy = 0;
    private Rectangle2D.Double sourceRect = null;
    private Rectangle2D.Double destRect = null;

   /**
    * constructor 
    * 
    * initialices the transfromation rectangles with unique values
    * (origin 0/0; widht 1; height 1)
    */
    public WorldToScreenTransform() {
    	setSourceRect( 0, 0, 1, 1 );
        setDestRect( 0, 0, 1, 1 );
    }

   /**
    * constructor
    *
    * initialices the transformation rectangle using the submitted
    * source- and destination rectangle.
    *
    * @param sourceRect is the boundary of the source geometry.
    * @param destRect is the boundary of the destination rectangle (for
    *        example a region on the screen)
    */
    public WorldToScreenTransform(Rectangle2D.Double sourceRect,Rectangle2D.Double destRect) {
        setSourceRect( sourceRect );
        setDestRect( destRect );
    }
    
   /**
    * constrctor
    *
    * @param sourceXMin minimum x-coordinate (source system) of the map.
    * @param sourceYMin minimum y-coordinate (source system) of the map.
    * @param sourceXMax maximum x-coordinate (source system) of the map.
    * @param sourceYMax maximum y-coordinate (source system) of the map.
    * @param destXMin minimum x-coordinate (destination system) of the map.
    * @param destYMin minimum y-coordinate (destination system) of the map.
    * @param destXMax maximum x-coordinate (destination system) of the map.
    * @param destYMax maximum y-coordinate (destination system) of the map.
    */
    public WorldToScreenTransform(double sourceXMin, double sourceYMin, double sourceXMax, 
    							  double sourceYMax, double destXMin, double destYMin, 
    							  double destXMax, double destYMax) {
         setSourceRect( sourceXMin, sourceYMin, sourceXMax, sourceYMax );
         setDestRect( destXMin, destYMin, destXMax, destYMax );
    }

   /**
    * sets the source rectangle
    *
    * @param sourceRect is the boundary of the source geometry.
    */
    public void setSourceRect(Rectangle2D.Double rect) {
    	
        sourceRect = rect;
        
        if ( (sourceRect != null) && (destRect != null) ) {
            calculateQX();
            calculateQY();
        }
    }
    
   /**
    * sets the source rectangle
    *
    * @param xMin minimum x-coordinate (source system) of the map.
    * @param yMin minimum y-coordinate (source system) of the map.
    * @param xMax maximum x-coordinate (source system) of the map.
    * @param yMax maximum y-coordinate (source system) of the map.
    */
    public void setSourceRect(double xMin, double yMin, double xMax, double yMax) {
    	
    	double dum = 0;
    	
    	if ( xMin > xMax ) {
    		dum = xMax;
    		xMax = xMin;
    		xMin = dum;
    	}
    	
    	if ( yMin > yMax ) {
    		dum = yMax;
    		yMax = yMin;
    		yMin = dum;
    	}

		sourceRect = new Rectangle2D.Double( xMin, yMax, xMax - xMin, yMax - yMin );

    	if ( destRect != null ) {
         	calculateQX();
            calculateQY();
        }

    }

	public Rectangle2D.Double getSourceRect() {
    	return sourceRect;
    }

   /** 
    * sets the destination rectangle.
    *
    * @param destRect is the boundary of the destination rectangle (for
    *        example a region on the screen)
    */
    public void setDestRect(Rectangle2D.Double rect) {    	
        destRect = rect;
        if ( (sourceRect != null) && (destRect != null) ) {
            calculateQX();
            calculateQY();
        }
    }
    
   /**
    * sets the destination rectangle
    *
    * @param xMin minimum x-coordinate (destination system) of the map.
    * @param yMin minimum y-coordinate (destination system) of the map.
    * @param xMax maximum x-coordinate (destination system) of the map.
    * @param yMax maximum y-coordinate (destination system) of the map.
    */ 
    public void setDestRect(double xMin, double yMin, double xMax, double yMax) {
    	
    	double dum = 0;
    	
    	if ( xMin > xMax ) {
    		dum = xMax;
    		xMax = xMin;
    		xMin = dum;
    	}
    	
    	if ( yMin > yMax ) {
    		dum = yMax;
    		yMax = yMin;
    		yMin = dum;
    	}
    	
    	destRect = new Rectangle2D.Double( xMin, yMin, xMax - xMin, yMax - yMin );
    	
    	if ( sourceRect != null ) {
   			calculateQX();
            calculateQY();
        }
        
    }
    
    public Rectangle2D.Double getDestRect() {
    	return destRect;
    }
    
 
   /**
    * executes a coordinat transformation for the submitted
    * x-coordinate of the source coordinat system.
    *
    * @param xsource, x-coordinate of a point in the source
    *        coordinate system.
    * @return the x-coordinate of the submitted value in the
    *         destination coordinate system.
    */
    public double getDestX(double xsource) {
        return destRect.x + (xsource - sourceRect.x) * qx;
    }

   /**
    * executes a coordinat transformation for the submitted
    * y-coordinate of the source coordinat system.
    *
    * @param ysource, y-coordinate of a point in the source
    *        coordinate system.
    * @return the y-coordinate of the submitted value in the
    *         destination coordinate system.
    */
    public double getDestY(double ysource) {
        return destRect.height + destRect.y - 
                   ( ysource - (sourceRect.y - sourceRect.height) ) * qy ;
    }
    
   /**
    * executes a coordinat transformation for the submitted
    * point of the source coordinat system.
    *
    * @param a point in the source coordinate system.
    * @return the location of the submitted point in the destination 
    *         coordinate system.
    */
    public DPoint getDestPoint(DPoint point) {
            double x = getDestX(point.x);
            double y = getDestY(point.y);
            return new DPoint(x,y);
    }

   /**
    * executes a coordinat transformation for the submitted
    * x-coordinate of the destination coordinate system.
    *
    * @param xsource, x-coordinate of a point in the destination
    *        coordinate system.
    * @return the x-coordinate of the submitted value in the
    *         source coordinate system.
    */
    public double getSourceX(double xdest) {
            return (xdest - destRect.x) / qx + sourceRect.x;
    }

   /**
    * executes a coordinat transformation for the submitted
    * y-coordinate of the destination coordinate system.
    *
    * @param ysource, y-coordinate of a point in the destination
    *        coordinate system.
    * @return the y-coordinate of the submitted value in the
    *         source coordinate system.
    */
    public double getSourceY(double ydest) 
    {
    	return -1 * ((ydest - destRect.height - destRect.y) / qy - sourceRect.y + sourceRect.height);
    }

   /**
    * executes a coordinat transformation for the submitted
    * point of the destination coordinate system.
    *
    * @param a point in the destination coordinate system.
    * @return the location of the submitted point in the source 
    *         coordinate system.
    */
    public DPoint getSourcePoint(DPoint point) 
    {
            double x = getSourceX(point.x);
            double y = getSourceY(point.y);
            return new DPoint(x,y);
    }

   /**
    * calculates the relation between the width of the
    * destination and the source coordinate system.
    */
    protected void calculateQX() 
    {
            qx = destRect.width/sourceRect.width;
    }

   /**
    * calculates the relation between the height of the
    * destination and the source coordinate system.
    */
    protected void calculateQY() 
    {
            qy = destRect.height/sourceRect.height;
    }


}
