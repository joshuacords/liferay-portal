/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.relationship.dynamic.data.mapping.internal;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.relationship.Relationship;
import com.liferay.portal.relationship.RelationshipResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class DDMStructureDLFileEntryTypeRelationshipResource
	implements RelationshipResource<DDMStructure> {

	@Override
	public Relationship<DDMStructure> relationship(
		Relationship.Builder<DDMStructure> builder) {

		return builder.modelSupplier(
			structureId -> _ddmStructureLocalService.fetchStructure(structureId)
		).outboundMultiRelationship(
			this::_getStructureFileEntryTypes
		).build();
	}

	private List<DLFileEntryType> _getStructureFileEntryTypes(
		DDMStructure structure) {

		List<DDMStructureLink> ddmStructureLinks =
			_ddmStructureLinkLocalService.getStructureLinks(
				structure.getStructureId());

		Stream<DDMStructureLink> stream = ddmStructureLinks.stream();

		long classNameId = _classNameLocalService.getClassNameId(
			DLFileEntryType.class);

		return stream.filter(
			ddmStructureLink -> ddmStructureLink.getClassNameId() == classNameId
		).map(
			ddmStructureLink -> _dlFileEntryTypeLocalService.fetchFileEntryType(
				ddmStructureLink.getClassPK())
		).collect(
			Collectors.toList()
		);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMStructureLinkLocalService _ddmStructureLinkLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

}