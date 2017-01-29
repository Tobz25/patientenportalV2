package patientenportal.helper;
/*
 * Maps a thrown DataNotFoundException to a proper Response, which is then returned to the client
 * @author Tobi
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import patientenportal.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://wiseb.wiwi.tu-dresden.de/wiki");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
