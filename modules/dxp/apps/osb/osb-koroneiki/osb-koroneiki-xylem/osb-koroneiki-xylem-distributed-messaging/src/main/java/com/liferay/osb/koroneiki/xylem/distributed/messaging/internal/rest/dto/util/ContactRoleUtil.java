/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Kyle Bischof
 */
public class ContactRoleUtil {

	public static ContactRole toContactRole(
			com.liferay.osb.koroneiki.taproot.model.ContactRole contactRole)
		throws Exception {

		return new ContactRole() {
			{
				dateCreated = contactRole.getCreateDate();
				dateModified = contactRole.getModifiedDate();
				description = contactRole.getDescription();
				externalLinks = TransformUtil.transformToArray(
					contactRole.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				key = contactRole.getContactRoleKey();
				name = contactRole.getName();
				system = contactRole.getSystem();
				type = Type.create(contactRole.getType());
			}
		};
	}

}