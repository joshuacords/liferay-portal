/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.template.TemplateResourceParser;
import com.liferay.portal.template.URLResourceParser;
import com.liferay.portal.template.soy.internal.util.SoyTemplateUtil;

import java.net.URL;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "lang.type=" + TemplateConstants.LANG_TYPE_SOY,
	service = {
		SoyTemplateBundleResourceParser.class, TemplateResourceParser.class
	}
)
public class SoyTemplateBundleResourceParser extends URLResourceParser {

	@Override
	public URL getURL(String templateId) {
		Bundle bundle = _bundleContext.getBundle(
			SoyTemplateUtil.getBundleId(templateId));

		int index = templateId.indexOf(TemplateConstants.BUNDLE_SEPARATOR);

		String templateName = templateId.substring(
			index + TemplateConstants.BUNDLE_SEPARATOR.length());

		return bundle.getResource(templateName);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private BundleContext _bundleContext;

}