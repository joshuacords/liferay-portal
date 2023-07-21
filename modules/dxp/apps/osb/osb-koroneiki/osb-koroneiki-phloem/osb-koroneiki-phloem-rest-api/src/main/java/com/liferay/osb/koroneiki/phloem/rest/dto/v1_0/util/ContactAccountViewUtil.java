/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ContactAccountViewUtil {

	public static ContactAccountView toContactAccountView(
			com.liferay.osb.koroneiki.taproot.model.Account taprootAccount,
			List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				taprootCustomerContactRoles,
			List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				taprootWorkerContactRoles)
		throws Exception {

		return new ContactAccountView() {
			{
				account = AccountUtil.toAccount(taprootAccount);
				customerContactRoles = TransformUtil.transformToArray(
					taprootCustomerContactRoles, ContactRoleUtil::toContactRole,
					ContactRole.class);
				workerContactRoles = TransformUtil.transformToArray(
					taprootWorkerContactRoles, ContactRoleUtil::toContactRole,
					ContactRole.class);
			}
		};
	}

}