/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SegmentsExperimentService}.
 *
 * @author Eduardo Garcia
 * @see SegmentsExperimentService
 * @generated
 */
public class SegmentsExperimentServiceWrapper
	implements SegmentsExperimentService,
			   ServiceWrapper<SegmentsExperimentService> {

	public SegmentsExperimentServiceWrapper(
		SegmentsExperimentService segmentsExperimentService) {

		_segmentsExperimentService = segmentsExperimentService;
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment addSegmentsExperiment(
			long segmentsExperienceId, long classNameId, long classPK,
			String name, String description, String goal, String goalTarget,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.addSegmentsExperiment(
			segmentsExperienceId, classNameId, classPK, name, description, goal,
			goalTarget, serviceContext);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			deleteSegmentsExperiment(long segmentsExperimentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.deleteSegmentsExperiment(
			segmentsExperimentId);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			deleteSegmentsExperiment(String segmentsExperimentKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.deleteSegmentsExperiment(
			segmentsExperimentKey);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			fetchSegmentsExperiment(
				long segmentsExperienceId, long classNameId, long classPK,
				int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.fetchSegmentsExperiment(
			segmentsExperienceId, classNameId, classPK, statuses);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			fetchSegmentsExperiment(long groupId, String segmentsExperimentKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.fetchSegmentsExperiment(
			groupId, segmentsExperimentKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _segmentsExperimentService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsExperiment>
			getSegmentsExperienceSegmentsExperiments(
				long[] segmentsExperienceIds, long classNameId, long classPK,
				int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.
			getSegmentsExperienceSegmentsExperiments(
				segmentsExperienceIds, classNameId, classPK, statuses, start,
				end);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment getSegmentsExperiment(
			long segmentsExperimentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.getSegmentsExperiment(
			segmentsExperimentId);
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsExperiment>
		getSegmentsExperiments(long groupId, long classNameId, long classPK) {

		return _segmentsExperimentService.getSegmentsExperiments(
			groupId, classNameId, classPK);
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsExperiment>
		getSegmentsExperiments(
			long segmentsExperienceId, long classNameId, long classPK,
			int[] statuses,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.segments.model.SegmentsExperiment>
					orderByComparator) {

		return _segmentsExperimentService.getSegmentsExperiments(
			segmentsExperienceId, classNameId, classPK, statuses,
			orderByComparator);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment runSegmentsExperiment(
			long segmentsExperimentId, double confidenceLevel,
			java.util.Map<Long, Double> segmentsExperienceIdSplitMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.runSegmentsExperiment(
			segmentsExperimentId, confidenceLevel,
			segmentsExperienceIdSplitMap);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment runSegmentsExperiment(
			String segmentsExperimentKey, double confidenceLevel,
			java.util.Map<String, Double> segmentsExperienceKeySplitMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.runSegmentsExperiment(
			segmentsExperimentKey, confidenceLevel,
			segmentsExperienceKeySplitMap);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			updateSegmentsExperiment(
				long segmentsExperimentId, String name, String description,
				String goal, String goalTarget)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.updateSegmentsExperiment(
			segmentsExperimentId, name, description, goal, goalTarget);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			updateSegmentsExperimentStatus(
				long segmentsExperimentId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.updateSegmentsExperimentStatus(
			segmentsExperimentId, status);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			updateSegmentsExperimentStatus(
				long segmentsExperimentId, long winnerSegmentsExperienceId,
				int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.updateSegmentsExperimentStatus(
			segmentsExperimentId, winnerSegmentsExperienceId, status);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			updateSegmentsExperimentStatus(
				String segmentsExperimentKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.updateSegmentsExperimentStatus(
			segmentsExperimentKey, status);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperiment
			updateSegmentsExperimentStatus(
				String segmentsExperimentKey,
				String winnerSegmentsExperienceKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentService.updateSegmentsExperimentStatus(
			segmentsExperimentKey, winnerSegmentsExperienceKey, status);
	}

	@Override
	public SegmentsExperimentService getWrappedService() {
		return _segmentsExperimentService;
	}

	@Override
	public void setWrappedService(
		SegmentsExperimentService segmentsExperimentService) {

		_segmentsExperimentService = segmentsExperimentService;
	}

	private SegmentsExperimentService _segmentsExperimentService;

}