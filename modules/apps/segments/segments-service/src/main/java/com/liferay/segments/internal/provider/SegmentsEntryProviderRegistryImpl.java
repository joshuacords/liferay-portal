/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.provider;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.segments.context.Context;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.provider.SegmentsEntryProvider;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(immediate = true, service = SegmentsEntryProviderRegistry.class)
public class SegmentsEntryProviderRegistryImpl
	implements SegmentsEntryProviderRegistry {

	@Override
	public long[] getSegmentsEntryClassPKs(
			long segmentsEntryId, int start, int end)
		throws PortalException {

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.getSegmentsEntry(segmentsEntryId);

		SegmentsEntryProvider segmentsEntryProvider = getSegmentsEntryProvider(
			segmentsEntry.getSource());

		if (segmentsEntryProvider == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No segments entry provider found for source " +
						segmentsEntry.getSource());
			}

			return new long[0];
		}

		return segmentsEntryProvider.getSegmentsEntryClassPKs(
			segmentsEntryId, start, end);
	}

	@Override
	public int getSegmentsEntryClassPKsCount(long segmentsEntryId)
		throws PortalException {

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.getSegmentsEntry(segmentsEntryId);

		SegmentsEntryProvider segmentsEntryProvider = getSegmentsEntryProvider(
			segmentsEntry.getSource());

		if (segmentsEntryProvider == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No segments entry provider found for source " +
						segmentsEntry.getSource());
			}

			return 0;
		}

		return segmentsEntryProvider.getSegmentsEntryClassPKsCount(
			segmentsEntryId);
	}

	@Override
	public long[] getSegmentsEntryIds(
			long groupId, String className, long classPK, Context context,
			long[] segmentEntryIds)
		throws PortalException {

		long[] finalSegmentsEntryIds = new long[0];

		for (SegmentsEntryProvider segmentsEntryProvider :
				_serviceTrackerMap.values()) {

			finalSegmentsEntryIds = ArrayUtil.append(
				finalSegmentsEntryIds,
				segmentsEntryProvider.getSegmentsEntryIds(
					groupId, className, classPK, context, segmentEntryIds,
					finalSegmentsEntryIds));
		}

		Set<Long> segmentsEntryIdsSet = SetUtil.fromArray(
			finalSegmentsEntryIds);

		return ArrayUtil.toLongArray(segmentsEntryIdsSet);
	}

	@Override
	public SegmentsEntryProvider getSegmentsEntryProvider(String source) {
		return _serviceTrackerMap.getService(source);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, SegmentsEntryProvider.class,
			"segments.entry.provider.source");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsEntryProviderRegistryImpl.class);

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	private ServiceTrackerMap<String, SegmentsEntryProvider> _serviceTrackerMap;

}