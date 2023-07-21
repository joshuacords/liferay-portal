/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.model.PowwowParticipantConstants;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowParticipantLocalServiceUtil;

/**
 * @author Shinn Lok
 */
public class PowwowMeetingPermission {

	public static void check(
			PermissionChecker permissionChecker, long powwowMeetingId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, powwowMeetingId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, PowwowMeeting powwowMeeting,
			PowwowParticipant powwowParticipant, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, powwowMeeting, powwowParticipant,
				actionId)) {

			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long powwowMeetingId,
			String actionId)
		throws PortalException {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		User user = permissionChecker.getUser();

		PowwowParticipant powwowParticipant = null;

		if (user.getUserId() > 0) {
			powwowParticipant =
				PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
					powwowMeeting.getPowwowMeetingId(), user.getUserId());
		}
		else {
			powwowParticipant =
				PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
					powwowMeeting.getPowwowMeetingId(), user.getEmailAddress());
		}

		return contains(
			permissionChecker, powwowMeeting, powwowParticipant, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, PowwowMeeting powwowMeeting,
		PowwowParticipant powwowParticipant, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				powwowMeeting.getCompanyId(), PowwowMeeting.class.getName(),
				powwowMeeting.getPowwowMeetingId(), powwowMeeting.getUserId(),
				actionId)) {

			return true;
		}

		if (powwowParticipant == null) {
			return false;
		}

		if (powwowParticipant.getType() ==
				PowwowParticipantConstants.TYPE_HOST) {

			return true;
		}

		return permissionChecker.hasPermission(
			powwowMeeting.getGroupId(), PowwowMeeting.class.getName(),
			powwowMeeting.getPowwowServerId(), actionId);
	}

}