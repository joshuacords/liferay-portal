/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.portlet.action;

import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.exception.NoSuchActionException;
import com.liferay.mobile.device.rules.exception.NoSuchRuleGroupException;
import com.liferay.mobile.device.rules.rule.RuleGroupProcessorUtil;
import com.liferay.mobile.device.rules.rule.RuleHandler;
import com.liferay.mobile.device.rules.rule.UnknownRuleHandlerException;
import com.liferay.mobile.device.rules.service.MDRRuleService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Locale;
import java.util.Map;

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
		"mvc.command.name=/mobile_device_rules/edit_rule"
	},
	service = MVCActionCommand.class
)
public class EditRuleMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteRule(ActionRequest request) throws Exception {
		long ruleId = ParamUtil.getLong(request, "ruleId");

		_mdrRuleService.deleteRule(ruleId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateRule(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteRule(actionRequest);
			}
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchActionException ||
				exception instanceof NoSuchRuleGroupException ||
				exception instanceof PrincipalException ||
				exception instanceof UnknownRuleHandlerException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw exception;
			}
		}
	}

	@Reference(unbind = "-")
	protected void setMDRRuleService(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	protected void updateRule(ActionRequest actionRequest) throws Exception {
		long ruleId = ParamUtil.getLong(actionRequest, "ruleId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");

		String type = ParamUtil.getString(actionRequest, "type");

		RuleHandler ruleHandler = RuleGroupProcessorUtil.getRuleHandler(type);

		if (ruleHandler == null) {
			throw new UnknownRuleHandlerException(type);
		}

		UnicodeProperties typeSettingsProperties =
			ActionUtil.getTypeSettingsProperties(
				actionRequest, ruleHandler.getPropertyNames());

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (ruleId <= 0) {
			long ruleGroupId = ParamUtil.getLong(actionRequest, "ruleGroupId");

			_mdrRuleService.addRule(
				ruleGroupId, nameMap, descriptionMap, type,
				typeSettingsProperties, serviceContext);
		}
		else {
			_mdrRuleService.updateRule(
				ruleId, nameMap, descriptionMap, type, typeSettingsProperties,
				serviceContext);
		}
	}

	private MDRRuleService _mdrRuleService;

}