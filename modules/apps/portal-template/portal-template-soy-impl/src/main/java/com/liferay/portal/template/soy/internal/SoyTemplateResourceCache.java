/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.template.BaseTemplateResourceCache;
import com.liferay.portal.template.soy.internal.configuration.SoyTemplateEngineConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(
	configurationPid = "com.liferay.portal.template.soy.internal.configuration.SoyTemplateEngineConfiguration",
	immediate = true, service = SoyTemplateResourceCache.class
)
public class SoyTemplateResourceCache extends BaseTemplateResourceCache {

	@Activate
	protected void activate(Map<String, Object> properties) {
		SoyTemplateEngineConfiguration soyTemplateEngineConfiguration =
			ConfigurableUtil.createConfigurable(
				SoyTemplateEngineConfiguration.class, properties);

		init(
			soyTemplateEngineConfiguration.resourceModificationCheck(),
			_multiVMPool, _singleVMPool, _PORTAL_CACHE_NAME);
	}

	@Deactivate
	protected void deactivate() {
		destroy();
	}

	private static final String _PORTAL_CACHE_NAME =
		SoyTemplateResourceCache.class.getName();

	@Reference
	private MultiVMPool _multiVMPool;

	@Reference
	private SingleVMPool _singleVMPool;

}