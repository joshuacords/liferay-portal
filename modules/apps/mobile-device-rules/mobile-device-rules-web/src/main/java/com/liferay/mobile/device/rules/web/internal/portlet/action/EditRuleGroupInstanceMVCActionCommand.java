/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.portlet.action;

import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.exception.NoSuchRuleGroupException;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Edward Han
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MDRPortletKeys.MOBILE_DEVICE_RULES,
		"mvc.command.name=/mobile_device_rules/edit_rule_group_instance"
	},
	service = MVCActionCommand.class
)
public class EditRuleGroupInstanceMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteRuleGroupInstance(ActionRequest actionRequest)
		throws PortalException {

		long ruleGroupInstanceId = ParamUtil.getLong(
			actionRequest, "ruleGroupInstanceId");

		_mdrRuleGroupInstanceService.deleteRuleGroupInstance(
			ruleGroupInstanceId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteRuleGroupInstance(actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateRuleGroupInstancesPriorities(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchRuleGroupException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw exception;
			}
		}
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupInstanceService(
		MDRRuleGroupInstanceService mdrRuleGroupInstanceService) {

		_mdrRuleGroupInstanceService = mdrRuleGroupInstanceService;
	}

	protected void updateRuleGroupInstancesPriorities(
			ActionRequest actionRequest)
		throws PortalException {

		String ruleGroupsInstancesJSON = ParamUtil.getString(
			actionRequest, "ruleGroupsInstancesJSON");

		JSONArray ruleGroupsInstancesJSONArray =
			JSONFactoryUtil.createJSONArray(ruleGroupsInstancesJSON);

		for (int i = 0; i < ruleGroupsInstancesJSONArray.length(); i++) {
			JSONObject ruleGroupInstanceJSONObject =
				ruleGroupsInstancesJSONArray.getJSONObject(i);

			long ruleGroupInstanceId = ruleGroupInstanceJSONObject.getLong(
				"ruleGroupInstanceId");

			int priority = ruleGroupInstanceJSONObject.getInt("priority");

			_mdrRuleGroupInstanceService.updateRuleGroupInstance(
				ruleGroupInstanceId, priority);
		}
	}

	private MDRRuleGroupInstanceService _mdrRuleGroupInstanceService;

	@Reference
	private Portal _portal;

}