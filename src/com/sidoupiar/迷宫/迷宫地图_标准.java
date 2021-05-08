package com.sidoupiar.迷宫;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public final class 迷宫地图_标准 implements 迷宫地图接口
{
	private int width;
	private int height;
	private List<List<迷宫格子数据>> lattices;
	private List<迷宫格子数据> road;
	
	public 迷宫地图_标准( int width , int height , List<List<迷宫格子数据>> lattices , List<迷宫格子数据> road )
	{
		this.width = width;
		this.height = height;
		this.lattices = lattices;
		this.road = road;
	}
	
	
	
	/**
	 * size = 格子大小 , 大于 1 , 因为墙宽最小为 1
	 * roadThickness = 无效参数
	 * drawRoad = 是否绘制出路
	 **/
	@Override
	public final BufferedImage toImage( int size , int roadThickness , boolean drawRoad )
	{
		int wallThickness = Math.max( (int)(size/2.5) , 1 );
		BufferedImage im = new BufferedImage( this.width*size+wallThickness , this.height*size+wallThickness , BufferedImage.TYPE_4BYTE_ABGR );
		Graphics g = im.getGraphics();
		g.setColor( Color.BLACK );
		g.fillRect( 0 , 0 , im.getWidth() , im.getHeight() );
		if( drawRoad ) g.setColor( Color.RED );
		else g.setColor( Color.WHITE );
		g.fillRect( im.getWidth()-wallThickness , im.getHeight()-size , wallThickness , size-wallThickness );
		List<迷宫格子数据> line;
		迷宫格子数据 lattice;
		int xs;
		int ys;
		int ws;
		for( int y = 0 ; y < this.height ; y ++ )
		{
			if( y >= this.lattices.size() ) continue;
			line = this.lattices.get( y );
			for( int x = 0 ; x < this.width ; x ++ )
			{
				if( x >= line.size() ) continue;
				lattice = line.get( x );
				if( lattice == null ) continue;
				if( ! lattice.hasHandled() ) continue;
				xs = x * size;
				ys = y * size;
				ws = size - wallThickness;
				g.setColor( Color.WHITE );
				g.fillRect( xs , ys , size , size );
				g.setColor( Color.BLACK );
				g.fillRect( xs , ys , wallThickness , wallThickness );
				if( lattice.hasWallUp() ) g.fillRect( xs , ys , size , wallThickness );
				if( lattice.hasWallLeft() ) g.fillRect( xs , ys , wallThickness , size );
			}
		}
		if( drawRoad )
		{
			g.setColor( Color.RED );
			int x;
			int y;
			int xh;
			int yh;
			for( 迷宫格子数据 roadLattice : this.road )
			{
				x = roadLattice.getX();
				y = roadLattice.getY();
				xs = x * size;
				ys = y * size;
				ws = size - wallThickness;
				xh = xs + wallThickness;
				yh = ys + wallThickness;
				g.fillRect( xh , yh , ws , ws );
				if( ! roadLattice.hasWallUp() ) g.fillRect( xh , ys , ws , wallThickness );
				if( ! roadLattice.hasWallLeft() ) g.fillRect( xs , yh , wallThickness , ws );
			}
		}
		g.dispose();
		im.flush();
		return im;
	}
}