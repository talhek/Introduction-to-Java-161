/**
 * This class represents the object Point. it's a point in space that is represented by line and column.
 * @authors Dor and Tal.
*/
public class Point
{

	//This class represents a point in space. x represents the line number and y represents the column.
	private int x;
	private int y;	
	
	/**
	 * This method is the builder for the point in space.
	 * @param x represents the line number.
	 * @param y represents the column.
	 */
	// 	This method creates a point in space, based on given line and column number. if the line/column are null, it will return
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return line number of the point;
	 */
	// This is  the getter for x - it returns the line number of the point.
	public int getX()
	{
		return x;
	}
	
	/**
	 * @return column number of the point;
	 */
	// This is the getter for the y - it returns the column number of the point.
	public int getY()
	{
		return y;
	}
}
