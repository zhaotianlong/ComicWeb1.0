package service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import service.IFileUpLoad;

public class FileUpLoadService implements IFileUpLoad {
	
	private final String strPic="D:\\tomcat 8.0\\webapps\\ComicWeb\\WebResources\\img\\userIcon";
	public String fileUpLoad(MultipartFile file) throws IOException {
		String path=null;
		if (!file.isEmpty()) {
			File f=new File(strPic + file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(),f);
			path=f.getAbsolutePath();
		}
		return path;
	}

	public void CutoutIcon(int x, int y, int w, int h) {
		// TODO Auto-generated method stub

	}

}
