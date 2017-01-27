package patientenportal.helper;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


import patientenportal.model.Role;
import patientenportal.model.User;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);
        User user = (User) requestContext.getSecurityContext().getUserPrincipal();


            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                if(checkPermissions(classRoles, user)== true)
                {
                	return;
	            }
	            else
	            {
	            	throw new ForbiddenRequestException("User does not have the permission to perform the request");
	            }
            } 
            else 
            {
                if (checkPermissions(methodRoles, user)== true)
                {
                	return;
                }
                else
                {
                		throw new ForbiddenRequestException("User does not have the permission to perform the request");
                }
            }
    }

    // Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private boolean checkPermissions(List<Role> allowedRoles, User user){
    	Role userRole = user.getActiveUserRole().getRole();
    	
    	if(allowedRoles.size()==0){
    		return true;
    	}
    	else if(allowedRoles.contains(userRole))
    		return true;
    	else return false;
        // Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method
    }
}