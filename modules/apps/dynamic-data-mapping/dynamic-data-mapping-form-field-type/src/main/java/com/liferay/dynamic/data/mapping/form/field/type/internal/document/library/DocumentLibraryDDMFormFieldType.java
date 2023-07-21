/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.document.library;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pedro Queiroz
 */
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.description=document-library-field-type-description",
		"ddm.form.field.type.display.order:Integer=9",
		"ddm.form.field.type.group=customized",
		"ddm.form.field.type.icon=upload",
		"ddm.form.field.type.label=document-library-field-type-label",
		"ddm.form.field.type.name=document_library"
	},
	service = DDMFormFieldType.class
)
public class DocumentLibraryDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public Class<? extends DDMFormFieldTypeSettings>
		getDDMFormFieldTypeSettings() {

		return DocumentLibraryDDMFormFieldTypeSettings.class;
	}

	@Override
	public String getModuleName() {
		return "dynamic-data-mapping-form-field-type/DocumentLibrary" +
			"/DocumentLibrary.es";
	}

	@Override
	public String getName() {
		return "document_library";
	}

}