package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import model.SpitSlot;
import model.vo.CommentModel;
import dao.ISpitSlotDao;
import dao.common.AbstractHibernateDao;

@Repository(value="spitslotDao")
public class SpitSlotDao extends AbstractHibernateDao<SpitSlot> implements
		ISpitSlotDao {

	public SpitSlotDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(SpitSlot.class);
	}
	
	
}
