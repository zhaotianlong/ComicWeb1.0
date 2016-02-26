package dao.impl;

import org.springframework.stereotype.Repository;

import dao.IFileDao;
import dao.common.AbstractFileDao;
import dao.common.IFileOperation;
@Repository("fileDao")
public class FileDao extends AbstractFileDao implements IFileDao {

	public FileDao() {
		super();
		// TODO Auto-generated constructor stub
	}

}
