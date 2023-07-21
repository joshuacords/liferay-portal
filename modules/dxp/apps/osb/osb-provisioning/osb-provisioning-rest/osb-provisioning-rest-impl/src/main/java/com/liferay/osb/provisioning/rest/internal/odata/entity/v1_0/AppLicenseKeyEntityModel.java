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
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amos Fong
 */
public class AppLicenseKeyEntityModel implements EntityModel {

	public static final String NAME = "AppLicenseKey";

	public AppLicenseKeyEntityModel() {
		_entityFieldsMap = Stream.of(
			new BooleanEntityField("active", locale -> "active"),
			new BooleanEntityField("complimentary", locale -> "complimentary"),
			new DateTimeEntityField(
				"expirationDate", locale -> Field.EXPIRATION_DATE,
				locale -> Field.EXPIRATION_DATE),
			new StringEntityField("hostName", locale -> "hostName"),
			new CollectionEntityField(
				new StringEntityField("ipAddresses", locale -> "ipAddresses")),
			new StringEntityField("licenseType", locale -> "licenseEntryType"),
			new CollectionEntityField(
				new StringEntityField(
					"macAddresses", locale -> "macAddresses")),
			new StringEntityField(
				"orderId",
				locale -> Field.getSortableFieldName(
					"assetReceiptLicenseUuid_String")),
			new StringEntityField(
				"productId",
				locale -> Field.getSortableFieldName("productId_String")),
			new StringEntityField(
				"productName",
				locale -> Field.getSortableFieldName("productName_String")),
			new StringEntityField("productVersion", locale -> "productVersion"),
			new DateTimeEntityField(
				"startDate", locale -> "startDate", locale -> "startDate")
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