#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class for all sub resource classes.
 */
public class AbstractSubResource {

	@Context
	protected UriInfo uriInfo;

	@Context
	protected HttpServletRequest request;

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractSubResource.class);

	/**
	 * Returns the locale of the request. If no 'Accept-Language' header is set, 
	 * the default configured localization locale is returned. If the header is present, 
	 * only the 'language-tag'-part of it will be taken to build a Locale. 
	 * @return the locale of the request. or the default configured localization locale.
	 */
	protected Locale getLocale() {
		String header = request.getHeader("Accept-Language");
		if (header != null && !header.isEmpty()) {
			String language = request.getLocale().getLanguage();
			if (language.length() == 5)
				language = language.substring(0, 2);
			return Locale.forLanguageTag(language);
		}
		
		return Locale.ENGLISH;
	}
}
