/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Kyle Bischof
 */
public class ContactUtil {

	public static Contact toContact(
			com.liferay.osb.koroneiki.taproot.model.Contact contact)
		throws Exception {

		return new Contact() {
			{
				dateCreated = contact.getCreateDate();
				dateModified = contact.getModifiedDate();
				emailAddress = contact.getEmailAddress();
				entitlements = TransformUtil.transformToArray(
					contact.getEntitlements(), EntitlementUtil::toEntitlement,
					Entitlement.class);
				externalLinks = TransformUtil.transformToArray(
					contact.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				firstName = contact.getFirstName();
				key = contact.getContactKey();
				languageId = contact.getLanguageId();
				lastName = contact.getLastName();
				middleName = contact.getMiddleName();
				uuid = contact.getUuid();
			}
		};
	}

}