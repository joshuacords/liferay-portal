/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.internal.security.permission.support;

import com.liferay.dynamic.data.mapping.util.DDMStructurePermissionSupport;
import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Inácio Nery
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
	service = {
		DDMStructurePermissionSupport.class, DDMTemplatePermissionSupport.class
	}
)
public class KaleoFormsDDMPermissionSupport
	implements DDMStructurePermissionSupport, DDMTemplatePermissionSupport {

	@Override
	public String getResourceName() {
		return KaleoFormsConstants.RESOURCE_NAME;
	}

	@Override
	public String getResourceName(long classNameId) {
		return KaleoFormsConstants.RESOURCE_NAME;
	}

}