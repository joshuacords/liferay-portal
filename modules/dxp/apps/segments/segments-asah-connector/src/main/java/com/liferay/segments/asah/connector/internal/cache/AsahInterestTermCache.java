/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.cache;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.segments.asah.connector.internal.configuration.SegmentsAsahConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(
	configurationPid = "com.liferay.segments.asah.connector.internal.configuration.SegmentsAsahConfiguration",
	immediate = true, service = AsahInterestTermCache.class
)
public class AsahInterestTermCache {

	public String[] getInterestTerms(String userId) {
		return _portalCache.get(_generateCacheKey(userId));
	}

	public void putInterestTerms(String userId, String[] terms) {
		_portalCache.put(
			_generateCacheKey(userId), terms,
			_interestTermsTimeToLiveInSeconds);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		SegmentsAsahConfiguration segmentsAsahConfiguration =
			ConfigurableUtil.createConfigurable(
				SegmentsAsahConfiguration.class, properties);

		_interestTermsTimeToLiveInSeconds =
			segmentsAsahConfiguration.interestTermsCacheExpirationTime();
	}

	@Reference(unbind = "-")
	protected void setMultiVMPool(MultiVMPool multiVMPool) {
		_portalCache =
			(PortalCache<String, String[]>)multiVMPool.getPortalCache(
				AsahInterestTermCache.class.getName());
	}

	private String _generateCacheKey(String userId) {
		return _CACHE_PREFIX + userId;
	}

	private static final String _CACHE_PREFIX = "segments-";

	private int _interestTermsTimeToLiveInSeconds =
		PortalCache.DEFAULT_TIME_TO_LIVE;
	private PortalCache<String, String[]> _portalCache;

}