package patientenportal.helper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import patientenportal.model.ErrorMessage;

	
@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException>{

	@Override
	public Response toResponse(UnauthorizedException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.UNAUTHORIZED)
				.entity(errorMessage)
				.build();
	}
}
