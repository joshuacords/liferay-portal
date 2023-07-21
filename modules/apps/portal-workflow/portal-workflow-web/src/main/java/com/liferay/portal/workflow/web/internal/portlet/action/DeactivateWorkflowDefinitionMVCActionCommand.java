/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.web.internal.portlet.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WorkflowPortletKeys.CONTROL_PANEL_WORKFLOW,
		"mvc.command.name=deactivateWorkflowDefinition"
	},
	service = MVCActionCommand.class
)
public class DeactivateWorkflowDefinitionMVCActionCommand
	extends RestoreWorkflowDefinitionMVCActionCommand {

	@Override
	protected String getSuccessMessage(ActionRequest actionRequest) {
		return LanguageUtil.get(
			getResourceBundle(actionRequest),
			"workflow-unpublished-successfully");
	}

	@Override
	protected boolean isActive() {
		return false;
	}

}