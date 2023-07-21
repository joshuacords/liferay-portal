/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.task.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.workflow.task.web.internal.permission.WorkflowTaskPermissionChecker;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.MY_WORKFLOW_TASK,
		"mvc.command.name=assignWorkflowTask"
	},
	service = MVCResourceCommand.class
)
public class AssignTaskMVCResourceCommand extends BaseMVCResourceCommand {

	protected void checkWorkflowTaskAssignmentPermission(
			long workflowTaskId, ThemeDisplay themeDisplay)
		throws Exception {

		WorkflowTask workflowTask = workflowTaskManager.getWorkflowTask(
			themeDisplay.getCompanyId(), workflowTaskId);

		long groupId = MapUtil.getLong(
			workflowTask.getOptionalAttributes(), "groupId",
			themeDisplay.getSiteGroupId());

		_workflowTaskPermissionChecker.check(
			groupId, workflowTask, themeDisplay.getPermissionChecker());
	}

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long workflowTaskId = ParamUtil.getLong(
			resourceRequest, "workflowTaskId");

		long assigneeUserId = ParamUtil.getLong(
			resourceRequest, "assigneeUserId");
		String comment = ParamUtil.getString(resourceRequest, "comment");

		checkWorkflowTaskAssignmentPermission(workflowTaskId, themeDisplay);

		workflowTaskManager.assignWorkflowTaskToUser(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			workflowTaskId, assigneeUserId, comment, null, null);

		SessionMessages.add(resourceRequest, "requestProcessed", "");
	}

	@Reference
	protected WorkflowTaskManager workflowTaskManager;

	private final WorkflowTaskPermissionChecker _workflowTaskPermissionChecker =
		new WorkflowTaskPermissionChecker();

}