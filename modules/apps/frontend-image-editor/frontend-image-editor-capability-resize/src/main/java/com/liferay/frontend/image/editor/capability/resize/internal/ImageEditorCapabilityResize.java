/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.capability.resize.internal;

import com.liferay.frontend.image.editor.capability.BaseImageEditorCapability;
import com.liferay.frontend.image.editor.capability.ImageEditorCapability;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.frontend.image.editor.capability.category=transform",
		"com.liferay.frontend.image.editor.capability.controls=resize",
		"com.liferay.frontend.image.editor.capability.icon=transform",
		"com.liferay.frontend.image.editor.capability.name=resize",
		"com.liferay.frontend.image.editor.capability.type=tool"
	},
	service = ImageEditorCapability.class
)
public class ImageEditorCapabilityResize extends BaseImageEditorCapability {

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return ResourceBundleUtil.getString(resourceBundle, "resize");
	}

	@Override
	public String getName() {
		return "resize";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.image.editor.capability.resize)"
	)
	private ServletContext _servletContext;

}