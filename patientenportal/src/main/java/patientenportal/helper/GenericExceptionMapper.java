package patientenportal.helper;
/*
 * Allgemeine Exception, die mit einer spezifischen Fehlermeldung aufgrufen werden kann
 * 
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import patientenportal.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<GenericException>{
	@Override
	public Response toResponse(GenericException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
