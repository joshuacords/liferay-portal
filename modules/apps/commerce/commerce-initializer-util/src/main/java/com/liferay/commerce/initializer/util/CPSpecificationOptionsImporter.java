/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.initializer.util.internal.CommerceInitializerUtil;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPOptionCategoryLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(service = CPSpecificationOptionsImporter.class)
public class CPSpecificationOptionsImporter {

	public List<CPSpecificationOption> importCPSpecificationOptions(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		List<CPSpecificationOption> cpSpecificationOptions = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			CPSpecificationOption cpSpecificationOption =
				_importCPSpecificationOption(
					jsonArray.getJSONObject(i), serviceContext);

			cpSpecificationOptions.add(cpSpecificationOption);
		}

		return cpSpecificationOptions;
	}

	private CPSpecificationOption _importCPSpecificationOption(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		String key = jsonObject.getString("Key");

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionLocalService.fetchCPSpecificationOption(
				serviceContext.getCompanyId(), key);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		long cpOptionCategoryId = 0;

		String categoryKey = jsonObject.getString("CategoryKey");

		if (Validator.isNotNull(categoryKey)) {
			CPOptionCategory cpOptionCategory =
				_cpOptionCategoryLocalService.getCPOptionCategory(
					serviceContext.getCompanyId(), categoryKey);

			cpOptionCategoryId = cpOptionCategory.getCPOptionCategoryId();
		}

		Locale locale = LocaleUtil.getSiteDefault();

		Map<Locale, String> titleMap = Collections.singletonMap(
			locale, CommerceInitializerUtil.getValue(jsonObject, "Title", key));

		Map<Locale, String> descriptionMap = Collections.singletonMap(
			locale, jsonObject.getString("Description"));

		boolean facetable = jsonObject.getBoolean("Facetable");

		return _cpSpecificationOptionLocalService.addCPSpecificationOption(
			serviceContext.getUserId(), cpOptionCategoryId, titleMap,
			descriptionMap, facetable, key, serviceContext);
	}

	@Reference
	private CPOptionCategoryLocalService _cpOptionCategoryLocalService;

	@Reference
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}