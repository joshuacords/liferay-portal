/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
 */
public class EntitlementDefinitionUtil {

	public static EntitlementDefinition toEntitlementDefinition(
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return new EntitlementDefinition() {
			{
				dateCreated = entitlementDefinition.getCreateDate();
				dateModified = entitlementDefinition.getModifiedDate();
				definition = entitlementDefinition.getDefinition();
				description = entitlementDefinition.getDescription();
				externalLinks = TransformUtil.transformToArray(
					entitlementDefinition.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				key = entitlementDefinition.getEntitlementDefinitionKey();
				name = entitlementDefinition.getName();
				status = Status.create(entitlementDefinition.getStatusLabel());
			}
		};
	}

}