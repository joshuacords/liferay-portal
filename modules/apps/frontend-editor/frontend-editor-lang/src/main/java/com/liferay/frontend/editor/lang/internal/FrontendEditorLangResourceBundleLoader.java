/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.lang.internal;

import com.liferay.portal.kernel.language.UTF8Control;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = "bundle.symbolic.name=com.liferay.frontend.editor.lang",
	service = ResourceBundleLoader.class
)
public class FrontendEditorLangResourceBundleLoader
	implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {
		Class<?> clazz = getClass();

		return ResourceBundle.getBundle(
			"content.Language", locale, clazz.getClassLoader(),
			UTF8Control.INSTANCE);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #loadResourceBundle(Locale)}
	 */
	@Deprecated
	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return loadResourceBundle(LocaleUtil.fromLanguageId(languageId));
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}