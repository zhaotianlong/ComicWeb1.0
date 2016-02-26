package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity(name="spitslot")
public class SpitSlot implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="spitslotid",nullable=false)
	private int spitslotId;
	
	@Column(name="spitslotdes")
	private String spitSlotDes; 
	
	@ManyToOne
	@JoinColumn(name="partid",referencedColumnName="partid")
	private ComicPart partId;
	
	public ComicPart getPartId() {
		return partId;
	}
	public void setPartId(ComicPart partId) {
		this.partId = partId;
	}
	public int getSpitslotId() {
		return spitslotId;
	}
	public void setSpitslotId(int spitslotId) {
		this.spitslotId = spitslotId;
	}
	public String getSpitSlotDes() {
		return spitSlotDes;
	}
	public void setSpitSlotDes(String spitSlotDes) {
		this.spitSlotDes = spitSlotDes;
	}

	
}
