/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the TeamAccountRole service. Represents a row in the &quot;Koroneiki_TeamAccountRole&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleImpl"
)
@ProviderType
public interface TeamAccountRole extends PersistedModel, TeamAccountRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TeamAccountRole, Long> TEAM_ID_ACCESSOR =
		new Accessor<TeamAccountRole, Long>() {

			@Override
			public Long get(TeamAccountRole teamAccountRole) {
				return teamAccountRole.getTeamId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TeamAccountRole> getTypeClass() {
				return TeamAccountRole.class;
			}

		};
	public static final Accessor<TeamAccountRole, Long> ACCOUNT_ID_ACCESSOR =
		new Accessor<TeamAccountRole, Long>() {

			@Override
			public Long get(TeamAccountRole teamAccountRole) {
				return teamAccountRole.getAccountId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TeamAccountRole> getTypeClass() {
				return TeamAccountRole.class;
			}

		};
	public static final Accessor<TeamAccountRole, Long> TEAM_ROLE_ID_ACCESSOR =
		new Accessor<TeamAccountRole, Long>() {

			@Override
			public Long get(TeamAccountRole teamAccountRole) {
				return teamAccountRole.getTeamRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TeamAccountRole> getTypeClass() {
				return TeamAccountRole.class;
			}

		};

	public Account getAccount()
		throws com.liferay.portal.kernel.exception.PortalException;

	public Team getTeam()
		throws com.liferay.portal.kernel.exception.PortalException;

	public TeamRole getTeamRole()
		throws com.liferay.portal.kernel.exception.PortalException;

}