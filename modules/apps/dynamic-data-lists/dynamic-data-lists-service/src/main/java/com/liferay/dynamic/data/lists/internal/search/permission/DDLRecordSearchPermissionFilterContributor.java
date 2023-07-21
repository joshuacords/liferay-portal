/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.search.permission;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.portal.search.permission.SearchPermissionFilterContributor;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(immediate = true, service = SearchPermissionFilterContributor.class)
public class DDLRecordSearchPermissionFilterContributor
	implements SearchPermissionFilterContributor {

	@Override
	public Optional<String> getParentEntryClassNameOptional(
		String entryClassName) {

		if (entryClassName.equals(DDLRecord.class.getName())) {
			return Optional.of(DDLRecordSet.class.getName());
		}

		return Optional.empty();
	}

}