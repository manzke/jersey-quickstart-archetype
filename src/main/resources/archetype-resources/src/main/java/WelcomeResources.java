#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.core.ResourceContext;

/** The main entry point for this REST interface. */
@Path("/")
public class WelcomeResources extends AbstractResource {

	@Context
	ResourceContext rc;

	/**
	 * Shows a welcome message as String with all possible endpoints.
	 *
	 * @return a welcome message as String with all possible endpoints.
	 */
	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String welcome() {
		return "Welcome to SDB REST services. Please use one of the endpoints: spaces, folders, "
				+ "documents, users, accounts, localization, system.";
	}

	@Path("/system")
	public SystemResource getSystem() {
		return rc.getResource(SystemResource.class);
	}
}
