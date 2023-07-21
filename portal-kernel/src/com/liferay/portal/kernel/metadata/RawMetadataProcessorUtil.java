/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.metadata;

import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;
import java.io.InputStream;

import java.lang.reflect.Field;

import java.util.Map;

/**
 * @author Miguel Pastor
 */
public class RawMetadataProcessorUtil {

	public static Map<String, Field[]> getFields() {
		return getRawMetadataProcessor().getFields();
	}

	public static Map<String, DDMFormValues> getRawMetadataMap(
			String extension, String mimeType, File file)
		throws PortalException {

		return getRawMetadataProcessor().getRawMetadataMap(
			extension, mimeType, file);
	}

	public static Map<String, DDMFormValues> getRawMetadataMap(
			String extension, String mimeType, InputStream inputStream)
		throws PortalException {

		return getRawMetadataProcessor().getRawMetadataMap(
			extension, mimeType, inputStream);
	}

	public static RawMetadataProcessor getRawMetadataProcessor() {
		return _rawMetadataProcessor;
	}

	public void setRawMetadataProcessor(
		RawMetadataProcessor rawMetadataProcessor) {

		_rawMetadataProcessor = rawMetadataProcessor;
	}

	private static RawMetadataProcessor _rawMetadataProcessor;

}