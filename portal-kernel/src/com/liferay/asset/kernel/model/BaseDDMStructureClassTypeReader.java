/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.model;

import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.DDMStructureManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Adolfo Pérez
 */
public class BaseDDMStructureClassTypeReader implements ClassTypeReader {

	public BaseDDMStructureClassTypeReader(String className) {
		_className = className;
	}

	@Override
	public List<ClassType> getAvailableClassTypes(
		long[] groupIds, Locale locale) {

		List<ClassType> classTypes = new ArrayList<>();

		List<DDMStructure> ddmStructures =
			DDMStructureManagerUtil.getStructures(
				groupIds, PortalUtil.getClassNameId(_className));

		for (DDMStructure ddmStructure : ddmStructures) {
			classTypes.add(
				new DDMStructureClassType(
					ddmStructure.getStructureId(), ddmStructure.getName(locale),
					LocaleUtil.toLanguageId(locale)));
		}

		return classTypes;
	}

	@Override
	public ClassType getClassType(long classTypeId, Locale locale)
		throws PortalException {

		DDMStructure ddmStructure = DDMStructureManagerUtil.getStructure(
			classTypeId);

		return new DDMStructureClassType(
			classTypeId, ddmStructure.getName(locale),
			LocaleUtil.toLanguageId(locale));
	}

	private final String _className;

}