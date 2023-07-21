/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
	service = MVCRenderCommand.class
)
public class EditAccountNoteMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long accountNoteId = ParamUtil.getLong(
				renderRequest, "accountNoteId");

			AccountNote accountNote = null;

			if (accountNoteId > 0) {
				accountNote = _accountNoteLocalService.getAccountNote(
					accountNoteId);

				renderRequest.setAttribute(
					TaprootWebKeys.ACCOUNT_NOTE, accountNote);
			}

			long accountId = BeanParamUtil.getLong(
				accountNote, renderRequest, "accountId");

			if (accountId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.ACCOUNT,
					_accountLocalService.getAccount(accountId));
			}

			return "/accounts_admin/edit_account_note.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			throw new PortletException(exception);
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

}