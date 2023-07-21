/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.constants.ContactRoleSystem;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ContactRoleImpl extends ContactRoleBaseImpl {

	public ContactRoleImpl() {
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			ContactRole.class.getName(), getContactRoleId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public boolean isMember() {
		if (isSystem()) {
			String name = getName();

			if (name.equals(ContactRoleSystem.NAME_MEMBER)) {
				return true;
			}
		}

		return false;
	}

}