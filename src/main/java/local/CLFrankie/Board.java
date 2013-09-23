package local.CLFrankie;

import java.util.ArrayList;

public class Board
{
	int[][] mData;
	int mDimension;
	
	public Board(int dimension)
	{
		this.mDimension = dimension;

		mData = new int[this.mDimension][this.mDimension];
		for(int i = 0; i < this.mData.length; i++)
			for(int j = 0; j < this.mData[i].length; j++)
				this.mData[i][j] = -1;
	}

	public Board(int[][] data)
	{
		this.mDimension = data.length;
		this.mData = data;
	}

	public void place(int x, int y, int val)
	{
		this.mData[x][y] = val;
	}
	
	public void unplace(int x, int y)
	{
		this.mData[x][y] = -1;
	}

	public int[] getRow(int x, int y)
	{
		int[] ret = new int[this.mDimension];
		for(int i = 0; i < this.mDimension; i++)
			ret[i] = this.mData[x][i];

		return ret;
	}

	public int[] getCol(int x, int y)
	{
		int[] ret = new int[this.mDimension];
		for(int i = 0; i < this.mDimension; i++)
			ret[i] = this.mData[i][y];

		return ret;
	}

	public int[][] getSec(int x, int y)
	{
		int[][] ret = new int[3][3];

		//get section coordinate
		int secX = x / 3;
		int secY = y / 3;

		for(int localX = 0; localX < 3; localX++)
			for(int localY = 0; localY < 3; localY++)
				ret[localX][localY] = this.mData[(secX * 3) + localX][(secY * 3) + localY];
		return ret;	
	}
		
	public boolean isFinished()
	{
		for(int i = 0; i < this.mData.length; i++)
			for(int j = 0; j < this.mData[i].length; j++)
				if(this.mData[i][j] == -1)
					return false;
		return true;	
	}

	public ArrayList<Integer[]> getEmpties()
	{
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();	
		Integer[] point = null;

		for(int i = 0; i < this.mData.length; i++)
		{
			for(int j = 0; j < this.mData[i].length; j++)
			{
				if(this.mData[i][j] == -1)
				{
					point = new Integer[2];
					point[0] = i;
					point[1] = j;
					ret.add(point); 
				}
			}
		}

		return ret;
	}
}
