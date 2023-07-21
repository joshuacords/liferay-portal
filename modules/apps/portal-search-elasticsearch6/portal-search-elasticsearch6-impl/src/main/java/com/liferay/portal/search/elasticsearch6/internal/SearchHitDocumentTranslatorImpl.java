/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collection;
import java.util.Map;

import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.search.SearchHit;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = SearchHitDocumentTranslator.class)
public class SearchHitDocumentTranslatorImpl
	implements SearchHitDocumentTranslator {

	@Override
	public Document translate(SearchHit searchHit) {
		Document document = new DocumentImpl();

		Map<String, DocumentField> documentFields = searchHit.getFields();

		for (String documentFieldName : documentFields.keySet()) {
			addField(document, documentFieldName, documentFields);
		}

		return document;
	}

	protected void addField(
		Document document, String fieldName,
		Map<String, DocumentField> documentFields) {

		Field field = getField(fieldName, documentFields);

		if (field != null) {
			document.add(field);
		}
	}

	protected Field getField(
		String fieldName, Map<String, DocumentField> documentFields) {

		String geopointIndicatorSuffix = ".geopoint";

		if (fieldName.endsWith(geopointIndicatorSuffix)) {
			return null;
		}

		DocumentField documentField = documentFields.get(fieldName);

		if (documentFields.containsKey(
				fieldName.concat(geopointIndicatorSuffix))) {

			return translateGeoPoint(documentField);
		}

		return translate(documentField);
	}

	protected Field translate(DocumentField documentField) {
		String name = documentField.getName();

		Collection<Object> values = documentField.getValues();

		return new Field(
			name, ArrayUtil.toStringArray(values.toArray(new Object[0])));
	}

	protected Field translateGeoPoint(DocumentField documentField) {
		Field field = new Field(documentField.getName());

		String[] values = StringUtil.split(documentField.getValue());

		field.setGeoLocationPoint(
			new GeoLocationPoint(
				Double.valueOf(values[0]), Double.valueOf(values[1])));

		return field;
	}

}