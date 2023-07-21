/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.display.field;

import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeField;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.field.ClassTypesInfoDisplayFieldProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = ClassTypesInfoDisplayFieldProvider.class)
public class ClassTypesInfoDisplayFieldProviderImpl
	implements ClassTypesInfoDisplayFieldProvider {

	@Override
	public List<InfoDisplayField> getClassTypeInfoDisplayFields(
			ClassType classType, Locale locale)
		throws PortalException {

		if (classType == null) {
			return Collections.emptyList();
		}

		List<InfoDisplayField> classTypeInfoDisplayFields = new ArrayList<>();

		for (ClassTypeField classTypeField : classType.getClassTypeFields()) {
			classTypeInfoDisplayFields.add(
				new InfoDisplayField(
					classTypeField.getName(),
					LanguageUtil.get(locale, classTypeField.getLabel()),
					classTypeField.getType()));
		}

		return classTypeInfoDisplayFields;
	}

	@Override
	public List<ClassType> getClassTypes(
			long groupId, ClassTypeReader classTypeReader, Locale locale)
		throws PortalException {

		return classTypeReader.getAvailableClassTypes(
			_portal.getCurrentAndAncestorSiteGroupIds(groupId), locale);
	}

	@Reference
	private Portal _portal;

}