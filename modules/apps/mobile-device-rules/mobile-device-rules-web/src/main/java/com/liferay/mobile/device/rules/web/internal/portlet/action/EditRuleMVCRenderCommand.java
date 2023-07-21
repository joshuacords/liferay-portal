/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.portlet.action;

import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.mobile.device.rules.service.MDRRuleGroupService;
import com.liferay.mobile.device.rules.service.MDRRuleService;
import com.liferay.mobile.device.rules.web.internal.constants.MDRWebKeys;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
		"mvc.command.name=/mobile_device_rules/edit_rule"
	},
	service = MVCRenderCommand.class
)
public class EditRuleMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		try {
			long ruleId = ParamUtil.getLong(renderRequest, "ruleId");

			MDRRule rule = _mdrRuleService.fetchRule(ruleId);

			renderRequest.setAttribute(
				MDRWebKeys.MOBILE_DEVICE_RULES_RULE, rule);

			String type = BeanPropertiesUtil.getString(rule, "type");

			renderRequest.setAttribute(
				MDRWebKeys.MOBILE_DEVICE_RULES_RULE_TYPE, type);

			renderRequest.setAttribute(
				MDRWebKeys.MOBILE_DEVICE_RULES_RULE_EDITOR_JSP,
				ActionUtil.getRuleEditorJSP(type));

			long ruleGroupId = BeanParamUtil.getLong(
				rule, renderRequest, "ruleGroupId");

			renderRequest.setAttribute(
				MDRWebKeys.MOBILE_DEVICE_RULES_RULE_GROUP,
				_mdrRuleGroupService.getRuleGroup(ruleGroupId));

			return "/edit_rule.jsp";
		}
		catch (PortalException portalException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}

			return "/error.jsp";
		}
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupService(
		MDRRuleGroupService mdrRuleGroupService) {

		_mdrRuleGroupService = mdrRuleGroupService;
	}

	@Reference(unbind = "-")
	protected void setMDRRuleService(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditRuleMVCRenderCommand.class);

	private MDRRuleGroupService _mdrRuleGroupService;
	private MDRRuleService _mdrRuleService;

}