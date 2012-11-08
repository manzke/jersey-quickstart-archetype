#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.ws.rs.GET;
import javax.ws.rs.core.Context;

import com.sun.jersey.spi.resource.Singleton;

import ${package}.features.connection.Connection;

/** The /system sub resource. */
@Singleton
public class SystemResource extends AbstractSubResource {
	@GET
	public String getInformation(@Context Connection connection) {
		return "jersey-system-08.11.2012 - "+connection;
	}
}
