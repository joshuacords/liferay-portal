/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.xsl.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManager;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.template.BaseTemplateManager;
import com.liferay.portal.template.TemplateContextHelper;
import com.liferay.portal.template.xsl.configuration.XSLEngineConfiguration;
import com.liferay.portal.xsl.XSLTemplateResource;

import java.util.Map;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Tina Tian
 */
@Component(
	configurationPid = "com.liferay.portal.template.xsl.configuration.XSLEngineConfiguration",
	immediate = true,
	property = "language.type=" + TemplateConstants.LANG_TYPE_XSL,
	service = TemplateManager.class
)
public class XSLManager extends BaseTemplateManager {

	@Override
	public void destroy() {
		if (templateContextHelper == null) {
			return;
		}

		templateContextHelper.removeAllHelperUtilities();

		templateContextHelper = null;
	}

	@Override
	public void destroy(ClassLoader classLoader) {
		templateContextHelper.removeHelperUtilities(classLoader);
	}

	@Override
	public String getName() {
		return TemplateConstants.LANG_TYPE_XSL;
	}

	@Override
	public void init() {
		templateContextHelper = new TemplateContextHelper();
	}

	@Activate
	@Modified
	protected void activate(ComponentContext componentContext) {
		_xslEngineConfiguration = ConfigurableUtil.createConfigurable(
			XSLEngineConfiguration.class, componentContext.getProperties());
	}

	@Override
	protected Template doGetTemplate(
		TemplateResource templateResource, boolean restricted,
		Map<String, Object> helperUtilities) {

		XSLTemplateResource xslTemplateResource =
			(XSLTemplateResource)templateResource;

		return new XSLTemplate(
			xslTemplateResource, templateContextHelper, _xslEngineConfiguration,
			restricted);
	}

	private volatile XSLEngineConfiguration _xslEngineConfiguration;

}