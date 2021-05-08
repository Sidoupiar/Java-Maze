package com.sidoupiar.迷宫;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class 迷宫地图_方块 implements 迷宫地图接口
{
	private int width;
	private int height;
	private List<List<迷宫地图_方块_格子数据>> blocks;
	
	public 迷宫地图_方块( int width , int height , List<List<迷宫格子数据>> lattices , List<迷宫格子数据> road )
	{
		this.width = width;
		this.height = height;
		this.blocks = new ArrayList<List<迷宫地图_方块_格子数据>>();
		
		List<迷宫格子数据> line;
		迷宫格子数据 lattice;
		List<迷宫地图_方块_格子数据> wallLine;
		List<迷宫地图_方块_格子数据> roadLine;
		List<迷宫地图_方块_格子数据> endLine = new ArrayList<迷宫地图_方块_格子数据>();
		int dx;
		int dy;
		for( int y = 0  ; y < this.height ; y ++ )
		{
			if( y >= lattices.size() ) line = new ArrayList<迷宫格子数据>();
			else line = lattices.get( y );
			wallLine = new ArrayList<迷宫地图_方块_格子数据>();
			roadLine = new ArrayList<迷宫地图_方块_格子数据>();
			for( int x = 0  ; x < this.width ; x ++ )
			{
				dx = x * 2;
				dy = y * 2;
				if( x >= line.size() )
				{
					wallLine.add( new 迷宫地图_方块_格子数据( dx , dy , true ) );
					wallLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy , true ) );
					roadLine.add( new 迷宫地图_方块_格子数据( dx , dy+1 , true ) );
					roadLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy+1 , true ) );
					if( x == width-1 )
					{
						wallLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy , true ) );
						roadLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy+1 , true ) );
					}
					if( y == height-1 )
					{
						endLine.add( new 迷宫地图_方块_格子数据( dx , dy+2 , true ) );
						endLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy+2 , true ) );
						if( x == width-1 ) endLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy+2 , true ) );
					}
					continue;
				}
				lattice = line.get( x );
				wallLine.add( new 迷宫地图_方块_格子数据( dx , dy , true ) );
				wallLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy , lattice.hasWallUp() ) );
				roadLine.add( new 迷宫地图_方块_格子数据( dx , dy+1 , lattice.hasWallLeft() ) );
				roadLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy+1 , false ) );
				if( x == width-1 )
				{
					wallLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy , true ) );
					roadLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy+1 , lattice.hasWallRight() ) );
				}
				if( y == height-1 )
				{
					endLine.add( new 迷宫地图_方块_格子数据( dx , dy+2 , true ) );
					endLine.add( new 迷宫地图_方块_格子数据( dx+1 , dy+2 , lattice.hasWallDown() ) );
					if( x == width-1 ) endLine.add( new 迷宫地图_方块_格子数据( dx+2 , dy+2 , true ) );
				}
			}
			this.blocks.add( wallLine );
			this.blocks.add( roadLine );
		}
		this.blocks.add( endLine );
		List<迷宫地图_方块_格子数据> up = new ArrayList<迷宫地图_方块_格子数据>();
		List<迷宫地图_方块_格子数据> left = new ArrayList<迷宫地图_方块_格子数据>();
		int x;
		int y;
		迷宫地图_方块_格子数据 block;
		for( 迷宫格子数据 r : road )
		{
			if( r == null ) continue;
			x = r.getX();
			y = r.getY();
			dx = x * 2;
			dy = y * 2;
			if( ! r.hasWallUp() )
			{
				block = this.blocks.get( dy ).get( dx+1 );
				block.setRoad( true );
				up.add( block );
			}
			if( ! r.hasWallLeft() )
			{
				block = this.blocks.get( dy+1 ).get( dx );
				block.setRoad( true );
				left.add( block );
			}
			this.blocks.get( dy+1 ).get( dx+1 ).setRoad( true );
			if( x == width-1 && ! r.hasWallRight() ) this.blocks.get( dy+1 ).get( dx+2 ).setRoad( true );
			if( y == height-1 && ! r.hasWallDown() ) this.blocks.get( dy+2 ).get( dx+1 ).setRoad( true );
		}
		for( 迷宫地图_方块_格子数据 b : up )
		{
			x = b.getX();
			y = b.getY() - 1;
			if( y < 0 ) continue;
			block = this.blocks.get( y ).get( x );
			if( ! block.isRoad() ) b.setRoad( false );
		}
		for( 迷宫地图_方块_格子数据 b : left )
		{
			x = b.getX() - 1;
			y = b.getY();
			if( x < 0 ) continue;
			block = this.blocks.get( y ).get( x );
			if( ! block.isRoad() ) b.setRoad( false );
		}
	}
	
	
	
	/**
	 * wallThickness = 墙宽
	 * roadThickness = 路宽
	 * drawRoad = 是否绘制出路
	 **/
	@Override
	public final BufferedImage toImage( int wallThickness , int roadThickness , boolean drawRoad )
	{
		int size = wallThickness + roadThickness;
		BufferedImage im = new BufferedImage( this.width*size+wallThickness , this.height*size+wallThickness , BufferedImage.TYPE_4BYTE_ABGR );
		Graphics g = im.getGraphics();
		List<迷宫地图_方块_格子数据> line;
		迷宫地图_方块_格子数据 block;
		int xs;
		int ys;
		int w;
		int h;
		for( int y = 0 , c = this.height*2+1 ; y < c ; y ++ )
		{
			line = this.blocks.get( y );
			for( int x = 0 , d = this.width*2+1 ; x < d ; x ++ )
			{
				block = line.get( x );
				if( x%2 == 0 )
				{
					xs = x / 2 * size;
					w = wallThickness;
				}
				else
				{
					xs = x / 2 * size + wallThickness;
					w = roadThickness;
				}
				if( y%2 == 0 )
				{
					ys = y / 2 * size;
					h = wallThickness;
				}
				else
				{
					ys = y / 2 * size + wallThickness;
					h = roadThickness;
				}
				if( block.isWall() ) g.setColor( Color.BLACK );
				else if( drawRoad && block.isRoad() ) g.setColor( Color.RED );
				else g.setColor( Color.WHITE );
				g.fillRect( xs , ys , w , h );
			}
		}
		g.dispose();
		im.flush();
		return im;
	}
}