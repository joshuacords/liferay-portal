/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.processor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.model.SegmentsExperienceModel;
import com.liferay.segments.processor.SegmentsExperienceRequestProcessor;
import com.liferay.segments.service.SegmentsExperienceLocalService;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = "segments.experience.request.processor.priority:Integer=0",
	service = SegmentsExperienceRequestProcessor.class
)
public class DefaultSegmentsExperienceRequestProcessor
	implements SegmentsExperienceRequestProcessor {

	@Override
	public long[] getSegmentsExperienceIds(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long groupId,
			long classNameId, long classPK, long[] segmentsExperienceIds)
		throws PortalException {

		List<SegmentsExperience> segmentsExperiences =
			_segmentsExperienceLocalService.getSegmentsExperiences(
				groupId, classNameId, classPK, true);

		Stream<SegmentsExperience> stream = segmentsExperiences.stream();

		return stream.filter(
			segmentsExperience -> segmentsExperience.getPriority() >= 0
		).mapToLong(
			SegmentsExperienceModel::getSegmentsExperienceId
		).toArray();
	}

	@Override
	public long[] getSegmentsExperienceIds(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long groupId,
			long classNameId, long classPK, long[] segmentsEntryIds,
			long[] segmentsExperienceIds)
		throws PortalException {

		List<SegmentsExperience> segmentsExperiences =
			_segmentsExperienceLocalService.getSegmentsExperiences(
				groupId, segmentsEntryIds, classNameId, classPK, true);

		Stream<SegmentsExperience> stream = segmentsExperiences.stream();

		return stream.filter(
			segmentsExperience -> segmentsExperience.getPriority() >= 0
		).mapToLong(
			SegmentsExperienceModel::getSegmentsExperienceId
		).toArray();
	}

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

}