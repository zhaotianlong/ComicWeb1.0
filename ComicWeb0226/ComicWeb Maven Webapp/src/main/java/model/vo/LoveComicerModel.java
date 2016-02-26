package model.vo;

import java.util.Date;

public class LoveComicerModel {
	private String authorId;
	private String athuorName;
	private int cmicSum;
	private int fansSum;
	private Date loveDate;
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAthuorName() {
		return athuorName;
	}
	public void setAthuorName(String athuorName) {
		this.athuorName = athuorName;
	}
	public int getCmicSum() {
		return cmicSum;
	}
	public void setCmicSum(int cmicSum) {
		this.cmicSum = cmicSum;
	}
	public int getFansSum() {
		return fansSum;
	}
	public void setFansSum(int fansSum) {
		this.fansSum = fansSum;
	}
	public Date getLoveDate() {
		return loveDate;
	}
	public void setLoveDate(Date loveDate) {
		this.loveDate = loveDate;
	}
	
	
}
