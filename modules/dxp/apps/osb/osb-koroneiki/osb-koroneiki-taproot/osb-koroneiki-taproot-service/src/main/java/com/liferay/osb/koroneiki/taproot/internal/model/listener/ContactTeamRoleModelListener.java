/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.model.listener;

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactTeamRoleModelListener
	extends BaseModelListener<ContactTeamRole> {

	@Override
	public void onAfterCreate(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		_reindex(contactTeamRole);
	}

	@Override
	public void onBeforeRemove(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		_reindex(contactTeamRole);
	}

	private void _reindex(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		try {
			List<TeamAccountRole> teamAccountRoles =
				_teamAccountRoleLocalService.getTeamAccountRoles(
					contactTeamRole.getTeamId());

			for (TeamAccountRole teamAccountRole : teamAccountRoles) {
				_accountLocalService.reindex(teamAccountRole.getAccountId());
			}

			_contactLocalService.reindex(contactTeamRole.getContactId());

			_teamLocalService.reindex(contactTeamRole.getTeamId());
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}