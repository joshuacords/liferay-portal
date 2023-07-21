/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.portlet.action;

import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupService;
import com.liferay.mobile.device.rules.web.internal.constants.MDRWebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MDRPortletKeys.MOBILE_DEVICE_RULES,
		"mvc.command.name=/mobile_device_rules/edit_rule_group"
	},
	service = MVCRenderCommand.class
)
public class EditRuleGroupMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		try {
			long ruleGroupId = ParamUtil.getLong(renderRequest, "ruleGroupId");

			MDRRuleGroup ruleGroup = _mdrRuleGroupService.fetchRuleGroup(
				ruleGroupId);

			renderRequest.setAttribute(
				MDRWebKeys.MOBILE_DEVICE_RULES_RULE_GROUP, ruleGroup);

			return "/edit_rule_group.jsp";
		}
		catch (PortalException portalException) {
			SessionErrors.add(renderRequest, portalException.getClass());

			return "/error.jsp";
		}
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupService(
		MDRRuleGroupService mdrRuleGroupService) {

		_mdrRuleGroupService = mdrRuleGroupService;
	}

	private MDRRuleGroupService _mdrRuleGroupService;

}