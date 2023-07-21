/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Entitlement;

/**
 * @author Amos Fong
 */
public class EntitlementUtil {

	public static Entitlement toEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement)
		throws Exception {

		return new Entitlement() {
			{
				entitlementDefinitionKey =
					entitlement.getEntitlementDefinitionKey();
				name = entitlement.getName();
			}
		};
	}

}