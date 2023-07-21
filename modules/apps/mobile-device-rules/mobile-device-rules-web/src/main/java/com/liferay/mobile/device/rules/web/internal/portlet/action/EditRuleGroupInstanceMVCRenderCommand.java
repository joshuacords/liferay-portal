/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.portlet.action;

import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
import com.liferay.mobile.device.rules.web.internal.constants.MDRWebKeys;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
		"mvc.command.name=/mobile_device_rules/edit_rule_group_instance"
	},
	service = MVCRenderCommand.class
)
public class EditRuleGroupInstanceMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		long ruleGroupInstanceId = ParamUtil.getLong(
			renderRequest, "ruleGroupInstanceId");

		MDRRuleGroupInstance ruleGroupInstance =
			_mdrRuleGroupInstanceLocalService.fetchRuleGroupInstance(
				ruleGroupInstanceId);

		renderRequest.setAttribute(
			MDRWebKeys.MOBILE_DEVICE_RULES_RULE_INSTANCE, ruleGroupInstance);

		long ruleGroupId = BeanParamUtil.getLong(
			ruleGroupInstance, renderRequest, "ruleGroupId");

		MDRRuleGroup ruleGroup = _mdrRuleGroupLocalService.fetchRuleGroup(
			ruleGroupId);

		renderRequest.setAttribute(
			MDRWebKeys.MOBILE_DEVICE_RULES_RULE_GROUP, ruleGroup);

		return "/edit_rule_group_instance_priorities.jsp";
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupInstanceLocalService(
		MDRRuleGroupInstanceLocalService mdrRuleGroupInstanceLocalService) {

		_mdrRuleGroupInstanceLocalService = mdrRuleGroupInstanceLocalService;
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupLocalService(
		MDRRuleGroupLocalService mdrRuleGroupLocalService) {

		_mdrRuleGroupLocalService = mdrRuleGroupLocalService;
	}

	private MDRRuleGroupInstanceLocalService _mdrRuleGroupInstanceLocalService;
	private MDRRuleGroupLocalService _mdrRuleGroupLocalService;

}