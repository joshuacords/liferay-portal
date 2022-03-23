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