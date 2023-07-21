/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.relationship.dynamic.data.mapping.internal;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.relationship.Relationship;
import com.liferay.portal.relationship.RelationshipResource;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.dynamic.data.mapping.model.DDMStructure",
	service = RelationshipResource.class
)
public class DDMStructureTemplateRelationshipResource
	implements RelationshipResource<DDMStructure> {

	@Override
	public Relationship<DDMStructure> relationship(
		Relationship.Builder<DDMStructure> builder) {

		return builder.modelSupplier(
			structureId -> _ddmStructureLocalService.fetchStructure(structureId)
		).outboundMultiRelationship(
			this::_getStructureTemplates
		).build();
	}

	private List<DDMTemplate> _getStructureTemplates(DDMStructure structure) {
		long classNameId = _classNameLocalService.getClassNameId(
			DDMStructure.class);

		return _ddmTemplateLocalService.getTemplates(
			structure.getGroupId(), classNameId);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

}