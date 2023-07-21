/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.impl;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneActionKeys;
import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneDestinationNames;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.permission.EntitlementDefinitionPermission;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementDefinitionServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=EntitlementDefinition"
	},
	service = AopService.class
)
public class EntitlementDefinitionServiceImpl
	extends EntitlementDefinitionServiceBaseImpl {

	public EntitlementDefinition addEntitlementDefinition(
			long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(),
			PhytohormoneActionKeys.ADD_ENTITLEMENT_DEFINITION);

		return entitlementDefinitionLocalService.addEntitlementDefinition(
			getUserId(), classNameId, name, description, definition, status);
	}

	public EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(), entitlementDefinitionId, ActionKeys.DELETE);

		return entitlementDefinitionLocalService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	public void synchronizeEntitlementDefinition(long entitlementDefinitionId)
		throws Exception {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(), entitlementDefinitionId, ActionKeys.UPDATE);

		Message message = new Message();

		message.put("entitlementDefinitionId", entitlementDefinitionId);

		_messageBus.sendMessage(
			PhytohormoneDestinationNames.SYNCHRONIZE_ENTITLEMENT_DEFINITION,
			message);
	}

	@Reference
	private EntitlementDefinitionPermission _entitlementDefinitionPermission;

	@Reference
	private MessageBus _messageBus;

}