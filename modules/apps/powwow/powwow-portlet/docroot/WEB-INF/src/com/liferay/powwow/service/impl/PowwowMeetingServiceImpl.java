/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.base.PowwowMeetingServiceBaseImpl;
import com.liferay.powwow.service.permission.MeetingsPermission;
import com.liferay.powwow.service.permission.PowwowMeetingPermission;
import com.liferay.powwow.util.ActionKeys;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class PowwowMeetingServiceImpl extends PowwowMeetingServiceBaseImpl {

	@Override
	public PowwowMeeting addPowwowMeeting(
			long groupId, String portletId, long powwowServerId, String name,
			String description, String providerType,
			Map<String, Serializable> providerTypeMetadataMap,
			String languageId, long calendarBookingId, int status,
			List<PowwowParticipant> powwowParticipants,
			ServiceContext serviceContext)
		throws PortalException {

		MeetingsPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_MEETING);

		return powwowMeetingLocalService.addPowwowMeeting(
			getUserId(), groupId, powwowServerId, name, description,
			providerType, providerTypeMetadataMap, languageId,
			calendarBookingId, status, powwowParticipants, serviceContext);
	}

	@Override
	public PowwowMeeting deletePowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.DELETE);

		return powwowMeetingLocalService.deletePowwowMeeting(powwowMeetingId);
	}

	@Override
	public PowwowMeeting getPowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.VIEW);

		return powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId);
	}

	@Override
	public List<PowwowMeeting> getPowwowMeetings(
		long groupId, int start, int end, OrderByComparator obc) {

		return powwowMeetingPersistence.filterFindByGroupId(
			groupId, start, end, obc);
	}

	@Override
	public int getPowwowMeetingsCount(long groupId) {
		return powwowMeetingPersistence.filterCountByGroupId(groupId);
	}

	@Override
	public PowwowMeeting updatePowwowMeeting(
			long powwowMeetingId, long powwowServerId, String name,
			String description, String providerType,
			Map<String, Serializable> providerTypeMetadataMap,
			String languageId, long calendarBookingId, int status,
			List<PowwowParticipant> powwowParticipants,
			ServiceContext serviceContext)
		throws PortalException {

		PowwowMeetingPermission.check(
			getPermissionChecker(), powwowMeetingId, ActionKeys.UPDATE);

		return powwowMeetingLocalService.updatePowwowMeeting(
			powwowMeetingId, powwowServerId, name, description, providerType,
			providerTypeMetadataMap, languageId, calendarBookingId, status,
			powwowParticipants, serviceContext);
	}

}