/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.security.permission;

import com.liferay.dynamic.data.lists.constants.DDLConstants;
import com.liferay.dynamic.data.mapping.util.DDMStructurePermissionSupport;
import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.dynamic.data.lists.model.DDLRecordSet",
	service = {
		DDMStructurePermissionSupport.class, DDMTemplatePermissionSupport.class
	}
)
public class DDLDDMPermissionSupport
	implements DDMStructurePermissionSupport, DDMTemplatePermissionSupport {

	@Override
	public String getResourceName() {
		return DDLConstants.RESOURCE_NAME;
	}

	@Override
	public String getResourceName(long classNameId) {
		return DDLConstants.RESOURCE_NAME;
	}

}