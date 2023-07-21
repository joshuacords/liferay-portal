/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.display.field;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.contributor.field.ExpandoInfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldType;
import com.liferay.info.display.field.ExpandoInfoDisplayFieldProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = ExpandoInfoDisplayFieldProvider.class)
public class ExpandoInfoDisplayFieldProviderImpl
	implements ExpandoInfoDisplayFieldProvider {

	@Override
	public List<InfoDisplayField> getContributorExpandoInfoDisplayFields(
		String className, Locale locale) {

		List<InfoDisplayField> infoDisplayFields = new ArrayList<>();

		for (ExpandoInfoDisplayContributorField
				expandoInfoDisplayContributorField :
					_getExpandoInfoDisplayContributorFields(className)) {

			InfoDisplayContributorFieldType infoDisplayContributorFieldType =
				expandoInfoDisplayContributorField.getType();

			infoDisplayFields.add(
				new InfoDisplayField(
					expandoInfoDisplayContributorField.getKey(),
					expandoInfoDisplayContributorField.getLabel(locale),
					infoDisplayContributorFieldType.getValue()));
		}

		return infoDisplayFields;
	}

	@Override
	public Map<String, Object> getContributorExpandoInfoDisplayFieldsValues(
		String className, Object displayObject, Locale locale) {

		Map<String, Object> expandoInfoDisplayFieldsValues = new HashMap<>();

		for (ExpandoInfoDisplayContributorField
				expandoInfoDisplayContributorField :
					_getExpandoInfoDisplayContributorFields(className)) {

			expandoInfoDisplayFieldsValues.putIfAbsent(
				expandoInfoDisplayContributorField.getKey(),
				expandoInfoDisplayContributorField.getValue(
					displayObject, locale));
		}

		return expandoInfoDisplayFieldsValues;
	}

	private List<ExpandoInfoDisplayContributorField>
		_getExpandoInfoDisplayContributorFields(String className) {

		List<ExpandoInfoDisplayContributorField>
			expandoInfoDisplayContributorFields = new ArrayList<>();

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			CompanyThreadLocal.getCompanyId(), className, 0L);

		Enumeration<String> attributeNames = expandoBridge.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			expandoInfoDisplayContributorFields.add(
				new ExpandoInfoDisplayContributorField(
					attributeName, expandoBridge));
		}

		return expandoInfoDisplayContributorFields;
	}

}