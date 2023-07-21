/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.render;

import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRendererRegistry;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRendererRegistryUtil;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Pablo Carvalho
 */
@Component(immediate = true, service = DDMFormFieldRendererRegistry.class)
public class DDMFormFieldRendererRegistryImpl
	implements DDMFormFieldRendererRegistry {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceRegistration = bundleContext.registerService(
			DDMFormFieldRenderer.class, new DDMFormFieldFreeMarkerRenderer(),
			null);

		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, DDMFormFieldRenderer.class, null,
			(serviceReference, emitter) -> {
				DDMFormFieldRenderer ddmFormFieldRenderer =
					bundleContext.getService(serviceReference);

				try {
					for (String supportedDDMFormFieldType :
							ddmFormFieldRenderer.
								getSupportedDDMFormFieldTypes()) {

						emitter.emit(supportedDDMFormFieldType);
					}
				}
				finally {
					bundleContext.ungetService(serviceReference);
				}
			});

		DDMFormFieldRendererRegistryUtil ddmFormFieldRendererRegistryUtil =
			new DDMFormFieldRendererRegistryUtil();

		ddmFormFieldRendererRegistryUtil.setDDMFormFieldRendererRegistry(this);
	}

	@Deactivate
	public void deactivate() {
		_ddmFormFieldRendererRegistryUtil.setDDMFormFieldRendererRegistry(null);

		_serviceTrackerMap.close();

		_serviceRegistration.unregister();
	}

	@Override
	public DDMFormFieldRenderer getDDMFormFieldRenderer(
		String ddmFormFieldType) {

		return _serviceTrackerMap.getService(ddmFormFieldType);
	}

	private final DDMFormFieldRendererRegistryUtil
		_ddmFormFieldRendererRegistryUtil =
			new DDMFormFieldRendererRegistryUtil();
	private ServiceRegistration<?> _serviceRegistration;
	private ServiceTrackerMap<String, DDMFormFieldRenderer> _serviceTrackerMap;

}