# SimpleGIS
GIS map view, written in Java.

SimpleGIS is a simple example of a Java based standalone GIS application.
It is possible to view a map with following object types:
* Cities
* Lakes
* Rivers
* Roads

A specific map format (with files containg the coordinates and info files) is required.
There is a map available in the demo_demo directory.
Another map with invalid content is in the err_map directory. It is used for internal testing.

To start the application, execute src/simeplegis/LaunchSimpleGIS.java

Note:
* The application is using WorldToScreenTransform.java and DPoint.java from EXSE, Department of Geography, University of Bonn.
* THe application is created 2002. Only some minor updates are done 2017. The fault tolerance as well as the internal architecture could be improved.
