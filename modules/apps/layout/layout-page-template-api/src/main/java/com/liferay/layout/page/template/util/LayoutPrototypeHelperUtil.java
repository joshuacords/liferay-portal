/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.util;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class LayoutPrototypeHelperUtil {

	public static Layout addLayoutPrototype(
			LayoutPrototypeLocalService layoutPrototypeLocalService,
			long companyId, long defaultUserId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String layoutTemplateId,
			List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		for (LayoutPrototype layoutPrototype : layoutPrototypes) {
			String defaultLanguageId = LocalizationUtil.getDefaultLanguageId(
				layoutPrototype.getName());

			Locale defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);

			String name = nameMap.get(defaultLocale);

			if ((name == null) ||
				name.equals(layoutPrototype.getName(defaultLocale))) {

				return null;
			}
		}

		LayoutPrototype layoutPrototype =
			layoutPrototypeLocalService.addLayoutPrototype(
				defaultUserId, companyId, nameMap, descriptionMap, true,
				new ServiceContext());

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layoutTemplateId, false);

		return layout;
	}

}