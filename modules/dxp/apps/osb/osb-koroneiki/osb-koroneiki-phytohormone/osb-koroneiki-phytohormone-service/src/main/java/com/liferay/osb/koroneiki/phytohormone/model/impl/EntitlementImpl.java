/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class EntitlementImpl extends EntitlementBaseImpl {

	public EntitlementImpl() {
	}

	public EntitlementDefinition getEntitlementDefinition()
		throws PortalException {

		return EntitlementDefinitionLocalServiceUtil.getEntitlementDefinition(
			getEntitlementDefinitionId());
	}

	public String getEntitlementDefinitionKey() throws PortalException {
		if (_entitlementDefinitionKey != null) {
			return _entitlementDefinitionKey;
		}

		EntitlementDefinition entitlementDefinition =
			getEntitlementDefinition();

		return entitlementDefinition.getEntitlementDefinitionKey();
	}

	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		_entitlementDefinitionKey = entitlementDefinitionKey;
	}

	private String _entitlementDefinitionKey;

}