#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.connection;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.spi.CloseableService;

import ${package}.Constants;
import ${package}.features.provider.AbstractInjectableProvider;

/**
 * The CredentialProvider builds a credential object from the HttpContext. Currently the HTTP
 * 'Authorizaion'-header is inspected.
 */
@Provider
public class ConnectionProvider extends AbstractInjectableProvider<Connection> {

	public static final String AUTH_HEADER = "Authorization";

	public ConnectionProvider() {
		super(Connection.class);
	}

	@Context
	CloseableService closeableService;

	/**
	 * Creates a Credentials object by the given HttpContext. Throws a WebApplicationException
	 * if the
	 * 'Authorization'-header is missing.
	 */
	@Override
	public Connection getValue(HttpContext c) {
		//get Connection by Credentials
		Connection connection =
				new Connection(Long.toString(System.currentTimeMillis()));

		// add connection to CloseableServiceFactory -> Jersey will close connection when request
		// is finished
		closeableService.add(connection);

		c.getProperties().put(Constants.CONTEXT_PARAM_CONNECTION, connection);

		if (c.isTracingEnabled()) {
			c.trace("Registered SPI Connection in request context.");
		}

		return connection;
	}
}
