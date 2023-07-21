/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.metadata;

import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.xml.Element;

import java.io.File;
import java.io.InputStream;

import java.lang.reflect.Field;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Miguel Pastor
 */
@ProviderType
public interface RawMetadataProcessor {

	public static final String TIKA_RAW_METADATA = "TIKARAWMETADATA";

	public void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception;

	public Map<String, Field[]> getFields();

	public Map<String, DDMFormValues> getRawMetadataMap(
			String extension, String mimeType, File file)
		throws PortalException;

	public Map<String, DDMFormValues> getRawMetadataMap(
			String extension, String mimeType, InputStream inputStream)
		throws PortalException;

	public void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception;

}