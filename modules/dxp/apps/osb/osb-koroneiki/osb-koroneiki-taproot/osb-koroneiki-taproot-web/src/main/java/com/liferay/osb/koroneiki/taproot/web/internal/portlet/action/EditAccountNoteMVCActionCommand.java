/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/edit_account_note"
	},
	service = MVCActionCommand.class
)
public class EditAccountNoteMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteAccountNote(ActionRequest actionRequest)
		throws PortalException {

		long accountNoteId = ParamUtil.getLong(actionRequest, "accountNoteId");

		_accountNoteLocalService.deleteAccountNote(accountNoteId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteAccountNote(actionRequest);
			}
			else {
				updateAccountNote(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateAccountNote(ActionRequest actionRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		long accountNoteId = ParamUtil.getLong(actionRequest, "accountNoteId");

		int priority = ParamUtil.getInteger(actionRequest, "priority");
		String content = ParamUtil.getString(actionRequest, "content");
		String format = ParamUtil.getString(actionRequest, "format");
		String status = ParamUtil.getString(actionRequest, "status");

		if (accountNoteId <= 0) {
			long accountId = ParamUtil.getLong(actionRequest, "accountId");
			String type = ParamUtil.getString(actionRequest, "type");

			_accountNoteLocalService.addAccountNote(
				user.getUserId(), user.getUuid(), user.getFullName(), accountId,
				type, priority, content, format, status);
		}
		else {
			_accountNoteLocalService.updateAccountNote(
				accountNoteId, user.getUuid(), user.getFullName(), priority,
				content, format, status);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountNoteMVCActionCommand.class);

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

}