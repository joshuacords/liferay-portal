/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.service.AccountFieldLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountFieldModelListener
	extends BaseAuditModelListener<AccountField> {

	@Override
	public void onAfterCreate(AccountField accountField)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			auditEntryLocalService.addAuditEntry(
				getUserId(), getClassNameId(accountField),
				getClassPK(accountField), 0, 0,
				AuditEntry.Action.UPDATE.toString(), accountField.getName(),
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				accountField.getValue(), getDescription(accountField),
				getServiceContext(accountField));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(AccountField accountField)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			auditEntryLocalService.addAuditEntry(
				getUserId(), getClassNameId(accountField),
				getClassPK(accountField), 0, 0,
				AuditEntry.Action.DELETE.toString(), accountField.getName(),
				StringPool.BLANK, accountField.getValue(), StringPool.BLANK,
				StringPool.BLANK, getDescription(accountField),
				getServiceContext(accountField));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(AccountField accountField)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			AccountField oldAccountField =
				_accountFieldLocalService.getAccountField(
					accountField.getAccountFieldId());

			String oldValue = oldAccountField.getValue();

			if (!Objects.equals(oldValue, accountField.getValue())) {
				auditEntryLocalService.addAuditEntry(
					getUserId(), getClassNameId(accountField),
					getClassPK(accountField), 0, 0,
					AuditEntry.Action.UPDATE.toString(), accountField.getName(),
					StringPool.BLANK, oldValue, StringPool.BLANK,
					accountField.getValue(), getDescription(accountField),
					getServiceContext(accountField));
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	protected long getClassNameId(AccountField accountField) {
		return classNameLocalService.getClassNameId(Account.class);
	}

	@Override
	protected long getClassPK(AccountField accountField) {
		return accountField.getAccountId();
	}

	@Override
	protected ServiceContext getServiceContext(AccountField accountField) {
		return getServiceContext(
			classNameLocalService.getClassNameId(Account.class),
			accountField.getAccountId());
	}

	@Reference
	private AccountFieldLocalService _accountFieldLocalService;

}