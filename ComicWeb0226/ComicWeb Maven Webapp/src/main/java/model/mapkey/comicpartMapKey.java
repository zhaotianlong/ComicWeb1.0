package model.mapkey;

import java.io.Serializable;

import model.Comic;

public class comicpartMapKey implements Serializable {
	
	private static final long serialVersionUID = 3176972128965536016L;
	
	private Integer partId;
	private Comic comic;
	
	
	
	public int getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (partId == 0 ? 0 : partId.hashCode());
		result = prime * result + (comic == null ? 0 : comic.hashCode());
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

		comicpartMapKey other = (comicpartMapKey) obj;
		if (partId.equals(other.partId) && comic.equals(other.comic))
			return true;
		return false;
	}

}
