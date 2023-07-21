/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.impl;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.phytohormone.model.Entitlement",
	service = AopService.class
)
public class EntitlementLocalServiceImpl
	extends EntitlementLocalServiceBaseImpl {

	public Entitlement addEntitlement(
			long userId, long entitlementDefinitionId, long classNameId,
			long classPK)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.findByPrimaryKey(
				entitlementDefinitionId);

		long entitlementId = counterLocalService.increment();

		Entitlement entitlement = entitlementPersistence.create(entitlementId);

		entitlement.setCompanyId(user.getCompanyId());
		entitlement.setUserId(user.getUserId());
		entitlement.setEntitlementDefinitionId(entitlementDefinitionId);
		entitlement.setClassNameId(classNameId);
		entitlement.setClassPK(classPK);
		entitlement.setName(entitlementDefinition.getName());

		return entitlementPersistence.update(entitlement);
	}

	public void deleteEntitlements(long classNameId, long classPK) {
		entitlementPersistence.removeByC_C(classNameId, classPK);
	}

	public List<Entitlement> getEntitlements(
		long classNameId, long classPK, int start, int end) {

		return entitlementPersistence.findByC_C(
			classNameId, classPK, start, end);
	}

	public List<Entitlement> getEntitlements(
		String className, long classPK, int start, int end) {

		return entitlementPersistence.findByC_C(
			classNameLocalService.getClassNameId(className), classPK, start,
			end);
	}

}