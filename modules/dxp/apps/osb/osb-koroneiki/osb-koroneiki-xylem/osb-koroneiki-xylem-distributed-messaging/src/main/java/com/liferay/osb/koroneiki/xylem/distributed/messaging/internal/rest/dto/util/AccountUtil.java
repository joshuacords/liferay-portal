/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Kyle Bischof
 */
public class AccountUtil {

	public static Account toAccount(
			com.liferay.osb.koroneiki.taproot.model.Account account)
		throws Exception {

		return new Account() {
			{
				assignedTeams = TransformUtil.transformToArray(
					account.getAssignedTeams(), TeamUtil::toTeam, Team.class);
				code = account.getCode();
				contactEmailAddress = account.getContactEmailAddress();
				dataRegion = DataRegion.create(account.getDataRegion());
				dateCreated = account.getCreateDate();
				dateModified = account.getModifiedDate();
				description = account.getDescription();
				entitlements = TransformUtil.transformToArray(
					account.getEntitlements(), EntitlementUtil::toEntitlement,
					Entitlement.class);
				externalLinks = TransformUtil.transformToArray(
					account.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				faxNumber = account.getFaxNumber();
				internal = account.getInternal();
				key = account.getAccountKey();
				language = Language.create(account.getLanguage());
				logoId = account.getLogoId();
				name = account.getName();
				phoneNumber = account.getPhoneNumber();
				postalAddresses = TransformUtil.transformToArray(
					account.getAddresses(),
					address -> PostalAddressUtil.toPostalAddress(address),
					PostalAddress.class);
				profileEmailAddress = account.getProfileEmailAddress();
				properties = account.getAccountFieldsMap();
				region = Region.create(account.getRegion());
				status = Status.create(account.getStatus());
				tier = Tier.create(account.getTier());
				website = account.getWebsite();
			}
		};
	}

}