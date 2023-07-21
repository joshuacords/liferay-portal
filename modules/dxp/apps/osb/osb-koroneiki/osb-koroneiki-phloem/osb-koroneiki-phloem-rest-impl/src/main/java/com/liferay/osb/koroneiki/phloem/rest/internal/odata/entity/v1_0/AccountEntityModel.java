/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.BooleanEntityField;
import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amos Fong
 */
public class AccountEntityModel implements EntityModel {

	public static final String NAME = "Account";

	public AccountEntityModel(List<String> accountFieldNames) {
		_entityFieldsMap = Stream.of(
			new StringEntityField("accountKey", locale -> "accountKey"),
			new CollectionEntityField(
				new StringEntityField(
					"activeProductKeys", locale -> "activeProductKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"assignedTeamKeyTeamRoleKeys",
					locale -> "assignedTeamKeyTeamRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys",
					locale ->
						"assignedTeamKeyTeamRoleKeyContactUuidContact" +
							"RoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"cancelledProductKeys", locale -> "cancelledProductKeys")),
			new EntityField(
				"code", EntityField.Type.STRING,
				locale -> Field.getSortableFieldName("code_String"),
				locale -> "code", String::valueOf),
			new CollectionEntityField(
				new StringEntityField(
					"contactEmailAddresses",
					locale -> "contactEmailAddresses")),
			new CollectionEntityField(
				new StringEntityField(
					"contactUuidContactRoleKeys",
					locale -> "contactUuidContactRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"contactUuids", locale -> "contactUuids")),
			new StringEntityField("creatorUuid", locale -> "userUuid"),
			new CollectionEntityField(
				new StringEntityField(
					"customerContactEmailAddresses",
					locale -> "customerContactEmailAddresses")),
			new CollectionEntityField(
				new StringEntityField(
					"customerContactUuids", locale -> "customerContactUuids")),
			new StringEntityField("dataRegion", locale -> "dataRegion"),
			new DateTimeEntityField(
				"dateCreated",
				locale -> Field.getSortableFieldName(Field.CREATE_DATE),
				locale -> Field.CREATE_DATE),
			new DateTimeEntityField(
				"dateModified",
				locale -> Field.getSortableFieldName(Field.MODIFIED_DATE),
				locale -> Field.MODIFIED_DATE),
			new CollectionEntityField(
				new StringEntityField(
					"entitlements", locale -> "entitlements")),
			new CollectionEntityField(
				new StringEntityField(
					"expiredProductKeys", locale -> "expiredProductKeys")),
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
			new CollectionEntityField(
				new StringEntityField(
					"generalArchivedNoteContent",
					locale -> "accountNoteGeneralContentArchived")),
			new CollectionEntityField(
				new StringEntityField(
					"generalNoteContent",
					locale -> "accountNoteGeneralContent")),
			new BooleanEntityField("internal", locale -> "internal"),
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName("name_String")),
			new BooleanEntityField("parent", locale -> "parent"),
			new StringEntityField(
				"parentAccountKey", locale -> "parentAccountKey"),
			new CollectionEntityField(
				new StringEntityField(
					"postalAddressCities",
					locale -> Field.getSortableFieldName(
						"addressCities_String"))),
			new CollectionEntityField(
				new StringEntityField(
					"postalAddressCountries",
					locale -> Field.getSortableFieldName(
						"addressCountries_String"))),
			new CollectionEntityField(
				new StringEntityField(
					"postalAddressRegions",
					locale -> Field.getSortableFieldName(
						"addressRegions_String"))),
			new CollectionEntityField(
				new StringEntityField(
					"postalAddressStreets",
					locale -> Field.getSortableFieldName(
						"addressStreets_String"))),
			new CollectionEntityField(
				new StringEntityField(
					"postalAddressZips",
					locale -> Field.getSortableFieldName(
						"addressZips_String"))),
			new CollectionEntityField(
				new StringEntityField(
					"productKeys", locale -> "productEntryKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"productPurchaseExternalLinkDomains",
					locale -> "productPurchaseExternalLinkDomains")),
			new CollectionEntityField(
				new StringEntityField(
					"productPurchaseExternalLinkEntityIds",
					locale -> "productPurchaseExternalLinkEntityIds")),
			new CollectionEntityField(
				new StringEntityField(
					"productPurchaseExternalLinkEntityNames",
					locale -> "productPurchaseExternalLinkEntityNames")),
			new StringEntityField(
				"region",
				locale -> Field.getSortableFieldName("region_String")),
			new CollectionEntityField(
				new StringEntityField(
					"salesArchivedNoteContent",
					locale -> "accountNoteSalesContentArchived")),
			new CollectionEntityField(
				new StringEntityField(
					"salesNoteContent", locale -> "accountNoteSalesContent")),
			new StringEntityField("status", locale -> "status"),
			new CollectionEntityField(
				new StringEntityField(
					"teamsAssignedToAccountKeyTeamRoleKeys",
					locale -> "teamsAssignedToAccountKeyTeamRoleKeys")),
			new StringEntityField("tier", locale -> "tier"),
			new CollectionEntityField(
				new StringEntityField(
					"unactivatedProductKeys",
					locale -> "unactivatedProductKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"workerContactEmailAddresses",
					locale -> "workerContactEmailAddresses")),
			new CollectionEntityField(
				new StringEntityField(
					"workerContactUuids", locale -> "workerContactUuids"))
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);

		for (String accountFieldName : accountFieldNames) {
			String name = "property_" + accountFieldName;

			_entityFieldsMap.put(
				name, new StringEntityField(name, locale -> name));
		}
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