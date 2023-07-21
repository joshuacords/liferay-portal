/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SegmentsExperimentRelService}.
 *
 * @author Eduardo Garcia
 * @see SegmentsExperimentRelService
 * @generated
 */
public class SegmentsExperimentRelServiceWrapper
	implements SegmentsExperimentRelService,
			   ServiceWrapper<SegmentsExperimentRelService> {

	public SegmentsExperimentRelServiceWrapper(
		SegmentsExperimentRelService segmentsExperimentRelService) {

		_segmentsExperimentRelService = segmentsExperimentRelService;
	}

	@Override
	public com.liferay.segments.model.SegmentsExperimentRel
			addSegmentsExperimentRel(
				long segmentsExperimentId, long segmentsExperienceId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.addSegmentsExperimentRel(
			segmentsExperimentId, segmentsExperienceId, serviceContext);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperimentRel
			deleteSegmentsExperimentRel(long segmentsExperimentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.deleteSegmentsExperimentRel(
			segmentsExperimentRelId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _segmentsExperimentRelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.segments.model.SegmentsExperimentRel
			getSegmentsExperimentRel(
				long segmentsExperimentId, long segmentsExperienceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.getSegmentsExperimentRel(
			segmentsExperimentId, segmentsExperienceId);
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsExperimentRel>
			getSegmentsExperimentRels(long segmentsExperimentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.getSegmentsExperimentRels(
			segmentsExperimentId);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperimentRel
			updateSegmentsExperimentRel(
				long segmentsExperimentRelId, double split)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.updateSegmentsExperimentRel(
			segmentsExperimentRelId, split);
	}

	@Override
	public com.liferay.segments.model.SegmentsExperimentRel
			updateSegmentsExperimentRel(
				long segmentsExperimentRelId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsExperimentRelService.updateSegmentsExperimentRel(
			segmentsExperimentRelId, name, serviceContext);
	}

	@Override
	public SegmentsExperimentRelService getWrappedService() {
		return _segmentsExperimentRelService;
	}

	@Override
	public void setWrappedService(
		SegmentsExperimentRelService segmentsExperimentRelService) {

		_segmentsExperimentRelService = segmentsExperimentRelService;
	}

	private SegmentsExperimentRelService _segmentsExperimentRelService;

}