/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.map.taglib.internal.servlet;

import com.liferay.map.MapProvider;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.service.GroupLocalService;

import java.util.Collection;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = {})
public class ServletContextUtil {

	public static GroupLocalService getGroupLocalService() {
		return _groupLocalService;
	}

	public static MapProvider getMapProvider(String mapProviderKey) {
		return _mapProviders.getService(mapProviderKey);
	}

	public static Collection<MapProvider> getMapProviders() {
		return _mapProviders.values();
	}

	public static final ServletContext getServletContext() {
		return _servletContext;
	}

	@Activate
	protected void activate(final BundleContext bundleContext) {
		_mapProviders = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, MapProvider.class, null,
			new ServiceReferenceMapper<String, MapProvider>() {

				@Override
				public void map(
					ServiceReference<MapProvider> serviceReference,
					ServiceReferenceMapper.Emitter<String> emitter) {

					MapProvider mapProvider = bundleContext.getService(
						serviceReference);

					emitter.emit(mapProvider.getKey());

					bundleContext.ungetService(serviceReference);
				}

			});
	}

	@Deactivate
	protected void deactivate() {
		_mapProviders.close();
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.map.taglib)", unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static GroupLocalService _groupLocalService;
	private static ServiceTrackerMap<String, MapProvider> _mapProviders;
	private static ServletContext _servletContext;

}