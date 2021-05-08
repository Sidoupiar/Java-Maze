package com.sidoupiar.迷宫;

public final class 迷宫地图_方块_格子数据
{
	private int x;
	private int y;
	
	private boolean isWall;
	private boolean isRoad;
	
	public 迷宫地图_方块_格子数据( int x , int y , boolean isWall )
	{
		this.x = x;
		this.y = y;
		
		this.isWall = isWall;
		this.isRoad = false;
	}
	
	
	
	public final void setRoad( boolean isRoad )
	{
		this.isRoad = isRoad;
	}
	
	
	
	public final int getX()
	{
		return this.x;
	}
	
	public final int getY()
	{
		return this.y;
	}
	
	
	
	public final boolean isWall()
	{
		return this.isWall;
	}
	
	public final boolean isRoad()
	{
		return this.isRoad;
	}
	
	
	
	@Override
	public final boolean equals( Object object )
	{
		if( object == null ) return false;
		if( object instanceof 迷宫地图_方块_格子数据 )
		{
			迷宫地图_方块_格子数据 block = ( 迷宫地图_方块_格子数据 ) object;
			if( this.getX() == block.getX() && this.getY() == block.getY() ) return true;
			else return false;
		}
		else return false;
	}
}