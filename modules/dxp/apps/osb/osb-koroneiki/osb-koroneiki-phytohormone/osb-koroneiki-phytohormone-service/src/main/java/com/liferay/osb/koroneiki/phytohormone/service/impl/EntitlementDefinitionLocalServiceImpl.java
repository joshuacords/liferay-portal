/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.impl;

import com.liferay.osb.koroneiki.phytohormone.exception.EntitlementDefinitionDefinitionException;
import com.liferay.osb.koroneiki.phytohormone.exception.EntitlementDefinitionNameException;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementDefinitionLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition",
	service = AopService.class
)
public class EntitlementDefinitionLocalServiceImpl
	extends EntitlementDefinitionLocalServiceBaseImpl {

	public EntitlementDefinition addEntitlementDefinition(
			long userId, long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(classNameId, 0, name, definition);

		long entitlementDefinitionId = counterLocalService.increment();

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.create(entitlementDefinitionId);

		entitlementDefinition.setCompanyId(user.getCompanyId());
		entitlementDefinition.setUserId(userId);
		entitlementDefinition.setEntitlementDefinitionKey(
			ModelKeyGenerator.generate(entitlementDefinitionId));
		entitlementDefinition.setClassNameId(classNameId);
		entitlementDefinition.setName(name);
		entitlementDefinition.setDescription(description);
		entitlementDefinition.setDefinition(definition);
		entitlementDefinition.setStatus(status);

		resourceLocalService.addResources(
			user.getCompanyId(), 0, userId,
			EntitlementDefinition.class.getName(), entitlementDefinitionId,
			false, false, false);

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	@Override
	public EntitlementDefinition deleteEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws PortalException {

		// Entitlements

		entitlementPersistence.removeByEntitlementDefinitionId(
			entitlementDefinition.getEntitlementDefinitionId());

		// Resources

		resourceLocalService.deleteResource(
			entitlementDefinition.getCompanyId(),
			EntitlementDefinition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			entitlementDefinition.getEntitlementDefinitionId());

		return entitlementDefinitionPersistence.remove(entitlementDefinition);
	}

	@Override
	public EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.findByPrimaryKey(
				entitlementDefinitionId);

		return deleteEntitlementDefinition(entitlementDefinition);
	}

	public EntitlementDefinition getEntitlementDefinition(
			String entitlementDefinitionKey)
		throws PortalException {

		return entitlementDefinitionPersistence.findByEntitlementDefinitionKey(
			entitlementDefinitionKey);
	}

	public List<EntitlementDefinition> getEntitlementDefinitions(
		long classNameId, int status) {

		return entitlementDefinitionPersistence.findByC_S(classNameId, status);
	}

	public List<EntitlementDefinition> getEntitlementDefinitions(
		String className, int status) {

		return entitlementDefinitionPersistence.findByC_S(
			classNameLocalService.getClassNameId(className), status);
	}

	public List<EntitlementDefinition> search(
		long classNameId, String name, int start, int end) {

		return entitlementDefinitionPersistence.findByC_LikeN(
			classNameId, name, start, end);
	}

	public int searchCount(long classNameId, String name) {
		return entitlementDefinitionPersistence.countByC_LikeN(
			classNameId, name);
	}

	public EntitlementDefinition updateEntitlementDefinition(
			long entitlementDefinitionId, String description, String definition,
			int status)
		throws PortalException {

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.findByPrimaryKey(
				entitlementDefinitionId);

		validate(
			entitlementDefinition.getClassNameId(), entitlementDefinitionId,
			entitlementDefinition.getName(), definition);

		entitlementDefinition.setDescription(description);
		entitlementDefinition.setDefinition(definition);
		entitlementDefinition.setStatus(status);

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	protected void validate(
			long classNameId, long entitlementDefinitionId, String name,
			String definition)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new EntitlementDefinitionNameException();
		}

		if (Validator.isNull(definition)) {
			throw new EntitlementDefinitionDefinitionException();
		}

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.fetchByC_N(classNameId, name);

		if ((entitlementDefinition != null) &&
			(entitlementDefinition.getEntitlementDefinitionId() !=
				entitlementDefinitionId)) {

			throw new EntitlementDefinitionNameException.MustNotBeDuplicate(
				classNameId, name);
		}
	}

}