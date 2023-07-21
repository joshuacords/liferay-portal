/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.identity.management.provider;

import com.liferay.osb.koroneiki.taproot.model.Contact;

/**
 * @author Amos Fong
 */
public interface ContactIdentityProvider {

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception;

	public Contact fetchContactByUuid(String uuid) throws Exception;

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception;

	public Contact getContactByUuid(String uuid) throws Exception;

}