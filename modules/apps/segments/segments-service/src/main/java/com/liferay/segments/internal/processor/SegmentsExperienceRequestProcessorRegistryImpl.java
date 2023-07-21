/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.processor;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.segments.processor.SegmentsExperienceRequestProcessor;
import com.liferay.segments.processor.SegmentsExperienceRequestProcessorRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true, service = SegmentsExperienceRequestProcessorRegistry.class
)
public class SegmentsExperienceRequestProcessorRegistryImpl
	implements SegmentsExperienceRequestProcessorRegistry {

	@Override
	public long[] getSegmentsExperienceIds(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long groupId,
			long classNameId, long classPK)
		throws PortalException {

		long[] segmentsExperienceIds = new long[0];

		for (SegmentsExperienceRequestProcessor
				segmentsExperienceRequestProcessor :
					getSegmentsExperienceRequestProcessors()) {

			segmentsExperienceIds =
				segmentsExperienceRequestProcessor.getSegmentsExperienceIds(
					httpServletRequest, httpServletResponse, groupId,
					classNameId, classPK, segmentsExperienceIds);
		}

		return segmentsExperienceIds;
	}

	@Override
	public long[] getSegmentsExperienceIds(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long groupId,
			long classNameId, long classPK, long[] segmentsEntryIds)
		throws PortalException {

		long[] segmentsExperienceIds = new long[0];

		for (SegmentsExperienceRequestProcessor
				segmentsExperienceRequestProcessor :
					getSegmentsExperienceRequestProcessors()) {

			segmentsExperienceIds =
				segmentsExperienceRequestProcessor.getSegmentsExperienceIds(
					httpServletRequest, httpServletResponse, groupId,
					classNameId, classPK, segmentsEntryIds,
					segmentsExperienceIds);
		}

		return segmentsExperienceIds;
	}

	@Override
	public List<SegmentsExperienceRequestProcessor>
		getSegmentsExperienceRequestProcessors() {

		List<SegmentsExperienceRequestProcessor>
			segmentsExperienceRequestProcessors = new ArrayList<>();

		_serviceTrackerList.forEach(segmentsExperienceRequestProcessors::add);

		return segmentsExperienceRequestProcessors;
	}

	@Activate
	protected void activate(final BundleContext bundleContext) {
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, SegmentsExperienceRequestProcessor.class,
			Collections.reverseOrder(
				new PropertyServiceReferenceComparator(
					"segments.experience.request.processor.priority")));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private ServiceTrackerList
		<SegmentsExperienceRequestProcessor, SegmentsExperienceRequestProcessor>
			_serviceTrackerList;

}