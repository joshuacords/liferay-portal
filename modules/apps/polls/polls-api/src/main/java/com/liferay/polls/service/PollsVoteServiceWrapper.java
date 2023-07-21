/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PollsVoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteService
 * @generated
 */
public class PollsVoteServiceWrapper
	implements PollsVoteService, ServiceWrapper<PollsVoteService> {

	public PollsVoteServiceWrapper(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	@Override
	public com.liferay.polls.model.PollsVote addVote(
			long questionId, long choiceId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pollsVoteService.addVote(questionId, choiceId, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pollsVoteService.getOSGiServiceIdentifier();
	}

	@Override
	public PollsVoteService getWrappedService() {
		return _pollsVoteService;
	}

	@Override
	public void setWrappedService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	private PollsVoteService _pollsVoteService;

}