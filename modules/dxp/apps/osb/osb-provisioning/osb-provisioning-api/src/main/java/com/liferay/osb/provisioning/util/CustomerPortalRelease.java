/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;

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

	public void sendContactAssignedWelcomeEmail(
			Contact contact, Account account,
			List<ContactRole> currentContactRoles, String[] addContactRoleKeys)
		throws Exception;

	public void sendContactVerifiedWelcomeEmail(Contact contact)
		throws Exception;

}