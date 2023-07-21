/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
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
				emailAddressVerified = contact.getEmailAddressVerified();
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
				teams = TransformUtil.transformToArray(
					contact.getTeams(), TeamUtil::toTeam, Team.class);
				uuid = contact.getUuid();
			}
		};
	}

}