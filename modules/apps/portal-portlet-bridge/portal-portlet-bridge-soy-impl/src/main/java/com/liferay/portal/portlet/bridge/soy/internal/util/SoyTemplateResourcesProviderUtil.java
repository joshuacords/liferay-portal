/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.portlet.bridge.soy.internal.util;

import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.template.soy.util.SoyTemplateResourcesProvider;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = {})
public class SoyTemplateResourcesProviderUtil {

	public static List<TemplateResource> getBundleTemplateResources(
		Bundle bundle, String templatePath) {

		return _soyTemplateResourcesProvider.getBundleTemplateResources(
			bundle, templatePath);
	}

	@Reference(unbind = "-")
	protected void setSoyTemplateResourcesProvider(
		SoyTemplateResourcesProvider soyTemplateResourcesProvider) {

		_soyTemplateResourcesProvider = soyTemplateResourcesProvider;
	}

	private static SoyTemplateResourcesProvider _soyTemplateResourcesProvider;

}