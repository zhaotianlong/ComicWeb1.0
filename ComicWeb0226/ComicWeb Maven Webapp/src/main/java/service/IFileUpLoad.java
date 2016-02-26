package service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUpLoad {

	public String fileUpLoad(MultipartFile file)throws IOException ;
	public void CutoutIcon(int x,int y,int w,int h);
}
