package model.mapkey;

import java.io.Serializable;

import javax.persistence.Embeddable;

import model.User;
import javassist.SerialVersionUID;

/**
 * 好友列表的实体
 * 
 * @param userid
 *            好友1id
 * @param friendid
 *            好友2id
 * @author lolo
 * */
public class gfMapKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3176972128965536016L;


	private User userId;
	private User friendId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public gfMapKey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (userId == null ? 0 : userId.hashCode());
		result = prime * result + (friendId == null ? 0 : friendId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		gfMapKey other = (gfMapKey) obj;
		if (userId.equals(other.userId) && friendId.equals(other.friendId))
			return true;
		return false;
	}
}
