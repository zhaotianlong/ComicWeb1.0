//package model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
////@Entity(name="userspitslot")
//public class UserSpitSlot {
//	@Id
//	@Column(name="id",nullable=false)
//	private int id;
//	
//	@ManyToOne
//	@JoinColumn(name="userid",referencedColumnName="accountid")
//	private User userId;
//	
//	@ManyToOne
//	@JoinColumn(name="spitslotid",referencedColumnName="spitslotid")
//	private SpitSlot spitSlotId;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public User getUserId() {
//		return userId;
//	}
//	public void setUserId(User userId) {
//		this.userId = userId;
//	}
//	public SpitSlot getSpitSlotId() {
//		return spitSlotId;
//	}
//	public void setSpitSlotId(SpitSlot spitSlotId) {
//		this.spitSlotId = spitSlotId;
//	}
//	
//}
