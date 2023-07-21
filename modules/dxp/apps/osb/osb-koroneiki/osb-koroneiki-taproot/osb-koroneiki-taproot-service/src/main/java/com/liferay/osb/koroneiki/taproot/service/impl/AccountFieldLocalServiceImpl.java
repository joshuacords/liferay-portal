/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.service.base.AccountFieldLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.AccountField",
	service = AopService.class
)
public class AccountFieldLocalServiceImpl
	extends AccountFieldLocalServiceBaseImpl {

	public AccountField addAccountField(
			long userId, long accountId, String name, String value)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long accountFieldId = counterLocalService.increment();

		AccountField accountField = accountFieldPersistence.create(
			accountFieldId);

		accountField.setCompanyId(user.getCompanyId());
		accountField.setUserId(userId);
		accountField.setAccountId(accountId);
		accountField.setName(name);
		accountField.setValue(value);

		return accountFieldPersistence.update(accountField);
	}

	public List<String> getAccountFieldNames() {
		return accountFieldFinder.findNames();
	}

	public List<AccountField> getAccountFields(long accountId) {
		return accountFieldPersistence.findByAccountId(accountId);
	}

	public AccountField updateAccountField(long accountFieldId, String value)
		throws PortalException {

		AccountField accountField = accountFieldPersistence.findByPrimaryKey(
			accountFieldId);

		accountField.setValue(value);

		return accountFieldPersistence.update(accountField);
	}

}