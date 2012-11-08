#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64InputStream;

import ${package}.features.customs.CustomMediaType;

@Provider
@Produces(CustomMediaType.APPLICATION_BASE64)
public class Base64BodyWriter implements MessageBodyWriter<InputStream> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return CustomMediaType.APPLICATION_BASE64_TYPE.equals(mediaType)
				&& InputStream.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(InputStream t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(InputStream in, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		// encode stream
		BufferedInputStream bis = new BufferedInputStream(
				new Base64InputStream(in, true));
		BufferedOutputStream bos = new BufferedOutputStream(entityStream);

		try {
			byte[] buffer = new byte[8192];
			for (;;) {
				int len = bis.read(buffer);
				if (len < 0) {
					break;
				}
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} finally {
			try {
				bis.close();
			} catch (Exception e) {
				// ignore
			}
			try {
				bos.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
}
