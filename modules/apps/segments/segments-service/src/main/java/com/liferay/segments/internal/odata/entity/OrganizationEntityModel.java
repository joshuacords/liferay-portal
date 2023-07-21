/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.odata.entity;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.ComplexEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IdEntityField;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.List;
import java.util.Map;

/**
 * Provides the entity data model from the Organization.
 *
 * @author David Arques
 */
public class OrganizationEntityModel implements EntityModel {

	public static final String NAME = "Organization";

	public OrganizationEntityModel(List<EntityField> customEntityFields) {
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
			new ComplexEntityField("customField", customEntityFields),
			new DateTimeEntityField(
				"dateModified",
				locale -> Field.getSortableFieldName(Field.MODIFIED_DATE),
				locale -> Field.MODIFIED_DATE),
			new IdEntityField(
				"assetTagIds", locale -> Field.ASSET_TAG_IDS, String::valueOf),
			new IdEntityField(
				"classPK", locale -> Field.ORGANIZATION_ID, String::valueOf),
			new IdEntityField(
				"companyId", locale -> Field.COMPANY_ID, String::valueOf),
			new IdEntityField(
				"organizationId", locale -> Field.ORGANIZATION_ID,
				String::valueOf),
			new IdEntityField(
				"parentOrganizationId", locale -> "parentOrganizationId",
				String::valueOf),
			new StringEntityField("country", locale -> "country"),
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName(Field.NAME)),
			new StringEntityField(
				"nameTreePath",
				locale -> Field.getSortableFieldName("nameTreePath_String")),
			new StringEntityField("region", locale -> "region"),
			new StringEntityField("type", locale -> Field.TYPE));
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}