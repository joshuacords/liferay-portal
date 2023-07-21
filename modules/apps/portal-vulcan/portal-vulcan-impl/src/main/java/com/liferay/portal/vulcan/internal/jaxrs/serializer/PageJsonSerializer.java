/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import com.liferay.portal.vulcan.pagination.Page;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

/**
 * @author Javier Gamarra
 */
public class PageJsonSerializer extends JsonSerializer<Page> {

	@Override
	public void serialize(
			Page page, JsonGenerator jsonGenerator, SerializerProvider provider)
		throws IOException {

		ToXmlGenerator toXmlGenerator = (ToXmlGenerator)jsonGenerator;

		toXmlGenerator.writeStartObject();

		toXmlGenerator.writeFieldName("items");

		toXmlGenerator.writeStartObject();

		toXmlGenerator.writeFieldName("items");

		toXmlGenerator.writeStartArray();

		for (Object item : page.getItems()) {
			Class<?> clazz = item.getClass();

			Class<?> superClass = clazz.getSuperclass();

			XmlRootElement xmlRootElement = superClass.getAnnotation(
				XmlRootElement.class);

			if (xmlRootElement != null) {
				toXmlGenerator.setNextName(new QName(xmlRootElement.name()));
			}

			toXmlGenerator.writeObject(item);
		}

		toXmlGenerator.writeEndArray();

		toXmlGenerator.writeEndObject();

		toXmlGenerator.writeObjectField("lastPage", page.getLastPage());

		toXmlGenerator.writeObjectField("page", page.getPage());

		toXmlGenerator.writeObjectField("pageSize", page.getPageSize());

		toXmlGenerator.writeObjectField("totalCount", page.getTotalCount());

		toXmlGenerator.writeEndObject();
	}

}