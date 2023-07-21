/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.service.base.AccountNoteServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=AccountNote"
	},
	service = AopService.class
)
public class AccountNoteServiceImpl extends AccountNoteServiceBaseImpl {

	public AccountNote addAccountNote(
			String creatorUID, String creatorName, long accountId, String type,
			int priority, String content, String format, String status)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.UPDATE);

		return accountNoteLocalService.addAccountNote(
			getUserId(), creatorUID, creatorName, accountId, type, priority,
			content, format, status);
	}

	public AccountNote deleteAccountNote(String accountNoteKey)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteKey);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.VIEW);

		return accountNoteLocalService.deleteAccountNote(
			accountNote.getAccountNoteId());
	}

	public AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteKey);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.VIEW);

		return accountNote;
	}

	public List<AccountNote> getAccountNotes(
			long accountId, String[] types, int[] priorities, String[] statuses,
			int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return accountNoteLocalService.getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	public int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return accountNoteLocalService.getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	public AccountNote updateAccountNote(
			long accountNoteId, String modifierUID, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteId);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.UPDATE);

		return accountNoteLocalService.updateAccountNote(
			accountNoteId, modifierUID, modifierName, priority, content, format,
			status);
	}

	@Reference
	private AccountPermission _accountPermission;

}