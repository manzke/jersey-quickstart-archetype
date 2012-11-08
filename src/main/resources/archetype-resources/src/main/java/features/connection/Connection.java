#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.connection;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Connection implements Closeable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Connection.class);
	private String message;

	public Connection(String message) {
		this.message = message;
	}

	@Override
	public void close() throws IOException {
		try {
			LOGGER.debug("Closed SPI Connection.");
		} catch (Exception e) {
			throw new IOException(e.getMessage(), e);
		}
	}

	@Override
	public String toString() {
		return "Connection [" + (message != null ? "message=" + message : "")
				+ "]";
	}	
}
