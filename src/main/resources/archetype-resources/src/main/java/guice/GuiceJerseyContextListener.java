#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.guice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import ${package}.Constants;
import ${package}.features.json.JacksonContextResolver;
import ${package}.filter.ContentNegotiationFilter;
import ${package}.filter.CrossDomainHeaderFilter;

/**
 * This context listener wires the Google Guice and Jersey frameworks together. As a result, both
 * injection methods can be used.
 */
public class GuiceJerseyContextListener extends GuiceServletContextListener {

	private boolean useLoggingFilter;
	private boolean useEntityLogging;

	@Override
	protected Injector getInjector() {

		return Guice.createInjector(new JerseyServletModule() {

			@Override
			protected void configureServlets() {
				// Add classes to be injected by guice
				bind(JacksonContextResolver.class).asEagerSingleton();

				// Use jersey class path scanning
				Map<String, String> params = new HashMap<String, String>();
				params.put(PackagesResourceConfig.PROPERTY_PACKAGES, Constants.REST_PACKAGE);

				// jersey request filters
				StringBuilder requestFilters = new StringBuilder();
				// content negotiation to map .json to JSON and .xml to XML responses.
				requestFilters.append(ContentNegotiationFilter.class.getName());
				if (useLoggingFilter) {
					requestFilters.append(";").append(LoggingFilter.class.getName());
				}
				params.put("com.sun.jersey.spi.container.ContainerRequestFilters",
						requestFilters.toString());

				StringBuilder responseFilters = new StringBuilder();
				// jersey response filters
				responseFilters.append(CrossDomainHeaderFilter.class.getName());
				if (useLoggingFilter) {
					responseFilters.append(";").append(LoggingFilter.class.getName());
				}
				params.put("com.sun.jersey.spi.container.ContainerResponseFilters",
						responseFilters.toString());

				// log request/response entities?
				params.put("com.sun.jersey.config.feature.logging.DisableEntitylogging",
						String.valueOf(!useEntityLogging));

				// json mapping via jackson
				params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");

				serve("/*").with(GuiceContainer.class, params);
			}
		});
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		this.useLoggingFilter = Boolean.parseBoolean(context.getInitParameter("RequestLogging"));
		this.useEntityLogging = Boolean.parseBoolean(context.getInitParameter("EntityLogging"));
		super.contextInitialized(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
	}
}
