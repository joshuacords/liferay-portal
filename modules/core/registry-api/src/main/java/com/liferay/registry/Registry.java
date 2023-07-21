/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

import com.liferay.registry.dependency.ServiceDependencyManager;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Raymond Augé
 */
@ProviderType
public interface Registry {

	public <S, R> R callService(Class<S> serviceClass, Function<S, R> function);

	public <S, R> R callService(String className, Function<S, R> function);

	public <T> ServiceReference<T>[] getAllServiceReferences(
			String className, String filterString)
		throws Exception;

	public Filter getFilter(String filterString) throws RuntimeException;

	public Registry getRegistry() throws SecurityException;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public <T> T getService(Class<T> clazz);

	public <T> T getService(ServiceReference<T> serviceReference);

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public <T> T getService(String className);

	public Collection<ServiceDependencyManager> getServiceDependencyManagers();

	public <T> ServiceReference<T> getServiceReference(Class<T> clazz);

	public <T> ServiceReference<T> getServiceReference(String className);

	public <T> Collection<ServiceReference<T>> getServiceReferences(
			Class<T> clazz, String filterString)
		throws Exception;

	public <T> ServiceReference<T>[] getServiceReferences(
			String className, String filterString)
		throws Exception;

	public <T> ServiceRegistrar<T> getServiceRegistrar(Class<T> clazz);

	public <T> Collection<T> getServices(Class<T> clazz, String filterString)
		throws Exception;

	public <T> T[] getServices(String className, String filterString)
		throws Exception;

	public String getSymbolicName(ClassLoader classLoader);

	public <T> ServiceRegistration<T> registerService(
		Class<T> clazz, T service);

	public <T> ServiceRegistration<T> registerService(
		Class<T> clazz, T service, Map<String, Object> properties);

	public <T> ServiceRegistration<T> registerService(
		String className, T service);

	public <T> ServiceRegistration<T> registerService(
		String className, T service, Map<String, Object> properties);

	public <T> ServiceRegistration<T> registerService(
		String[] classNames, T service);

	public <T> ServiceRegistration<T> registerService(
		String[] classNames, T service, Map<String, Object> properties);

	public void registerServiceDependencyManager(
		ServiceDependencyManager serviceDependencyManager);

	public Registry setRegistry(Registry registry) throws SecurityException;

	public <S, T> ServiceTracker<S, T> trackServices(Class<S> clazz);

	public <S, T> ServiceTracker<S, T> trackServices(
		Class<S> clazz,
		ServiceTrackerCustomizer<S, T> serviceTrackerCustomizer);

	public <S, T> ServiceTracker<S, T> trackServices(Filter filter);

	public <S, T> ServiceTracker<S, T> trackServices(
		Filter filter, ServiceTrackerCustomizer<S, T> serviceTrackerCustomizer);

	public <S, T> ServiceTracker<S, T> trackServices(String className);

	public <S, T> ServiceTracker<S, T> trackServices(
		String className,
		ServiceTrackerCustomizer<S, T> serviceTrackerCustomizer);

	public <T> boolean ungetService(ServiceReference<T> serviceReference);

	public void unregisterServiceDependencyManager(
		ServiceDependencyManager serviceDependencyManager);

}