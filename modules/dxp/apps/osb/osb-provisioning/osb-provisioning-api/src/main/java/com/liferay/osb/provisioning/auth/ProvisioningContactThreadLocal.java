/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.auth;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author Amos Fong
 */
public class ProvisioningContactThreadLocal {

	public static Contact getContact() {
		return _contactThreadLocal.get();
	}

	public static void setContact(Contact contact) {
		_contactThreadLocal.set(contact);
	}

	private static final ThreadLocal<Contact> _contactThreadLocal =
		new CentralizedThreadLocal<>(
			ProvisioningContactThreadLocal.class + "._contactThreadLocal");

}