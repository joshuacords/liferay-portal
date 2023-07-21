/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.BooleanEntityField;
import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amos Fong
 */
public class TeamEntityModel implements EntityModel {

	public static final String NAME = "Team";

	public TeamEntityModel() {
		_entityFieldsMap = Stream.of(
			new CollectionEntityField(
				new StringEntityField(
					"accountEntitlements", locale -> "accountEntitlements")),
			new StringEntityField("accountKey", locale -> "accountKey"),
			new CollectionEntityField(
				new StringEntityField(
					"accountKeyTeamRoleKeys",
					locale -> "accountKeyTeamRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"contactEmailAddresses",
					locale -> "contactEmailAddresses")),
			new CollectionEntityField(
				new StringEntityField(
					"contactUuids", locale -> "contactUuids")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkDomains", locale -> "externalLinkDomains")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkEntityIds",
					locale -> "externalLinkEntityIds")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkEntityNames",
					locale -> "externalLinkEntityNames")),
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName("name_String")),
			new BooleanEntityField("system", locale -> "system")
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);
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