package model.vo;

import java.util.Date;

public class LoveComicModel {
	private String comicId;
	private String comicName;
	private String partId;
	private int partNum;
	private Date partUpdate;
	private String src;
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getComicId() {
		return comicId;
	}
	public void setComicId(String comicId) {
		this.comicId = comicId;
	}
	public String getComicName() {
		return comicName;
	}
	public void setComicName(String comicName) {
		this.comicName = comicName;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public int getPartNum() {
		return partNum;
	}
	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}
	public Date getPartUpdate() {
		return partUpdate;
	}
	public void setPartUpdate(Date partUpdate) {
		this.partUpdate = partUpdate;
	}
	
	
}
