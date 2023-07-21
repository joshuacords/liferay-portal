/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.invitation.invite.members.internal.upgrade;

import com.liferay.invitation.invite.members.internal.upgrade.v1_0_0.UpgradeNamespace;
import com.liferay.invitation.invite.members.internal.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.invitation.invite.members.internal.upgrade.v2_0_0.util.MemberRequestTable;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class InviteMembersServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.1", "1.0.1", new UpgradeNamespace(), new UpgradePortletId());

		// See LPS-65946

		registry.register(
			"1.0.0", "1.0.1", new UpgradeNamespace(), new UpgradePortletId());

		registry.register(
			"1.0.1", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {MemberRequestTable.class}));
	}

}