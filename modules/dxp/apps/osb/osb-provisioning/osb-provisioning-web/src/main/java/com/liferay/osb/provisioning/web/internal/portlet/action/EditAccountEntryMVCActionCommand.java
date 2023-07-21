/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/edit_account_entry"
	},
	service = MVCActionCommand.class
)
public class EditAccountEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = null;

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(ProvisioningActionKeys.UPDATE_INSTRUCTIONS)) {
				updateInstructions(actionRequest);
			}
			else if (cmd.equals(ProvisioningActionKeys.UPDATE_LANGUAGE_ID)) {
				updateLanguageId(actionRequest);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(actionResponse);

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			if (Validator.isNotNull(exception.getMessage())) {
				jsonObject = JSONUtil.put(
					"errorMessage", exception.getMessage());
			}
			else {
				jsonObject = JSONUtil.put(
					"errorMessage",
					LanguageUtil.get(
						themeDisplay.getRequest(),
						"an-unexpected-error-occurred"));
			}
		}

		jsonObject = JSONUtil.put(
			"successMessage",
			LanguageUtil.get(
				themeDisplay.getRequest(),
				"your-request-processed-successfully"));

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected void updateInstructions(ActionRequest actionRequest)
		throws Exception {

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		String instructions = ParamUtil.getString(
			actionRequest, "instructions");

		_accountEntryWebService.updateInstructions(accountKey, instructions);
	}

	protected void updateLanguageId(ActionRequest actionRequest)
		throws Exception {

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		String languageId = ParamUtil.getString(actionRequest, "languageId");

		_accountEntryWebService.updateLanguageId(accountKey, languageId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountEntryMVCActionCommand.class);

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private Portal _portal;

}