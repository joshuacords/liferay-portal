/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.util.DDMStructurePermissionSupport;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.data.engine.rest.internal.model.InternalDataDefinition",
	service = {
		DDMStructurePermissionSupport.class,
		InternalDataDefinitionDDMStructurePermissionSupport.class
	}
)
public class InternalDataDefinitionDDMStructurePermissionSupport
	implements DDMStructurePermissionSupport {

	@Override
	public String getResourceName() {
		return "com.liferay.data.engine";
	}

}