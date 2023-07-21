/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v2_0_2;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;

/**
 * @author Lino Alves
 */
public class UpgradeDDMFormInstanceStructureResourceAction
	extends UpgradeProcess {

	public UpgradeDDMFormInstanceStructureResourceAction(
		ResourceActions resourceActions) {

		_resourceActions = resourceActions;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"delete from ResourcePermission where name = ?");
			PreparedStatement ps1 = connection.prepareStatement(
				"delete from ResourceAction where name = ?")) {

			String compositeModelName = _resourceActions.getCompositeModelName(
				DDMFormInstance.class.getName(), DDMStructure.class.getName());

			ps.setString(1, compositeModelName);

			ps.executeUpdate();

			ps1.setString(1, compositeModelName);

			ps1.executeUpdate();
		}
	}

	private final ResourceActions _resourceActions;

}