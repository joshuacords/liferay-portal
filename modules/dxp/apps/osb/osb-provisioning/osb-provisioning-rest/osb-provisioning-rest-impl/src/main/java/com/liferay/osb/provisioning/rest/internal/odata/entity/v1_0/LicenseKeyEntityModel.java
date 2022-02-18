/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
 * @author Kyle Bischof
 */
public class LicenseKeyEntityModel implements EntityModel {

	public static final String NAME = "LicenseKey";

	public LicenseKeyEntityModel() {
		_entityFieldsMap = Stream.of(
			new StringEntityField("accountKey", locale -> "accountKey"),
			new BooleanEntityField("active", locale -> "active"),
			new StringEntityField("description", locale -> "description"),
			new DateTimeEntityField(
				"expirationDate", locale -> Field.EXPIRATION_DATE,
				locale -> Field.EXPIRATION_DATE),
			new StringEntityField("hostName", locale -> "hostName"),
			new StringEntityField("instanceSize", locale -> "sizing"),
			new CollectionEntityField(
				new StringEntityField("ipAddresses", locale -> "ipAddresses")),
			new CollectionEntityField(
				new StringEntityField(
					"macAddresses", locale -> "macAddresses")),
			new StringEntityField("name", locale -> "name"),
			new StringEntityField("productVersion", locale -> "productVersion"),
			new DateTimeEntityField(
				"startDate", locale -> "startDate", locale -> "startDate"),
			new StringEntityField("type", locale -> "licenseEntryType")
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