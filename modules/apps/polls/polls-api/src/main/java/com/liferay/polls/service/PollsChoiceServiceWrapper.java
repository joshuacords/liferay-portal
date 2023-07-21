/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PollsChoiceService}.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoiceService
 * @generated
 */
public class PollsChoiceServiceWrapper
	implements PollsChoiceService, ServiceWrapper<PollsChoiceService> {

	public PollsChoiceServiceWrapper(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pollsChoiceService.getOSGiServiceIdentifier();
	}

	@Override
	public PollsChoiceService getWrappedService() {
		return _pollsChoiceService;
	}

	@Override
	public void setWrappedService(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	private PollsChoiceService _pollsChoiceService;

}