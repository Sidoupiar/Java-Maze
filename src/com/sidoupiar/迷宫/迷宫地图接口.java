package com.sidoupiar.迷宫;

import java.awt.image.BufferedImage;

public interface 迷宫地图接口
{
	public BufferedImage toImage( int wallThickness , int roadThickness , boolean drawRoad );
}