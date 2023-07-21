/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.segments.SegmentsEntryRetriever;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.context.Context;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.simulator.SegmentsEntrySimulator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Cristina González
 */
@Component(service = SegmentsEntryRetriever.class)
public class SegmentsEntryRetrieverImpl implements SegmentsEntryRetriever {

	@Override
	public long[] getSegmentsEntryIds(
		long groupId, long userId, Context context, long[] segmentEntryIds) {

		return ArrayUtil.toLongArray(
			SetUtil.fromArray(
				ArrayUtil.append(
					_getSegmentEntryIds(
						groupId, userId, context, segmentEntryIds),
					SegmentsEntryConstants.ID_DEFAULT)));
	}

	private long[] _getSegmentEntryIds(
		long groupId, long userId, Context context, long[] segmentEntryIds) {

		if ((_segmentsEntrySimulator != null) &&
			_segmentsEntrySimulator.isSimulationActive(userId)) {

			return _segmentsEntrySimulator.getSimulatedSegmentsEntryIds(userId);
		}

		try {
			return _segmentsEntryProviderRegistry.getSegmentsEntryIds(
				groupId, User.class.getName(), userId, context,
				segmentEntryIds);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}

			return new long[0];
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsEntryRetrieverImpl.class);

	@Reference
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.liferay.portal.kernel.model.User)"
	)
	private volatile SegmentsEntrySimulator _segmentsEntrySimulator;

}