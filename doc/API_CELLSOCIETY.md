###We critiqued the large aspects of the API from Team 1's CellSociety:
####Comments on each line show what we think access should be

```java
package Simulation;
public abstract class Simulation { // since this doesn't have any instance variables, perhaps make this an enum class 
      public abstract int getNextStateOfCell(Cell cell, ArrayList<Cell> neighbors); //public to other packages
    public void setCurrentGrid(Cell[][] grid) //private
    public void setNextGrid(Cell[][] grid) //private
    public void update() //public
    public ArrayList<Cell> getNeighbors(Cell cell) //package-private
    public void updateColor() //private
    public void moveNextToCurrent() //private
    public HashMap<Integer, Color> getMyColorLookupTable() //public
    public HashMap<String, Integer> getMyStateLookupTable() //public
}

package Cell;
public class Cell{ 
      public Cell (Polygon shape) //public
    public int getRow() //public
    public void setRow(int row) //package-private
    public int getCol() //public
    public void setCol(int col) //package-private
    public int getState() //public
    public void setState(int state) //public
    public Polygon getShape() //public
    public void setColor(Paint color) //public 
}

package view;
public class XMLParser { 
      public XMLParser(String filepath) //public
    public String getSimulationType() //
    public int getGridX() //package-private
    public int getGridY() //package-private
    public HashMap<String, Double> getRandomInfo() //package-private
    public ArrayList<String> getStates() //package-private
    public ArrayList<Double> getStateProportions() //package-private
    public boolean isItBasedOnStates() //private
}

package Grid;
public abstract class Grid { 
      public Grid(String filePath, int displaySize) //public
    public abstract void initialize();  //package-private
    public HashMap<String, Integer> getSimulationMap() //public
    public Simulation getSimulation(String sim, HashMap<String, Double> map) //public
    public Cell getSpecificCell(Polygon shape)  //package-private
    public void initializeCurrentNext(Polygon shape, int row, int col) //private
    public void moveNexttoCurrent()  //private
    public boolean checkGameEnding()  //public
    public Cell[][] getMyCurrentState()  //private
    public void updateGrid()  //public
    public String getSimulationName() //public
    public void display(Group root) //public
    public void unDisplay(Group root) //public
    public void setInitialGridColors() //private
}
```