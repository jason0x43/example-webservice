package edu.wright.cs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
public class ErrorHandler implements ExceptionMapper<Throwable> {
	public Response toResponse(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return Response.status(500)
			.entity(sw.toString())
			.type(MediaType.TEXT_PLAIN)
			.build();
	}
}
