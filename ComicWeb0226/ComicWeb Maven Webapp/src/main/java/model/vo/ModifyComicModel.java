package model.vo;

public class ModifyComicModel {

	private String comicId;
	private String comicName;
	private String description;
	private int charge;
	private int comicStatus;
	private String initial;
	private String src;
	
	
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getComicStatus() {
		return comicStatus;
	}
	public void setComicStatus(int comicStatus) {
		this.comicStatus = comicStatus;
	}
	
	

}
