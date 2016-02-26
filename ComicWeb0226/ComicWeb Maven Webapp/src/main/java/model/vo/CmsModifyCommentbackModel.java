package model.vo;

/***
 * cms更改回复的实体
 * @author LOLO
 *
 */
public class CmsModifyCommentbackModel {
	private Integer subId;
	private String commentDes;
	private String fromId;
	private String toId;
	private Integer commentId;

	public String getCommentDes() {
		return commentDes;
	}
	public void setCommentDes(String commentDes) {
		this.commentDes = commentDes;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	
	
	
}
