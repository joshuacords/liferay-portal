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

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
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

		_updateAccountModifiedDate(contactTeamRole);

		_reindex(contactTeamRole);
	}

	@Override
	public void onBeforeRemove(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		_updateAccountModifiedDate(contactTeamRole);

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

	private void _updateAccountModifiedDate(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		try {
			Team team = contactTeamRole.getTeam();

			_accountLocalService.updateAccount(team.getAccount());
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