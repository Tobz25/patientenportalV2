package patientenportal.helper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import patientenportal.model.ErrorMessage;

@Provider
public class ForbiddenRequestExceptionMapper implements ExceptionMapper<ForbiddenRequestException>{
	
	@Override
	public Response toResponse(ForbiddenRequestException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 403, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.FORBIDDEN)
				.entity(errorMessage)
				.build();
	}

}
