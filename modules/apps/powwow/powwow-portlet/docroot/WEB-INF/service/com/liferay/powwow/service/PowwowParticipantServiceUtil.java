/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.powwow.model.PowwowParticipant;

import java.util.List;

/**
 * Provides the remote service utility for PowwowParticipant. This utility wraps
 * <code>com.liferay.powwow.service.impl.PowwowParticipantServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Shinn Lok
 * @see PowwowParticipantService
 * @generated
 */
public class PowwowParticipantServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.powwow.service.impl.PowwowParticipantServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PowwowParticipant deletePowwowParticipant(
			PowwowParticipant powwowParticipant)
		throws PortalException {

		return getService().deletePowwowParticipant(powwowParticipant);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<PowwowParticipant> getPowwowParticipants(
			long powwowMeetingId)
		throws PortalException {

		return getService().getPowwowParticipants(powwowMeetingId);
	}

	public static int getPowwowParticipantsCount(long powwowMeetingId)
		throws PortalException {

		return getService().getPowwowParticipantsCount(powwowMeetingId);
	}

	public static PowwowParticipant updatePowwowParticipant(
			long powwowParticipantId, long powwowMeetingId, String name,
			long participantUserId, String emailAddress, int type, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updatePowwowParticipant(
			powwowParticipantId, powwowMeetingId, name, participantUserId,
			emailAddress, type, status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static PowwowParticipantService getService() {
		return _service;
	}

	public static void setService(PowwowParticipantService service) {
		_service = service;
	}

	private static volatile PowwowParticipantService _service;

}