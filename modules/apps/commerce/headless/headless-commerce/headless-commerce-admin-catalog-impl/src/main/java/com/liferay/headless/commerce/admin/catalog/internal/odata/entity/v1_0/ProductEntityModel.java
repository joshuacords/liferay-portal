/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.odata.entity.v1_0;

import com.liferay.commerce.product.constants.CPField;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
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
 * @author Alessio Antonio Rendina
 */
public class ProductEntityModel implements EntityModel {

	public ProductEntityModel() {
		_entityFieldsMap = Stream.of(
			new CollectionEntityField(
				new IntegerEntityField(
					"categoryIds", locale -> "assetCategoryIds")),
			new IntegerEntityField("catalogId", locale -> "commerceCatalogId"),
			new CollectionEntityField(
				new EntityField(
					"channelId", EntityField.Type.INTEGER,
					locale -> Field.getSortableFieldName(
						CPField.CHANNEL_GROUP_IDS),
					locale -> CPField.CHANNEL_GROUP_IDS,
					object -> _getChannelGroupId(object))),
			new DateTimeEntityField(
				"createDate",
				locale -> Field.getSortableFieldName(Field.CREATE_DATE),
				locale -> Field.CREATE_DATE),
			new DateTimeEntityField(
				"modifiedDate",
				locale -> Field.getSortableFieldName(Field.MODIFIED_DATE),
				locale -> Field.MODIFIED_DATE),
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName("name")),
			new StringEntityField("productType", locale -> "productTypeName"),
			new CollectionEntityField(
				new IntegerEntityField("statusCode", locale -> Field.STATUS))
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private String _getChannelGroupId(Object channelId) {
		CommerceChannel commerceChannel =
			CommerceChannelLocalServiceUtil.fetchCommerceChannel(
				GetterUtil.getLong(channelId));

		if (commerceChannel == null) {
			return "-1";
		}

		return String.valueOf(commerceChannel.getGroupId());
	}

	private final Map<String, EntityField> _entityFieldsMap;

}