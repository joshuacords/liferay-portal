/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.exception.AccountCodeException;
import com.liferay.osb.koroneiki.taproot.exception.AccountNameException;
import com.liferay.osb.koroneiki.taproot.exception.AccountParentException;
import com.liferay.osb.koroneiki.taproot.exception.RequiredAccountException;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.service.AccountFieldLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/edit_account"
	},
	service = MVCActionCommand.class
)
public class EditAccountMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteAccount(ActionRequest actionRequest)
		throws PortalException {

		long accountId = ParamUtil.getLong(actionRequest, "accountId");

		_accountService.deleteAccount(accountId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteAccount(actionRequest);
			}
			else {
				updateAccount(actionRequest, actionResponse);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof RequiredAccountException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				sendRedirect(actionRequest, actionResponse);
			}
			else if (exception instanceof AccountCodeException ||
					 exception instanceof AccountNameException ||
					 exception instanceof AccountParentException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/accounts_admin/edit_account");
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateAccount(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long accountId = ParamUtil.getLong(actionRequest, "accountId");

		long parentAccountId = ParamUtil.getLong(
			actionRequest, "parentAccountId");
		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		String description = ParamUtil.getString(actionRequest, "description");
		String contactEmailAddress = ParamUtil.getString(
			actionRequest, "contactEmailAddress");
		String profileEmailAddress = ParamUtil.getString(
			actionRequest, "profileEmailAddress");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
		String faxNumber = ParamUtil.getString(actionRequest, "faxNumber");
		String website = ParamUtil.getString(actionRequest, "website");
		String tier = ParamUtil.getString(actionRequest, "tier");
		String region = ParamUtil.getString(actionRequest, "region");
		String dataRegion = ParamUtil.getString(actionRequest, "dataRegion");
		String language = ParamUtil.getString(actionRequest, "language");
		boolean internal = ParamUtil.getBoolean(actionRequest, "internal");
		String status = ParamUtil.getString(actionRequest, "status");

		List<AccountField> accountFields = new ArrayList<>();

		int[] accountFieldIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "accountFieldIndexes"), 0);

		for (int accountFieldIndex : accountFieldIndexes) {
			String accountFieldName = ParamUtil.getString(
				actionRequest, "accountFieldName_" + accountFieldIndex);
			String accountFieldValue = ParamUtil.getString(
				actionRequest, "accountFieldValue_" + accountFieldIndex);

			if (Validator.isNull(accountFieldName) ||
				Validator.isNull(accountFieldValue)) {

				continue;
			}

			AccountField accountField =
				_accountFieldLocalService.createAccountField(0);

			accountField.setName(accountFieldName);
			accountField.setValue(accountFieldValue);

			accountFields.add(accountField);
		}

		Account account = null;

		if (accountId <= 0) {
			account = _accountService.addAccount(
				parentAccountId, name, code, description, 0,
				contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, tier, region, dataRegion, language,
				internal, status, accountFields);
		}
		else {
			account = _accountService.updateAccount(
				accountId, parentAccountId, name, code, description, 0,
				contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, tier, region, dataRegion, language,
				internal, status, accountFields);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL renderURL = liferayPortletResponse.createRenderURL();

		renderURL.setParameter(
			"mvcRenderCommandName", "/accounts_admin/edit_account");
		renderURL.setParameter("redirect", redirect);
		renderURL.setParameter(
			"accountId", String.valueOf(account.getAccountId()));

		actionRequest.setAttribute(WebKeys.REDIRECT, renderURL.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountMVCActionCommand.class);

	@Reference
	private AccountFieldLocalService _accountFieldLocalService;

	@Reference
	private AccountService _accountService;

	@Reference
	private Portal _portal;

}