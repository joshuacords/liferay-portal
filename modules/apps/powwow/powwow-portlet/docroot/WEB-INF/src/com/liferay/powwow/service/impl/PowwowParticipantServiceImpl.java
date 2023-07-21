/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.base.PowwowParticipantServiceBaseImpl;
import com.liferay.powwow.service.permission.PowwowMeetingPermission;
import com.liferay.powwow.util.ActionKeys;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class PowwowParticipantServiceImpl
	extends PowwowParticipantServiceBaseImpl {

	@Override
	public PowwowParticipant deletePowwowParticipant(
			PowwowParticipant powwowParticipant)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowParticipant.getPowwowMeetingId(),
			ActionKeys.UPDATE);

		return powwowParticipantPersistence.remove(powwowParticipant);
	}

	@Override
	public List<PowwowParticipant> getPowwowParticipants(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.VIEW);

		return powwowParticipantLocalService.getPowwowParticipants(
			powwowMeetingId);
	}

	@Override
	public int getPowwowParticipantsCount(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.VIEW);

		return powwowParticipantLocalService.getPowwowParticipantsCount(
			powwowMeetingId);
	}

	@Override
	public PowwowParticipant updatePowwowParticipant(
			long powwowParticipantId, long powwowMeetingId, String name,
			long participantUserId, String emailAddress, int type, int status,
			ServiceContext serviceContext)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.UPDATE);

		return powwowParticipantLocalService.updatePowwowParticipant(
			powwowParticipantId, powwowMeetingId, name, participantUserId,
			emailAddress, type, status, serviceContext);
	}

}