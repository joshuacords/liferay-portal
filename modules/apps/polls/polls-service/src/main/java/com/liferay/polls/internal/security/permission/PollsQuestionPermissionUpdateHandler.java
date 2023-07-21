/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.security.permission;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsQuestionLocalService;
import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gergely Mathe
 */
@Component(
	property = "model.class.name=com.liferay.polls.model.PollsQuestion",
	service = PermissionUpdateHandler.class
)
public class PollsQuestionPermissionUpdateHandler
	implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		PollsQuestion pollsQuestion =
			_pollsQuestionLocalService.fetchPollsQuestion(
				GetterUtil.getLong(primKey));

		if (pollsQuestion == null) {
			return;
		}

		pollsQuestion.setModifiedDate(new Date());

		_pollsQuestionLocalService.updatePollsQuestion(pollsQuestion);
	}

	@Reference(unbind = "-")
	protected void setPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {

		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	private PollsQuestionLocalService _pollsQuestionLocalService;

}