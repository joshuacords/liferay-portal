/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.web.internal.security.permission.resource;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class PollsQuestionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long questionId,
			String actionId)
		throws PortalException {

		return _pollsQuestionModelResourcePermission.contains(
			permissionChecker, questionId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, PollsQuestion question,
			String actionId)
		throws PortalException {

		return _pollsQuestionModelResourcePermission.contains(
			permissionChecker, question, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.polls.model.PollsQuestion)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<PollsQuestion> modelResourcePermission) {

		_pollsQuestionModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<PollsQuestion>
		_pollsQuestionModelResourcePermission;

}