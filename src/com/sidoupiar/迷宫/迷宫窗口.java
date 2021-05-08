package com.sidoupiar.迷宫;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public final class 迷宫窗口 extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 程序入口
	 * 可执行程序从这里进入
	 **/
	public static final void main( String[] args )
	{
		// 基本参数
		int mazeWidth = 100;
		int mazeHeight = 100;
		long seed = 1L;
		int width = 700;
		int height = 700;
		
		// 创建窗口
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new 迷宫窗口( "迷宫" , mazeWidth , mazeHeight , seed );
		frame.setBounds( (int)((d.getWidth()-width)/2) , (int)((d.getHeight()-height)/2) , width , height );
		frame.setVisible( true );
	}
	
	
	
	private BufferedImage maze;
	private BufferedImage mazeRoad;
	
	public 迷宫窗口( String title , int width , int height , long seed )
	{
		super( title );
		long now = System.currentTimeMillis();
		this.setUndecorated( true );
		迷宫地图接口 map = 迷宫生成器.CreateDeepthMaze( width , height , seed );
		this.maze = map.toImage( 1 , 1 , false );
		this.mazeRoad = map.toImage( 1 , 1 , true );
		try
		{
			// 把生成的迷宫图片保存进文件夹
			String desktop = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
			File file1 = new File( desktop+File.separator+"迷宫" );
			File file2 = new File( desktop+File.separator+"迷宫答案" );
			if( ! file1.exists() ) file1.mkdirs();
			if( ! file2.exists() ) file2.mkdirs();
			ImageIO.write( this.maze , "png" , new File( file1 , "迷宫-"+width+"x"+height+"-"+seed+".png" ) );
			ImageIO.write( this.mazeRoad , "png" , new File( file2 , "迷宫答案-"+width+"x"+height+"-"+seed+".png" ) );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		System.out.println( "总共耗时 : "+(double)(System.currentTimeMillis()-now)/1000D );
	}
	
	@Override
	public void paint( Graphics g )
	{
		BufferedImage im = new BufferedImage( this.getWidth() , this.getHeight() , BufferedImage.TYPE_4BYTE_ABGR );
		Graphics img = im.getGraphics();
		super.paint( img );
		if( maze != null ) img.drawImage( this.maze , 10 , 10 , this.getWidth()-20 , this.getHeight()-20 , null );
		img.dispose();
		im.flush();
		
		g.drawImage( im , 0 , 0 , null );
	}
}