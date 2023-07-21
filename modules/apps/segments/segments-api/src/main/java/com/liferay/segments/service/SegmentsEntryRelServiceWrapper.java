/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SegmentsEntryRelService}.
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryRelService
 * @generated
 */
public class SegmentsEntryRelServiceWrapper
	implements SegmentsEntryRelService,
			   ServiceWrapper<SegmentsEntryRelService> {

	public SegmentsEntryRelServiceWrapper(
		SegmentsEntryRelService segmentsEntryRelService) {

		_segmentsEntryRelService = segmentsEntryRelService;
	}

	@Override
	public com.liferay.segments.model.SegmentsEntryRel addSegmentsEntryRel(
			long segmentsEntryId, long classNameId, long classPK,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.addSegmentsEntryRel(
			segmentsEntryId, classNameId, classPK, serviceContext);
	}

	@Override
	public void deleteSegmentsEntryRel(long segmentsEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_segmentsEntryRelService.deleteSegmentsEntryRel(segmentsEntryRelId);
	}

	@Override
	public void deleteSegmentsEntryRel(
			long segmentsEntryId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_segmentsEntryRelService.deleteSegmentsEntryRel(
			segmentsEntryId, classNameId, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _segmentsEntryRelService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsEntryRel>
			getSegmentsEntryRels(long segmentsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.getSegmentsEntryRels(segmentsEntryId);
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsEntryRel>
			getSegmentsEntryRels(
				long segmentsEntryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.segments.model.SegmentsEntryRel>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.getSegmentsEntryRels(
			segmentsEntryId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.segments.model.SegmentsEntryRel>
			getSegmentsEntryRels(long groupId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.getSegmentsEntryRels(
			groupId, classNameId, classPK);
	}

	@Override
	public int getSegmentsEntryRelsCount(long segmentsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.getSegmentsEntryRelsCount(
			segmentsEntryId);
	}

	@Override
	public int getSegmentsEntryRelsCount(
			long groupId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _segmentsEntryRelService.getSegmentsEntryRelsCount(
			groupId, classNameId, classPK);
	}

	@Override
	public boolean hasSegmentsEntryRel(
		long segmentsEntryId, long classNameId, long classPK) {

		return _segmentsEntryRelService.hasSegmentsEntryRel(
			segmentsEntryId, classNameId, classPK);
	}

	@Override
	public SegmentsEntryRelService getWrappedService() {
		return _segmentsEntryRelService;
	}

	@Override
	public void setWrappedService(
		SegmentsEntryRelService segmentsEntryRelService) {

		_segmentsEntryRelService = segmentsEntryRelService;
	}

	private SegmentsEntryRelService _segmentsEntryRelService;

}