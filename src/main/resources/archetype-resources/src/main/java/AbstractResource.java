#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


/** The abstract base resource Jax-Rs class combines methods to be used by all resource classes. */
public class AbstractResource {

	/** The UriInfo object of the underlying request. Is injected by the Jersey framework. */
	@Context
	protected UriInfo uriInfo;

	/** The HttpServletRequest object of the underlying request. Is injected by the Jersey
	 * framework. */
	@Context
	protected HttpServletRequest request;

}
