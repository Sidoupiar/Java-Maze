package com.sidoupiar.迷宫;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class 迷宫生成器
{
	public static final 迷宫地图接口 CreateDeepthMaze( int width , int height , long seed )
	{
		List<List<迷宫格子数据>> lattices = new ArrayList<List<迷宫格子数据>>();
		List<迷宫格子数据> line;
		迷宫格子数据 lattice;
		for( int y = 0 ; y < height ; y ++ )
		{
			line = new ArrayList<迷宫格子数据>();
			for( int x = 0 ; x < width ; x ++ )
			{
				lattice = new 迷宫格子数据( x , y );
				line.add( lattice );
			}
			lattices.add( line );
		}
		List<迷宫格子数据> buffer = new ArrayList<迷宫格子数据>();
		List<迷宫格子数据> road = new ArrayList<迷宫格子数据>();
		
		lattice = lattices.get( 0 ).get( 0 );
		lattice.finishHandled();
		lattice.deleteWallLeft();
		buffer.add( lattice );
		road.add( lattice );
		
		Random random = new Random( seed );
		List<迷宫格子数据> randomList = new ArrayList<迷宫格子数据>();
		int x;
		int y;
		迷宫格子数据 bufferLattice;
		while( 迷宫生成器.HasLatticeWithNoHandled( lattices ) )
		{
			randomList.clear();
			lattice = buffer.get( buffer.size()-1 );
			x = lattice.getX();
			y = lattice.getY();
			if( y > 0 )
			{
				bufferLattice = lattices.get( y-1 ).get( x );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( x < width-1 )
			{
				bufferLattice = lattices.get( y ).get( x+1 );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( y < height-1 )
			{
				bufferLattice = lattices.get( y+1 ).get( x );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( x > 0 )
			{
				bufferLattice = lattices.get( y ).get( x-1 );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( randomList.size() > 0 )
			{
				bufferLattice = randomList.get( (int)(random.nextDouble()*randomList.size()) );
				bufferLattice.finishHandled();
				if( bufferLattice.getX() == x )
				{
					if( bufferLattice.getY() > y )
					{
						lattice.deleteWallDown();
						bufferLattice.deleteWallUp();
					}
					else
					{
						lattice.deleteWallUp();
						bufferLattice.deleteWallDown();
					}
				}
				else
				{
					if( bufferLattice.getX() > x )
					{
						lattice.deleteWallRight();
						bufferLattice.deleteWallLeft();
					}
					else
					{
						lattice.deleteWallLeft();
						bufferLattice.deleteWallRight();
					}
				}
				buffer.add( bufferLattice );
				if( bufferLattice.getX() == width-1 && bufferLattice.getY() == height-1 )
				{
					road.clear();
					for( 迷宫格子数据 l : buffer ) road.add( l );
				}
			}
			else
			{
				if( buffer.size() > 1 ) buffer.remove( buffer.size()-1 );
				else break;
			}
		}
		
		line = lattices.get( lattices.size()-1 );
		lattice = line.get( line.size()-1 );
		lattice.deleteWallRight();
		
		return new 迷宫地图_方块( width , height , lattices , road );
	}
	
	public static final 迷宫地图接口 CreateRandomDeepthMaze( int width , int height , long seed )
	{
		List<List<迷宫格子数据>> lattices = new ArrayList<List<迷宫格子数据>>();
		List<迷宫格子数据> line;
		迷宫格子数据 lattice;
		for( int y = 0 ; y < height ; y ++ )
		{
			line = new ArrayList<迷宫格子数据>();
			for( int x = 0 ; x < width ; x ++ )
			{
				lattice = new 迷宫格子数据( x , y );
				line.add( lattice );
			}
			lattices.add( line );
		}
		List<迷宫格子数据> buffer = new ArrayList<迷宫格子数据>();
		List<迷宫格子数据> road = new ArrayList<迷宫格子数据>();
		
		lattice = lattices.get( 0 ).get( 0 );
		lattice.finishHandled();
		lattice.deleteWallLeft();
		buffer.add( lattice );
		road.add( lattice );
		
		Random random = new Random( seed );
		List<迷宫格子数据> randomList = new ArrayList<迷宫格子数据>();
		int x;
		int y;
		迷宫格子数据 bufferLattice;
		while( 迷宫生成器.HasLatticeWithNoHandled( lattices ) )
		{
			randomList.clear();
			lattice = buffer.get( (int)(random.nextDouble()*buffer.size()) );
			x = lattice.getX();
			y = lattice.getY();
			if( y > 0 )
			{
				bufferLattice = lattices.get( y-1 ).get( x );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( x < width-1 )
			{
				bufferLattice = lattices.get( y ).get( x+1 );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( y < height-1 )
			{
				bufferLattice = lattices.get( y+1 ).get( x );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( x > 0 )
			{
				bufferLattice = lattices.get( y ).get( x-1 );
				if( ! bufferLattice.hasHandled() ) randomList.add( bufferLattice );
			}
			if( randomList.size() > 0 )
			{
				bufferLattice = randomList.get( (int)(random.nextDouble()*randomList.size()) );
				bufferLattice.finishHandled();
				if( bufferLattice.getX() == x )
				{
					if( bufferLattice.getY() > y )
					{
						lattice.deleteWallDown();
						bufferLattice.deleteWallUp();
					}
					else
					{
						lattice.deleteWallUp();
						bufferLattice.deleteWallDown();
					}
				}
				else
				{
					if( bufferLattice.getX() > x )
					{
						lattice.deleteWallRight();
						bufferLattice.deleteWallLeft();
					}
					else
					{
						lattice.deleteWallLeft();
						bufferLattice.deleteWallRight();
					}
				}
				buffer.add( bufferLattice );
				if( bufferLattice.getX() == width-1 && bufferLattice.getY() == height-1 )
				{
					road.clear();
					for( 迷宫格子数据 l : buffer ) road.add( l );
				}
			}
			else
			{
				if( buffer.size() > 1 ) buffer.remove( lattice );
				else break;
			}
		}
		
		line = lattices.get( lattices.size()-1 );
		lattice = line.get( line.size()-1 );
		lattice.deleteWallRight();
		
		return new 迷宫地图_标准( width , height , lattices , road );
	}
	
	private static final boolean HasLatticeWithNoHandled( List<List<迷宫格子数据>> lattices )
	{
		for( List<迷宫格子数据> line : lattices )
		{
			for( 迷宫格子数据 lattice : line )
			{
				if( ! lattice.hasHandled() ) return true;
			}
		}
		return false;
	}
}