/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.internal.model.listener;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactModelListener extends BaseModelListener<Contact> {

	@Override
	public void onAfterUpdate(Contact contact) throws ModelListenerException {
		try {
			_reindex(contact);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(Contact contact) throws PortalException {
		List<Account> accounts = _accountLocalService.getContactAccounts(
			contact.getContactId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Account account : accounts) {
			_accountLocalService.reindex(account.getAccountId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactModelListener.class);

	@Reference
	private AccountLocalService _accountLocalService;

}