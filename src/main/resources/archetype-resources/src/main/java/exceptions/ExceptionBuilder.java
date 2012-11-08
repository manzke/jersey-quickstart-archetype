#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import ${package}.features.customs.CustomStatus;

/**
 * The class ExceptionBuilder can be used to throw different kinds of exceptions.
 */
public class ExceptionBuilder {

	/**
	 * Builds an internal server error (status code 500) exception with the given message as the
	 * response body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildInternalServerError(String message) {
		return buildWebAppException(message, Status.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Builds an internal server error (status code 500) exception with the given message as the
	 * response body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildBadRequestException(String message) {
		return buildWebAppException(message, Status.BAD_REQUEST);
	}

	/**
	 * Builds an internal server error (status code 500) exception with the given message as the
	 * response body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildLengthRequiredException(String message) {
		return buildWebAppException(message, CustomStatus.LENGTH_REQUIRED);
	}

	/**
	 * Builds a not found error (status code 404) exception with the given message as the response
	 * body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildNotFoundException(String message) {
		return buildWebAppException(message, Status.NOT_FOUND);
	}

	/**
	 * Builds a authorization error (status code 401) exception with the given message as the
	 * response
	 * body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildAuthorizationException(String message) {
		return buildWebAppException(message, Status.UNAUTHORIZED);
	}

	/**
	 * Builds a Service unavailable error (status code 503) exception with the given message as the
	 * response body.
	 *
	 * @param message The message to use as the response body.
	 */
	public static WebApplicationException buildUnavailableException(String message) {
		return buildWebAppException(message, Status.SERVICE_UNAVAILABLE);
	}

	/**
	 * Builds a {@see WebApplicationException} with the given status and message.
	 *
	 * @param message The message to use as the response body.
	 * @param status The status to use for the HTTP response.
	 */
	public static WebApplicationException buildWebAppException(String message, StatusType status) {
		Response response =
				Response.status(status).entity(message).type(MediaType.TEXT_PLAIN_TYPE).build();
		return new WebApplicationException(response);
	}
}
