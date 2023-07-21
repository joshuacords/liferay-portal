/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.AccountNoteLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.AccountNote",
	service = AopService.class
)
public class AccountNoteLocalServiceImpl
	extends AccountNoteLocalServiceBaseImpl {

	public AccountNote addAccountNote(
			long userId, String creatorUID, String creatorName, long accountId,
			String type, int priority, String content, String format,
			String status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long accountNoteId = counterLocalService.increment();

		AccountNote accountNote = accountNotePersistence.create(accountNoteId);

		accountNote.setCompanyId(user.getCompanyId());
		accountNote.setUserId(userId);
		accountNote.setCreatorUID(creatorUID);
		accountNote.setCreatorName(creatorName);
		accountNote.setAccountNoteKey(
			ModelKeyGenerator.generate(accountNoteId));
		accountNote.setAccountId(accountId);
		accountNote.setType(type);
		accountNote.setPriority(priority);
		accountNote.setContent(content);
		accountNote.setFormat(format);
		accountNote.setStatus(status);

		_accountLocalService.reindex(accountNote.getAccountId());

		return accountNotePersistence.update(accountNote);
	}

	public AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		return accountNotePersistence.findByAccountNoteKey(accountNoteKey);
	}

	public List<AccountNote> getAccountNotes(
		long accountId, int start, int end) {

		return accountNotePersistence.findByAccountId(accountId, start, end);
	}

	public List<AccountNote> getAccountNotes(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end) {

		return accountNotePersistence.findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end);
	}

	public int getAccountNotesCount(long accountId) {
		return accountNotePersistence.countByAccountId(accountId);
	}

	public int getAccountNotesCount(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return accountNotePersistence.countByAI_T_P_S(
			accountId, types, priorities, statuses);
	}

	public AccountNote updateAccountNote(
			long accountNoteId, String modifierUID, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		AccountNote accountNote = accountNotePersistence.findByPrimaryKey(
			accountNoteId);

		if (!content.equals(accountNote.getContent())) {
			accountNote.setModifiedDate(new Date());
			accountNote.setModifierUID(modifierUID);
			accountNote.setModifierName(modifierName);
		}
		else {
			accountNote.setModifiedDate(accountNote.getModifiedDate());
		}

		accountNote.setPriority(priority);
		accountNote.setContent(content);
		accountNote.setFormat(format);
		accountNote.setStatus(status);

		_accountLocalService.reindex(accountNote.getAccountId());

		return accountNotePersistence.update(accountNote);
	}

	@Reference
	private AccountLocalService _accountLocalService;

}