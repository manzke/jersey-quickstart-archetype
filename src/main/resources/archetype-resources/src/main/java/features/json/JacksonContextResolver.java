#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;

@Provider
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mapper;

	/**
	 * Creates an instance of this resolver, registering the provided custom XML Elements and
	 * Properties.
	 * 
	 * @param additionalClasses
	 *            The custom extensions (JAXB classes) to be registered (can be left blank).
	 * @throws JAXBException
	 *             if an error was encountered while creating the JAXBContext, such as (but not
	 *             limited to): No JAXB implementation was discovered, Classes use JAXB annotations
	 *             incorrectly, Classes have colliding annotations (i.e., two classes with the same
	 *             type name), The JAXB implementation was unable to locate provider-specific
	 *             out-of-band information (such as additional files generated at the development
	 *             time.)
	 */
	public JacksonContextResolver() throws JAXBException {
		mapper = new ObjectMapper();

		AnnotationIntrospector introspector = new JacksonAnnotationIntrospector();
		mapper.getDeserializationConfig().withAnnotationIntrospector(introspector);
		mapper.getSerializationConfig().withAnnotationIntrospector(introspector);
	}

	/**
	 * @return A single, shared context for both, WebDAV XML Elements and Properties and custom
	 *         extensions.
	 */
	@Override
	public final ObjectMapper getContext(final Class<?> cls) {
		return cls.getPackage().getName()
				.startsWith(JacksonContextResolver.class.getPackage().getName()) ? this.mapper
				: null;
	}
}
