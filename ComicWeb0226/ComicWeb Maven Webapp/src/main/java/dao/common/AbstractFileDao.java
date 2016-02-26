package dao.common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public abstract class AbstractFileDao implements IFileOperation {

	public void ResizeImage(InputStream is,OutputStream file,String format, int w, int h) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage prevImage = ImageIO.read(is); 
		double width = prevImage.getWidth(); 
		double height = prevImage.getHeight(); 
		double percentW = w/width; 
		double percentH = h/height;
		int newWidth = (int)(width * percentW); 
		int newHeight = (int)(height * percentH); 
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR); 
		Graphics graphics = image.createGraphics();  
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);  
		ImageIO.write(image,format,file);
		file.flush();
		is.close(); 
		file.close();
	}

	public void CutoutImg(InputStream is,File file,String format ,int w, int h, int x, int y) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage image = ImageIO.read(is); 
		BufferedImage icon=image.getSubimage(x, y, w, h);
		ImageIO.write(icon,format,file);
		is.close();
	}
}
