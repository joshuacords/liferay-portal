/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.license.model.LicenseKey;

import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public interface CustomerPortalRelease {

	public boolean hasAccountAccessPermission(Account account, Contact contact)
		throws Exception;

	public boolean hasAccountManageLicenseKeysPermission(
			String accountKey, Contact contact)
		throws Exception;

	public boolean isEnabled(
		String accountKey, Set<ProductPurchase> productPurchases,
		Account.Region region);

	public void sendAutoProvisionedWelcomeEmail(Account account)
		throws Exception;

	public void sendAutoProvisionedWelcomeEmail(
			String emailAddress, Account account,
			List<ContactRole> currentContactRoles,
			List<ContactRole> addContactRoles)
		throws Exception;

	public void sendContactAccountActivationKeyEmail(
		Contact contact, Account account, LicenseKey licenseKey);

	public void sendContactAssignedWelcomeEmail(
			Contact contact, Account account,
			List<ContactRole> currentContactRoles, String[] addContactRoleKeys)
		throws Exception;

	public void sendContactVerifiedWelcomeEmail(Contact contact)
		throws Exception;

}