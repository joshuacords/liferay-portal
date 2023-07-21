/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.workflow.kaleo.KaleoTaskAssignmentFactory;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
public abstract class BaseTaskAssignmentSelector
	implements TaskAssignmentSelector {

	@SuppressWarnings("unchecked")
	protected Collection<KaleoTaskAssignment> getKaleoTaskAssignments(
		Map<String, ?> results) {

		List<KaleoTaskAssignment> kaleoTaskAssignments = new ArrayList<>();

		User user = (User)results.get(USER_ASSIGNMENT);

		if (user != null) {
			KaleoTaskAssignment kaleoTaskAssignment =
				getUserKaleoTaskAssignment(user);

			kaleoTaskAssignments.add(kaleoTaskAssignment);
		}
		else {
			List<Role> roles = (List<Role>)results.get(ROLES_ASSIGNMENT);

			getRoleKaleoTaskAssignments(roles, kaleoTaskAssignments);
		}

		return kaleoTaskAssignments;
	}

	protected void getRoleKaleoTaskAssignments(
		List<Role> roles, List<KaleoTaskAssignment> kaleoTaskAssignments) {

		if (roles == null) {
			return;
		}

		for (Role role : roles) {
			KaleoTaskAssignment kaleoTaskAssignment =
				kaleoTaskAssignmentFactory.createKaleoTaskAssignment();

			kaleoTaskAssignment.setAssigneeClassName(Role.class.getName());
			kaleoTaskAssignment.setAssigneeClassPK(role.getRoleId());

			kaleoTaskAssignments.add(kaleoTaskAssignment);
		}
	}

	protected KaleoTaskAssignment getUserKaleoTaskAssignment(User user) {
		KaleoTaskAssignment kaleoTaskAssignment =
			kaleoTaskAssignmentFactory.createKaleoTaskAssignment();

		kaleoTaskAssignment.setAssigneeClassName(User.class.getName());
		kaleoTaskAssignment.setAssigneeClassPK(user.getUserId());

		return kaleoTaskAssignment;
	}

	protected static final String ROLES_ASSIGNMENT = "roles";

	protected static final String USER_ASSIGNMENT = "user";

	@Reference
	protected KaleoTaskAssignmentFactory kaleoTaskAssignmentFactory;

}