/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.template.TemplateResource;

import java.util.List;
import java.util.Locale;

/**
 * @author Bruno Basto
 */
public class SoyTofuCacheHandler {

	public SoyTofuCacheHandler(
		PortalCache<String, SoyTofuCacheBag> portalCache) {

		_portalCache = portalCache;
	}

	public SoyTofuCacheBag add(
		String templateId, SoyFileSet soyFileSet, SoyTofu soyTofu) {

		SoyTofuCacheBag soyTofuCacheBag = new SoyTofuCacheBag(
			soyFileSet, soyTofu);

		_portalCache.put(templateId, soyTofuCacheBag);

		return soyTofuCacheBag;
	}

	public SoyTofuCacheBag get(String templateId) {
		return _portalCache.get(templateId);
	}

	public void removeIfAny(List<TemplateResource> templateResources) {
		for (TemplateResource templateResource : templateResources) {
			String templateId = templateResource.getTemplateId();

			for (String key : _portalCache.getKeys()) {
				if (key.equals(templateId) ||
					key.startsWith(templateId + StringPool.COMMA) ||
					key.endsWith(StringPool.COMMA + templateId) ||
					key.contains(
						StringPool.COMMA + templateId + StringPool.COMMA)) {

					_portalCache.remove(key);
				}
			}
		}
	}

	public void removeIfAny(Locale locale) {
		for (String key : _portalCache.getKeys()) {
			SoyTofuCacheBag soyTofuCacheBag = _portalCache.get(key);

			if (soyTofuCacheBag != null) {
				soyTofuCacheBag.removeMessageBundle(locale);
			}
		}
	}

	private final PortalCache<String, SoyTofuCacheBag> _portalCache;

}