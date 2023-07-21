/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.internal.util;

import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = AssetPublisherCustomizerRegistry.class)
public class DefaultAssetPublisherCustomizerRegistry
	implements AssetPublisherCustomizerRegistry {

	@Override
	public AssetPublisherCustomizer getAssetPublisherCustomizer(
		String portletId) {

		AssetPublisherCustomizer assetPublisherCustomizer =
			_assetPublisherCustomizers.get(portletId);

		if (assetPublisherCustomizer == null) {
			assetPublisherCustomizer = _assetPublisherCustomizers.get(
				AssetPublisherPortletKeys.ASSET_PUBLISHER);
		}

		return assetPublisherCustomizer;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	public void registerAssetPublisherCustomizer(
		AssetPublisherCustomizer assetPublisherCustomizer) {

		_assetPublisherCustomizers.put(
			assetPublisherCustomizer.getPortletId(), assetPublisherCustomizer);
	}

	public void unregisterAssetPublisherCustomizer(
		AssetPublisherCustomizer assetPublisherCustomizer) {

		_assetPublisherCustomizers.remove(
			assetPublisherCustomizer.getPortletId());
	}

	private final Map<String, AssetPublisherCustomizer>
		_assetPublisherCustomizers = new ConcurrentHashMap<>();

}