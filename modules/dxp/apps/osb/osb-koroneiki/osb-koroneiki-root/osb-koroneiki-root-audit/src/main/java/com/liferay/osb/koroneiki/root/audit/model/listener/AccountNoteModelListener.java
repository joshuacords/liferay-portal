/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountNoteModelListener
	extends BaseAuditModelListener<AccountNote> {

	@Override
	protected long getClassNameId(AccountNote accountNote) {
		return classNameLocalService.getClassNameId(Account.class);
	}

	@Override
	protected long getClassPK(AccountNote accountNote) {
		return accountNote.getAccountId();
	}

	@Override
	protected AccountNote getModel(long classPK) throws PortalException {
		return _accountNoteLocalService.getAccountNote(classPK);
	}

	@Override
	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (field.equals("content")) {
			return false;
		}

		return super.isSkipFieldUpdate(field, oldValue, newValue);
	}

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

}