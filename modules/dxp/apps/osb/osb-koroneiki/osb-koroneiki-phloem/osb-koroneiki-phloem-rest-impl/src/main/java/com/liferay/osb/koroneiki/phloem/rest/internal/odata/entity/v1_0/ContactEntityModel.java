/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
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
public class ContactEntityModel implements EntityModel {

	public static final String NAME = "Contact";

	public ContactEntityModel() {
		_entityFieldsMap = Stream.of(
			new CollectionEntityField(
				new StringEntityField("accountKeys", locale -> "accountKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"accountKeysContactRoleKeys",
					locale -> "accountKeysContactRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"customerAccountKeys", locale -> "customerAccountKeys")),
			new EntityField(
				"emailAddress", EntityField.Type.STRING,
				locale -> Field.getSortableFieldName("emailAddress_String"),
				locale -> "emailAddress", String::valueOf),
			new CollectionEntityField(
				new StringEntityField(
					"entitlements", locale -> "entitlements")),
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
			new StringEntityField("firstName", locale -> "firstName"),
			new StringEntityField("lastName", locale -> "lastName"),
			new StringEntityField("middleName", locale -> "middleName"),
			new CollectionEntityField(
				new StringEntityField("teamKeys", locale -> "teamKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"workerAccountKeys", locale -> "workerAccountKeys"))
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