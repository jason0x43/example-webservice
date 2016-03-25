package edu.wright.cs;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.googlejavaformat.java.Formatter;

// The @Path annotation defines what path under the main application path this resource will be
// available on. Individual handler functions may also have @Path annotations, which are appended to
// the main class @Path.
@Path("format")
public class FormatterResource {
	// This handler is for GET requests (when the browser requests data)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
    public String getIt() throws Exception {
		return "Got it!";
    }

	// This handler is for POST requests (when the browser sends data)
    @POST
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
    public String formatCode(CodeRequest code) throws Exception {
		return new Formatter().formatSource(code.code);

		// Alternatively, you could use ProcessBuilder here to execute an external application,
		// passing it the data in `code` and reading its output. When the process finishes, you
		// would just return its output.
    }
}
