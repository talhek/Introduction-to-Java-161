/**
* This class represents a map of colors. Each color is represented by an english letter. 
* @author Dor and Tal.
*/
public class Map
{
	
	private char[][] map; 
	
	/**
	 * 
	 * @param map is a given array of colors for the map.
	*/
	// This is a builder for the object Map. 
	// It creates a map using a given array of chars.  
	public Map(char[][] map)
	{
		//First, make sure the given array is not null.
		if (map == null)
			this.map = null;
		else
		{
			// leng is the length of lines and columns of the array.
			int leng = map.length;
			this.map = new char[leng][leng];
			// The method runs through the given array and puts it into the new array of the object map.
			// Any given cell with a letter is put into the new map.
			// If it's a capital letter, it will convert it into a non-capital letter.
			// If it's not a letter, the letter 'z' will be put instead.
			for (int i=0; i<leng; i=i+1)
				for (int j=0; j<leng; j=j+1)
				{
					if (map[i][j]>='a' && map[i][j]<='z')
					{
						this.map[i][j]=map[i][j];
					}
					else if (map[i][j]>='A' && map[i][j]<='Z')
					{
						this.map[i][j]=(char)(map[i][j]+32);
					}
						else
						{
							this.map[i][j]='z';
						}
				}
		}
	}
	
	/**
	 * This method is the getter for the Map.
	 * @return the array that represents the map of colors.
	 */
	// This is the getter for Map. it returns the array of chars that is the map of colors.
	public char[][] getMap()
	{
		return map;
	}

	/**
	 * This method equals the current map with another map of colors.
	 * @param map is another map of colors.
	 * @return true if both maps represent the same map of color, false otherwise.
	 */
	// This method gets another given Map and checks if both maps represent the same map of color. 
	public boolean equals(Map map)
	{
		boolean equal = true;
		//First, make sure the first dimension of the maps is of the same length.
		char[][] otherMap = map.getMap();
		if (otherMap == null && this.map != null)
			equal = false;
		else
		{
			if (otherMap != null && this.map == null)
				equal = false;
			else
			{
				if (this.map.length == otherMap.length)
				{
					//Now, make sure the other map is actually a square matrix
					for (int i=0; i<otherMap.length & equal; i = i+1)
					{
						if (otherMap[i].length != this.map.length)
							equal = false;
					}
				}
				//If the length is not the same, the maps are not equal.
				else
					equal=false;
				//If both Maps are of equal length, the method will check if the content of the cells is the same.
				if (equal == true)
				{
					for (int i=0; i<this.map.length & equal; i=i+1)
						for(int j=0; j<this.map.length & equal; j=j+1)
							if(this.map[i][j] != otherMap[i][j])
								equal = false;
				}

			}
		}
		return equal;
	} 
	
	// This is a helper method. It gets an array of chars and a char.
	// It looks for the char in the array and return true if it is in the array, false otherwise.
	private boolean Contains(char[] arr, char c)
	{
		boolean found = false;
		for (int i=0; i<arr.length && !found && arr[i]!=0; i=i+1)
		{
			if (arr[i] == c)
				found = true;
		}
		return found;
	}
	
	/**
	 * This method checks how many colors the map contains.
	 * @return the number of colors the map contains.
	*/
	// This method checks how many colors the map contains. If the map is empty or points an null, it will return 0.
	public int numOfColors()
	{
		int ans =0;
		// First, check if the map is null or empty.
		if (map != null && map.length != 0)
		// If it isn't, run through the map and count the colors.
		{
			// First, create a new array one dimensional array, the size of the whole map.
			char[] colors = new char[26];
			int colorsCount = 0;
			// Now, run through the map and add only new colors to the array.
			// This will use the helper method Contains to check if the color was already put in the new array.
			for (int i=0; i<map.length; i=i+1)
			{
				for (int j=0; j<map.length; j=j+1)
				{
					if (!Contains(colors, (map[i][j])))
					{
						colors[colorsCount] = map[i][j];
						colorsCount = colorsCount+1;
					}
				}
			}
			// count the number of colors in the array colors.
			for (int i=0; i<colors.length && colors[i]!=0; i=i+1)
				ans = ans+1;
		}
		return ans;
	}
	
	//This is a helper method that checks if a point is on the map, if it's internal or on the edges or on the walls.
	//It will return the number of neighbouring points that the given point has.
	//If the point is outside the map, it will return 0.
	private int PointType(Point p)
	{
		//type represents how many neighbour the point has.
		int type;
		//First, check if the point is outside the map.
		if (p == null || p.getX()<0 | p.getY()<0 | p.getX()>map.length-1 | p.getY()>map.length-1)
		{
			type = 0;
		}
		else
		{
			//If not, check if the point is on the edges.
			if ((p.getX()==0 & p.getY()==0) | (p.getX()==map.length-1 & p.getY()==map.length-1) | (p.getX()==0 & p.getY()==map.length-1) | (p.getX()==map.length-1 & p.getY()==0))
				type = 3;
			else
			{
				//If not, check if the point is internal.
				if (p.getX() > 0 & p.getX() <map.length & p.getY()>0 & p.getY()<map.length)
					type = 8;
				else
				{
					//If not, then the point is on the walls.
					type = 5;
				}
			}
		}
		return type;
	}
	
	/**
	 * 
	 * @param p is a given point on the map.
	 * @return how many different colors the point and its neighbour contain.
	 */
	// This method returns the number of different colors of neighbour of a given point. This includes the color of the point itself.
	// It will return 0 if the input point is outside the map.
	public int numOfColors(Point p)
	{
		// ans is the number of different colors of neighbour.
		// type is how many neighbour the point has. This is checked using the helper method PointType.
		// colors2 is used to count the different colors of the neighbour. colors2Count is its counter.
		int ans = 0, type = PointType(p);
		char[] colors2 = new char [type+1];
		int colors2Count=0;
		//If the point is internal:
		if (type == 8)
		{
			for (int i=p.getX()-1; i<=p.getX()+1; i=i+1)
				for (int j=p.getY()-1; j<=p.getY()+1; j=j+1)
				{
					if (!Contains(colors2, map[i][j]))
					{
						colors2[colors2Count]=map[i][j];
						colors2Count=colors2Count+1;
					}
				}
		}
		//If the point is on the walls (check for each wall):
		if (type == 5)
		{
			if (p.getX()==0)
			{
				for (int i=0; i<=1; i=i+1)
					for (int j=p.getY()-1; j<=p.getY()+1; j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getY()==0)
			{
				for (int i=p.getX()-1; i<=p.getX()+1; i=i+1)
					for (int j=0; j<=1; j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getX()==map.length-1)
			{
				for (int i=p.getX(); i<=p.getX()-1; i=i+1)
					for (int j=p.getY()-1; j<=p.getY()+1; j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getX()==map.length-1)
			{
				for (int i=p.getX()-1; i<=p.getX()+1; i=i+1)
					for (int j=p.getY()-1; j<=p.getY(); j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
		}
		//If the point is on the edges (check for each edge):
		if (type == 3)
		{
			if (p.getX()==0 & p.getY()==0)
			{
				for (int i=0; i<=1; i=i+1)
					for (int j=0; j<=1; j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getX()==0 & p.getY()==map.length-1)
			{
				for (int i=0; i<=1; i=i+1)
					for (int j=p.getY()-1; j<=p.getY(); j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getX()==map.length-1 & p.getY()==0)
			{
				for (int i=p.getX()-1; i<=p.getX(); i=i+1)
					for (int j=0; j<=1; j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
			if (p.getY()==map.length-1 & p.getY()==map.length-1)
			{
				for (int i=p.getX()-1; i<=p.getX(); i=i+1)
					for (int j=p.getY()-1; j<=p.getY(); j=j+1)
					{
						if (!Contains(colors2, map[i][j]))
						{
							colors2[colors2Count]=map[i][j];
							colors2Count=colors2Count+1;
						}
					}
			}
		}
		
		for (int i=0; i<colors2.length && colors2[i]!=0; i=i+1)
			ans = ans+1;
		
		//If none of the above If's happens, it means the point is outside the map and ans will be 0.
		
		return ans;
	}

	/**
	 * This method will check if 2 points are neighbors.
	 * @param p1 is the first given point.
	 * @param p2 is the second given point.
	 * @return will return true if the points are neighbors, false otherwise.
	*/
	
	// This method will return true if 2 given points are neighbors or false otherwise.
	// Two points are legal neighbors if:
	// 1 - they are not the same point.
	// 2 - they are both inside the map.
	// 3 - they are in neighboring possitions.
	// 4 - their color is the same.
	public boolean legalNeighbor(Point p1, Point p2)
	{
		boolean neighbor = true;
		//First, let's make sure p1 != p2
		if ((p1.getX() == p2.getX()) & (p1.getY() == p2.getY()))
			neighbor = false;
		else
		{
			//Second, let's make sure they are both inside the map
			if ((PointType(p1)==0) | (PointType(p2)==0))
				neighbor = false;
			else
			{
				//Third, let's make sure they are neighbors on map
				int xDiff = Math.abs(p1.getX()-p2.getX()); 
				int yDiff = Math.abs(p1.getY()-p2.getY());
				if (xDiff > 1 | yDiff > 1)
					neighbor = false;
				else
				{
				// Fourth and least, let's make sure the color is the same
					if (map[p1.getX()][p1.getY()] != map[p2.getX()][p2.getY()])
						neighbor=false;
				}
			}
		}
		
		//If all terms were fulfilled, neighbor will stay true. otherwise, it will be false.
		return neighbor;
	}

	/**
	 * This method paints a block with a given color. 
	 * @param p is a given point.
	 * @param color is the new color to paint the point's block.
	*/
	
	// This method gets a point on the map and a new color.
	// It will recursively find all the points of the same color that touch each other around the given point and "paint" them with the new color.
	// To be of the same color on the same block, a point has to fulfill 1 of 3 temrs:
	// 1- be the same point. 2- be a legal neighbor. 3- be a legal neighbor of a legal neighbor.
	public void fill(Point p,char color)
	{
		if (color>='A' & color<='Z')
			color = (char)(color+32);
		int type = PointType(p);
		//If the point is outside the map and is not already in the right color, do nothing. Otherwise, go on.
		if (type!=0 && map[p.getX()][p.getY()]!=color)
		{
			//We are going to make an array of all the legal neighbors of the point, except for the point itself.
			Point[] neighbors = new Point[type];
			int Ncount = 0 ;
			for (int i=p.getX()-1; i<=p.getX()+1; i=i+1)
				for(int j=p.getY()-1; j<=p.getY()+1; j=j+1)
				{
					if (i!=p.getX() | j!=p.getY())
					{
						Point p2 = new Point(i,j);
						if(legalNeighbor(p,p2))
						{
							neighbors[Ncount]=p2;
							Ncount=Ncount+1;
						}
					}
				}
			//Now we can safely paint the given point.
			map[p.getX()][p.getY()]=color;
			
			//At this point, we need to run through the legal neighbors we found and do the same.
			for (int i=0; i<neighbors.length & neighbors[i]!=null; i=i+1)
			{
				//To prevent checking the same point twice, let's make sure it's not already colored.
				if (map[neighbors[i].getX()][neighbors[i].getY()]!=color)
				{
					fill(neighbors[i], color);
				}
			}
		}
	}
}