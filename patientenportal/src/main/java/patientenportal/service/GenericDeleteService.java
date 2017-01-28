package patientenportal.service;

import java.util.List;

import patientenportal.dao.BaseClassDAOImpl;

public class GenericDeleteService {
	public static void genericDelete(long BaseClassId) {
		BaseClassDAOImpl bci = new BaseClassDAOImpl();
		bci.deleteEntity(BaseClassId);
	}
}
