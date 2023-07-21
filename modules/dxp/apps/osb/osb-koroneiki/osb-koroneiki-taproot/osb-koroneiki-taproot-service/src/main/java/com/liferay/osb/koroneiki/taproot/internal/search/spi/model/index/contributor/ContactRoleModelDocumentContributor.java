/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.ContactRole",
	service = ModelDocumentContributor.class
)
public class ContactRoleModelDocumentContributor
	implements ModelDocumentContributor<ContactRole> {

	@Override
	public void contribute(Document document, ContactRole contactRole) {
		document.addKeyword(Field.COMPANY_ID, contactRole.getCompanyId());
		document.addDate(Field.CREATE_DATE, contactRole.getCreateDate());
		document.addText(Field.DESCRIPTION, contactRole.getDescription());
		document.addDate(Field.MODIFIED_DATE, contactRole.getModifiedDate());
		document.addText(Field.NAME, contactRole.getName());
		document.addKeyword(
			Field.TYPE, StringUtil.toLowerCase(contactRole.getType()));
		document.addKeyword(Field.USER_ID, contactRole.getUserId());

		document.addKeyword("contactRoleKey", contactRole.getContactRoleKey());
		document.addKeyword("system", contactRole.isSystem());

		document.addDateSortable(
			Field.CREATE_DATE, contactRole.getCreateDate());
		document.addDateSortable(
			Field.MODIFIED_DATE, contactRole.getModifiedDate());
		document.addTextSortable(Field.NAME, contactRole.getName());
	}

}