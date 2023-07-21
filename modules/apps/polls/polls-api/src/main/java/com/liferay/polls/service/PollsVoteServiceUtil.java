/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service;

import com.liferay.polls.model.PollsVote;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for PollsVote. This utility wraps
 * <code>com.liferay.polls.service.impl.PollsVoteServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteService
 * @generated
 */
public class PollsVoteServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.polls.service.impl.PollsVoteServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PollsVote addVote(
			long questionId, long choiceId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addVote(questionId, choiceId, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static PollsVoteService getService() {
		return _service;
	}

	public static void setService(PollsVoteService service) {
		_service = service;
	}

	private static volatile PollsVoteService _service;

}