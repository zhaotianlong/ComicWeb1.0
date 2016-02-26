package model.vo;

import org.springframework.web.multipart.MultipartFile;

public class AuthorCreateComicModel {

	private String comicName;
	private String description;
	private int comicStatus;
	private int charge;
	private String initial;
	private int[] tab;
	private MultipartFile imgUpLoad;
	private String authorId;
	
	
	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public MultipartFile getImgUpLoad() {
		return imgUpLoad;
	}
	public void setImgUpLoad(MultipartFile imgUpLoad) {
		this.imgUpLoad = imgUpLoad;
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
	public int getComicStatus() {
		return comicStatus;
	}
	public void setComicStatus(int comicStatus) {
		this.comicStatus = comicStatus;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public int[] getTab() {
		return tab;
	}
	public void setTab(int[] tab) {
		this.tab = tab;
	}
	
	
}
