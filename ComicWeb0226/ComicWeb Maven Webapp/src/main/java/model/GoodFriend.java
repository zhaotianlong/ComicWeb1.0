package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.mapkey.gfMapKey;

@Entity(name="goodfriend")
@IdClass(gfMapKey.class)

/*
 * 好友列表实体
 * @param user1Id
 * @param user2Id
 * */
public class GoodFriend {
	
	@Id
	@JoinColumn(name="userid",referencedColumnName="accountid")
	private User userId;
	@Id
	@JoinColumn(name="friendid",referencedColumnName="accountid")
	private User friendId;

	
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public User getFriendId() {
		return friendId;
	}
	public void setFriendId(User friendId) {
		this.friendId = friendId;
	}
	
}
