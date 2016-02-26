package model.vo;

public class CmsModifyCommentModel {
	private Integer id;
	private String commentDes;
	private String accountId;
	private String comicId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommentDes() {
		return commentDes;
	}
	public void setCommentDes(String commentDes) {
		this.commentDes = commentDes;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getComicId() {
		return comicId;
	}
	public void setComicId(String comicId) {
		this.comicId = comicId;
	}
	
	
}
