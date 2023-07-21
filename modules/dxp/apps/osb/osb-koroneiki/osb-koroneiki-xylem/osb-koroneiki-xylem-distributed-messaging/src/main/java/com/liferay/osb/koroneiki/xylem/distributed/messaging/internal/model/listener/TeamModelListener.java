/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.team.create",
		"remove.topic=koroneiki.team.delete",
		"update.topic=koroneiki.team.update"
	},
	service = ModelListener.class
)
public class TeamModelListener extends BaseXylemModelListener<Team> {

	@Override
	protected Callable<Message> getCallable(Team team) throws Exception {
		Account account = _accountLocalService.getAccount(team.getAccountId());

		team.setAccountKey(account.getAccountKey());

		return () -> messageFactory.create(team);
	}

	@Reference
	private AccountLocalService _accountLocalService;

}