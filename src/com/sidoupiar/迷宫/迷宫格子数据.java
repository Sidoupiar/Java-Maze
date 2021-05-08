package com.sidoupiar.迷宫;

public final class 迷宫格子数据
{
	private int x;
	private int y;
	
	private boolean isHandled;
	private boolean wallUp;
	private boolean wallRight;
	private boolean wallDown;
	private boolean wallLeft;
	
	public 迷宫格子数据( int x , int y )
	{
		this.x = x;
		this.y = y;
		
		this.isHandled = false;
		this.wallUp = true;
		this.wallRight = true;
		this.wallDown = true;
		this.wallLeft = true;
	}
	
	
	
	public final void finishHandled()
	{
		this.isHandled = true;
	}
	
	public final void deleteWallUp()
	{
		this.wallUp = false;
	}
	
	public final void deleteWallRight()
	{
		this.wallRight = false;
	}
	
	public final void deleteWallDown()
	{
		this.wallDown = false;
	}
	
	public final void deleteWallLeft()
	{
		this.wallLeft = false;
	}
	
	
	
	public final int getX()
	{
		return this.x;
	}
	
	public final int getY()
	{
		return this.y;
	}
	
	
	
	public final boolean hasHandled()
	{
		return this.isHandled;
	}
	
	public final boolean hasWallUp()
	{
		return this.wallUp;
	}
	
	public final boolean hasWallRight()
	{
		return this.wallRight;
	}
	
	public final boolean hasWallDown()
	{
		return this.wallDown;
	}
	
	public final boolean hasWallLeft()
	{
		return this.wallLeft;
	}
	
	
	
	@Override
	public final String toString()
	{
		return this.x + "-" + this.y;
	}
	@Override
	public final boolean equals( Object object )
	{
		if( object == null ) return false;
		if( object instanceof 迷宫格子数据 )
		{
			迷宫格子数据 lattice = ( 迷宫格子数据 ) object;
			if( this.getX() == lattice.getX() && this.getY() == lattice.getY() ) return true;
			else return false;
		}
		else return false;
	}
}