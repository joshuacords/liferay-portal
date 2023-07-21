/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.model.listener;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class EntitlementModelListener extends BaseModelListener<Entitlement> {

	@Override
	public void onAfterCreate(Entitlement entitlement)
		throws ModelListenerException {

		try {
			_reindex(entitlement);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterRemove(Entitlement entitlement)
		throws ModelListenerException {

		try {
			_reindex(entitlement);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(Entitlement entitlement)
		throws ModelListenerException {

		try {
			_reindex(entitlement);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(Entitlement entitlement) throws PortalException {
		if (entitlement.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			_accountLocalService.reindex(entitlement.getClassPK());

			List<Team> teams = _teamLocalService.getAccountTeams(
				entitlement.getClassPK(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Team team : teams) {
				_teamLocalService.reindex(team.getTeamId());
			}
		}
		else if (entitlement.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			_contactLocalService.reindex(entitlement.getClassPK());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EntitlementModelListener.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}