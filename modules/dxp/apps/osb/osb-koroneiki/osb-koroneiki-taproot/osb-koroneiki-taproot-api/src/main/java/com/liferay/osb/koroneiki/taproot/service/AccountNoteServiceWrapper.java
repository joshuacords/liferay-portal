/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountNoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteService
 * @generated
 */
public class AccountNoteServiceWrapper
	implements AccountNoteService, ServiceWrapper<AccountNoteService> {

	public AccountNoteServiceWrapper(AccountNoteService accountNoteService) {
		_accountNoteService = accountNoteService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote addAccountNote(
			String creatorUID, String creatorName, long accountId, String type,
			int priority, String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.addAccountNote(
			creatorUID, creatorName, accountId, type, priority, content, format,
			status);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			deleteAccountNote(String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.deleteAccountNote(accountNoteKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote getAccountNote(
			String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNote(accountNoteKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
			getAccountNotes(
				long accountId, String[] types, int[] priorities,
				String[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	@Override
	public int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountNoteService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			updateAccountNote(
				long accountNoteId, String modifierUID, String modifierName,
				int priority, String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.updateAccountNote(
			accountNoteId, modifierUID, modifierName, priority, content, format,
			status);
	}

	@Override
	public AccountNoteService getWrappedService() {
		return _accountNoteService;
	}

	@Override
	public void setWrappedService(AccountNoteService accountNoteService) {
		_accountNoteService = accountNoteService;
	}

	private AccountNoteService _accountNoteService;

}