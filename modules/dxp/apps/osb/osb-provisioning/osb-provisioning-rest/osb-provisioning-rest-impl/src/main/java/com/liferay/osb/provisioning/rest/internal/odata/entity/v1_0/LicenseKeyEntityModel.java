/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.BooleanEntityField;
import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IntegerEntityField;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyEntityModel implements EntityModel {

	public static final String NAME = "LicenseKey";

	public LicenseKeyEntityModel() {
		_entityFieldsMap = Stream.of(
			new StringEntityField("accountKey", locale -> "accountKey"),
			new BooleanEntityField("active", locale -> "active"),
			new BooleanEntityField("complimentary", locale -> "complimentary"),
			new StringEntityField("description", locale -> "description"),
			new DateTimeEntityField(
				"expirationDate", locale -> Field.EXPIRATION_DATE,
				locale -> Field.EXPIRATION_DATE),
			new StringEntityField("hostName", locale -> "hostName"),
			new CollectionEntityField(
				new StringEntityField("ipAddresses", locale -> "ipAddresses")),
			new StringEntityField(
				"licenseEntryType", locale -> "licenseEntryType"),
			new CollectionEntityField(
				new StringEntityField(
					"macAddresses", locale -> "macAddresses")),
			new IntegerEntityField(
				"maxClusterNodes", locale -> "maxClusterNodes"),
			new StringEntityField("name", locale -> "name"),
			new StringEntityField(
				"productName",
				locale -> Field.getSortableFieldName("productName_String")),
			new StringEntityField("productVersion", locale -> "productVersion"),
			new StringEntityField("sizing", locale -> "sizing"),
			new DateTimeEntityField(
				"startDate", locale -> "startDate", locale -> "startDate"),
			new CollectionEntityField(
				new StringEntityField(
					"subscriptionContactUuids",
					locale -> "subscriptionContactUuids"))
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