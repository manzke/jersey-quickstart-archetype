#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.filter;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.container.filter.UriConnegFilter;

/**
 * The ContentNegotiationFilter maps calls to a URI like /something.xml to /something and sets
 * the right accept header for the ending xml. Currently this filter is configured to map .json
 * and .xml URI endings.
 */
public class ContentNegotiationFilter extends UriConnegFilter {
	private static final Map<String, MediaType> mappedMediaTypes = new HashMap<String, MediaType>(2);

	static {
		mappedMediaTypes.put("json", MediaType.APPLICATION_JSON_TYPE);
		mappedMediaTypes.put("xml", MediaType.APPLICATION_XML_TYPE);
		mappedMediaTypes.put("txt", MediaType.TEXT_PLAIN_TYPE);
		mappedMediaTypes.put("html", MediaType.TEXT_HTML_TYPE);
	}

	public ContentNegotiationFilter() {
		super(mappedMediaTypes);
	}
}
