package dao.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.dom4j.io.OutputFormat;

public interface IFileOperation {
	public void  ResizeImage(InputStream is,OutputStream file,String format,int w,int h)throws IOException;
	public void  CutoutImg(InputStream is,File file,String format,int w,int h, int x,int y)throws IOException;
}
