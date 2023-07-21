/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.kernel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.io.File;

import java.util.Locale;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class DDMTemplateManagerUtil {

	public static DDMTemplate addTemplate(
			long userId, long groupId, long classNameId, long classPK,
			long resourceClassNameId, String templateKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String type, String mode, String language, String script,
			boolean cacheable, boolean smallImage, String smallImageURL,
			File smallImageFile, ServiceContext serviceContext)
		throws PortalException {

		return _ddmTemplateManager.addTemplate(
			userId, groupId, classNameId, classPK, resourceClassNameId,
			templateKey, nameMap, descriptionMap, type, mode, language, script,
			cacheable, smallImage, smallImageURL, smallImageFile,
			serviceContext);
	}

	public static DDMTemplate fetchTemplate(
		long groupId, long classNameId, String templateKey) {

		return _ddmTemplateManager.fetchTemplate(
			groupId, classNameId, templateKey);
	}

	public static DDMTemplate getTemplate(long templateId)
		throws PortalException {

		return _ddmTemplateManager.getTemplate(templateId);
	}

	private static volatile DDMTemplateManager _ddmTemplateManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			DDMTemplateManager.class, DDMTemplateManagerUtil.class,
			"_ddmTemplateManager", false);

}