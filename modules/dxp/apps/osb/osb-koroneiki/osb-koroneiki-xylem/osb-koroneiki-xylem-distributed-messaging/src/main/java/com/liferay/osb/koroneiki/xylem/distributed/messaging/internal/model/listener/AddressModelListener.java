/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AddressModelListener extends BaseXylemModelListener<Address> {

	@Override
	protected Callable<Message> getCallable(Address address) throws Exception {
		if (address.getClassNameId() == _classNameLocalService.getClassNameId(
				Account.class)) {

			Account account = _accountLocalService.getAccount(
				address.getClassPK());

			return () -> messageFactory.create(account);
		}

		return null;
	}

	@Override
	protected String getCreateTopic(Address address) {
		return _getTopic(address);
	}

	@Override
	protected String getPrimaryKey(Address address) {
		return String.valueOf(address.getClassPK());
	}

	@Override
	protected String getRemoveTopic(Address address) {
		return _getTopic(address);
	}

	@Override
	protected String getUpdateTopic(Address address) {
		return _getTopic(address);
	}

	private String _getTopic(Address address) {
		if (address.getClassNameId() == _classNameLocalService.getClassNameId(
				Account.class)) {

			return "koroneiki.account.update";
		}

		return null;
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

}