/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class EntitlementDefinitionModelListener
	extends BaseAuditModelListener<EntitlementDefinition> {

	@Override
	protected EntitlementDefinition getModel(long classPK)
		throws PortalException {

		return _entitlementDefinitionLocalService.getEntitlementDefinition(
			classPK);
	}

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}