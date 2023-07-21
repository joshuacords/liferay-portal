/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.asset;

import com.liferay.asset.kernel.model.ClassTypeField;
import com.liferay.asset.kernel.model.DDMStructureClassType;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class DLFileEntryClassType extends DDMStructureClassType {

	public DLFileEntryClassType(
		long classTypeId, String classTypeName, String languageId) {

		super(classTypeId, classTypeName, languageId);
	}

	@Override
	public List<ClassTypeField> getClassTypeFields() throws PortalException {
		List<ClassTypeField> classTypeFields = new ArrayList<>();

		DLFileEntryType dlFileEntryType =
			DLFileEntryTypeLocalServiceUtil.getDLFileEntryType(
				getClassTypeId());

		List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			classTypeFields.addAll(
				getClassTypeFields(ddmStructure.getStructureId()));
		}

		return classTypeFields;
	}

}