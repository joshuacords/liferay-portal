/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.internal.scaler;

import com.liferay.adaptive.media.image.scaler.AMImageScaler;
import com.liferay.adaptive.media.image.scaler.AMImageScalerTracker;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Sergio González
 */
@Component(service = AMImageScalerTracker.class)
public class AMImageScalerTrackerImpl implements AMImageScalerTracker {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, AMImageScaler.class, "(mime.type=*)",
			new PropertyServiceReferenceMapper<>("mime.type"),
			new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Deactivate
	public void deactivate() {
		_serviceTrackerMap.close();
	}

	@Override
	public AMImageScaler getAMImageScaler(String mimeType) {
		List<AMImageScaler> amImageScalers = _serviceTrackerMap.getService(
			mimeType);

		if (ListUtil.isNotEmpty(amImageScalers)) {
			for (AMImageScaler amImageScaler : amImageScalers) {
				if (amImageScaler.isEnabled()) {
					return amImageScaler;
				}
			}
		}

		amImageScalers = _serviceTrackerMap.getService("*");

		if (ListUtil.isNotEmpty(amImageScalers)) {
			for (AMImageScaler amImageScaler : amImageScalers) {
				if (amImageScaler.isEnabled()) {
					return amImageScaler;
				}
			}
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unable to find default image scaler");
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AMImageScalerTrackerImpl.class);

	private ServiceTrackerMap<String, List<AMImageScaler>> _serviceTrackerMap;

}