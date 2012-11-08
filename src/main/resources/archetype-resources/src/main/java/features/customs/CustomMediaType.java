#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.customs;

import javax.ws.rs.core.MediaType;


/**
 * The class CustomMediaType.
 */
public class CustomMediaType {
	public static final MediaType APPLICATION_BASE64_TYPE = new MediaType("application", "base64");
	public static final String APPLICATION_BASE64 = "application/base64";

}
