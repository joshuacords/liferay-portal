/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.service.impl;

import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.data.engine.service.base.DEDataDefinitionFieldLinkLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.data.engine.model.DEDataDefinitionFieldLink",
	service = AopService.class
)
public class DEDataDefinitionFieldLinkLocalServiceImpl
	extends DEDataDefinitionFieldLinkLocalServiceBaseImpl {

	@Override
	public DEDataDefinitionFieldLink addDEDataDefinitionFieldLink(
		long groupId, long classNameId, long classPK, long ddmStructureId,
		String fieldName) {

		DEDataDefinitionFieldLink deDataDefinitionFieldLink =
			deDataDefinitionFieldLinkPersistence.create(
				counterLocalService.increment());

		deDataDefinitionFieldLink.setGroupId(groupId);
		deDataDefinitionFieldLink.setClassNameId(classNameId);
		deDataDefinitionFieldLink.setClassPK(classPK);
		deDataDefinitionFieldLink.setDdmStructureId(ddmStructureId);
		deDataDefinitionFieldLink.setFieldName(fieldName);

		return deDataDefinitionFieldLinkPersistence.update(
			deDataDefinitionFieldLink);
	}

}