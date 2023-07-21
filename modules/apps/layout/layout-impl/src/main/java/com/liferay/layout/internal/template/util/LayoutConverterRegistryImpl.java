/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.template.util;

import com.liferay.layout.util.template.LayoutConverter;
import com.liferay.layout.util.template.LayoutConverterRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = LayoutConverterRegistry.class)
public class LayoutConverterRegistryImpl implements LayoutConverterRegistry {

	@Override
	public LayoutConverter getLayoutConverter(String layoutTemplateId) {
		return _layoutConverters.getService(layoutTemplateId);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_layoutConverters = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, LayoutConverter.class, "layout.template.id");
	}

	private ServiceTrackerMap<String, LayoutConverter> _layoutConverters;

}