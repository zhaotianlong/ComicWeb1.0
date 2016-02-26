package model.mapkey;

import java.io.Serializable;

import model.Author;
import model.User;

public class auMapKey implements Serializable {

	private static final long serialVersionUID = 3176972128965536016L;

	private User userId;
	private Author authorId;
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Author getFriendId() {
		return authorId;
	}
	public void setFriendId(Author authorId) {
		this.authorId = authorId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (userId == null ? 0 : userId.hashCode());
		result = prime * result + (authorId == null ? 0 : authorId.hashCode());
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

		auMapKey other = (auMapKey) obj;
		if (userId.equals(other.userId) && authorId.equals(other.authorId))
			return true;
		return false;
	}

}
