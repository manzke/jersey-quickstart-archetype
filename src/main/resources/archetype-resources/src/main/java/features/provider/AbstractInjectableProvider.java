#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.features.provider;

import java.lang.reflect.Type;

import javax.ws.rs.core.Context;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

/**
 * This is the abstract base class for typed injectable provider classes.
 *
 * @param <E> The type of a concrete injectableProvider.
 *
 * @see InjectableProvider
 */
public abstract class AbstractInjectableProvider<E> extends AbstractHttpContextInjectable<E>
		implements InjectableProvider<Context, Type> {

	private final Type injectableProviderType;

	public AbstractInjectableProvider(Type injectableProviderType) {
		this.injectableProviderType = injectableProviderType;
	}

	@Override
	public Injectable<E> getInjectable(ComponentContext ic, Context context, Type type) {
		if (type.equals(injectableProviderType)) {
			return getInjectable(ic, context);
		}

		return null;
	}

	/**
	 * Returns the injectable of this InjectableProvider.
	 *
	 * @param componentContext the injectable component context.
	 * @param context  the injectable context.
	 *
	 * @return the injectable.
	 */
	public Injectable<E> getInjectable(ComponentContext componentContext, Context context) {
		return this;
	}

	@Override
	public ComponentScope getScope() {
		return ComponentScope.PerRequest;
	}
}
