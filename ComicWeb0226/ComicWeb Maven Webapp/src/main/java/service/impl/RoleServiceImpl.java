package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import dao.IRoleDao;
import dao.common.IOperation;
import model.Role;
import service.IRoleService;
import service.common.AbstractService;

@Service("roleService")
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {
	@Resource(name="roleDao")
	private IRoleDao roleDao; 
	@Override
	protected IOperation<Role> getDao() {
		// TODO Auto-generated method stub
		return this.roleDao;
	}
}
