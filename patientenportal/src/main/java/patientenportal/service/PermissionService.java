package patientenportal.service;

import javax.persistence.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.model.User;

public class PermissionService {
	
	public Response setPermission(Entity entity, long userId, String permission){
		return Response.status(Status.OK).build();
	}
	
	public String getPermission(Entity entity, long userId){
		return "permission";
	}

}
