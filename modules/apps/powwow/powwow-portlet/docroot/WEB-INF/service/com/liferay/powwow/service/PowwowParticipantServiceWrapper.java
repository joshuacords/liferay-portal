/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PowwowParticipantService}.
 *
 * @author Shinn Lok
 * @see PowwowParticipantService
 * @generated
 */
public class PowwowParticipantServiceWrapper
	implements PowwowParticipantService,
			   ServiceWrapper<PowwowParticipantService> {

	public PowwowParticipantServiceWrapper(
		PowwowParticipantService powwowParticipantService) {

		_powwowParticipantService = powwowParticipantService;
	}

	@Override
	public com.liferay.powwow.model.PowwowParticipant deletePowwowParticipant(
			com.liferay.powwow.model.PowwowParticipant powwowParticipant)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowParticipantService.deletePowwowParticipant(
			powwowParticipant);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _powwowParticipantService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.powwow.model.PowwowParticipant>
			getPowwowParticipants(long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowParticipantService.getPowwowParticipants(powwowMeetingId);
	}

	@Override
	public int getPowwowParticipantsCount(long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowParticipantService.getPowwowParticipantsCount(
			powwowMeetingId);
	}

	@Override
	public com.liferay.powwow.model.PowwowParticipant updatePowwowParticipant(
			long powwowParticipantId, long powwowMeetingId, String name,
			long participantUserId, String emailAddress, int type, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _powwowParticipantService.updatePowwowParticipant(
			powwowParticipantId, powwowMeetingId, name, participantUserId,
			emailAddress, type, status, serviceContext);
	}

	@Override
	public PowwowParticipantService getWrappedService() {
		return _powwowParticipantService;
	}

	@Override
	public void setWrappedService(
		PowwowParticipantService powwowParticipantService) {

		_powwowParticipantService = powwowParticipantService;
	}

	private PowwowParticipantService _powwowParticipantService;

}