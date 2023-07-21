/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.test.util;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;

import java.util.List;

import org.apache.log4j.Level;

import org.junit.Assert;

/**
 * @author Inácio Nery
 */
public class CalendarWorkflowTestUtil {

	public static void activateWorkflow(Group group) throws PortalException {
		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			TestPropsValues.getUserId(), TestPropsValues.getCompanyId(),
			group.getGroupId(), CalendarBooking.class.getName(), 0, 0,
			"Single Approver", 1);
	}

	public static void completeWorkflow(Group group) throws Exception {
		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					"com.liferay.util.mail.MailEngine", Level.OFF)) {

			List<WorkflowTask> workflowTasks =
				WorkflowTaskManagerUtil.getWorkflowTasksByUserRoles(
					TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
					false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			Assert.assertEquals(
				workflowTasks.toString(), 1, workflowTasks.size());

			WorkflowTask workflowTask = workflowTasks.get(0);

			PermissionChecker userPermissionChecker =
				PermissionCheckerFactoryUtil.create(TestPropsValues.getUser());

			PermissionThreadLocal.setPermissionChecker(userPermissionChecker);

			WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				group.getCompanyId(), TestPropsValues.getUserId(),
				workflowTask.getWorkflowTaskId(), TestPropsValues.getUserId(),
				StringPool.BLANK, null, null);

			WorkflowTaskManagerUtil.completeWorkflowTask(
				group.getCompanyId(), TestPropsValues.getUserId(),
				workflowTask.getWorkflowTaskId(), Constants.APPROVE,
				StringPool.BLANK, null);
		}
	}

}