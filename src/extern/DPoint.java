package extern;
import java.awt.geom.*;
import java.io.*;


public class DPoint extends Point2D.Double implements Externalizable
{
      public DPoint()
      {}
      public DPoint(double x, double y)
      {
            super(x,y);
      }
      public void writeExternal(ObjectOutput out) throws IOException
      {
            out.writeDouble(x);
            out.writeDouble(y);
      }
      public void readExternal(ObjectInput in) throws IOException,ClassNotFoundException
      {
            x=in.readDouble();
            y=in.readDouble();
      }

}
