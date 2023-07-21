/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.contributor.sort;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.contributor.ContributorConstants;
import com.liferay.portal.search.contributor.sort.SortFieldNameTranslator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = ContributorConstants.ENTRY_CLASS_NAME_PROPERTY_KEY + "=com.liferay.osb.koroneiki.taproot.model.Contact",
	service = SortFieldNameTranslator.class
)
public class ContactSortFieldNameTranslator implements SortFieldNameTranslator {

	@Override
	public String getSortFieldName(String orderByCol) {
		if (orderByCol.equals("emailAddress")) {
			return Field.getSortableFieldName("emailAddress_String");
		}
		else if (orderByCol.equals("name")) {
			return "firstName";
		}

		return orderByCol;
	}

}