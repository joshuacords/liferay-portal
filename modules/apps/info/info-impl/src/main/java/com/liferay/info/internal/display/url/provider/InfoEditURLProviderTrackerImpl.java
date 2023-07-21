/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.display.url.provider;

import com.liferay.info.display.url.provider.InfoEditURLProvider;
import com.liferay.info.display.url.provider.InfoEditURLProviderTracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = InfoEditURLProviderTracker.class)
public class InfoEditURLProviderTrackerImpl
	implements InfoEditURLProviderTracker {

	@Override
	public InfoEditURLProvider getInfoEditURLProvider(String className) {
		return _infoEditURLProviders.get(className);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setInfoEditURLProviders(
		InfoEditURLProvider infoEditURLProvider,
		Map<String, Object> properties) {

		String className = (String)properties.get("model.class.name");

		_infoEditURLProviders.put(className, infoEditURLProvider);
	}

	protected void unsetInfoEditURLProviders(
		InfoEditURLProvider infoEditURLProvider,
		Map<String, Object> properties) {

		String className = (String)properties.get("model.class.name");

		_infoEditURLProviders.remove(className);
	}

	private final Map<String, InfoEditURLProvider> _infoEditURLProviders =
		new ConcurrentHashMap<>();

}