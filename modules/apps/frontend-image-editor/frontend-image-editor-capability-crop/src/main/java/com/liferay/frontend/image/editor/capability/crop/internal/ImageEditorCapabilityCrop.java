/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.capability.crop.internal;

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
		"com.liferay.frontend.image.editor.capability.controls=crop",
		"com.liferay.frontend.image.editor.capability.icon=transform",
		"com.liferay.frontend.image.editor.capability.name=crop",
		"com.liferay.frontend.image.editor.capability.type=tool"
	},
	service = ImageEditorCapability.class
)
public class ImageEditorCapabilityCrop extends BaseImageEditorCapability {

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return ResourceBundleUtil.getString(resourceBundle, "crop");
	}

	@Override
	public String getName() {
		return "crop";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.image.editor.capability.crop)"
	)
	private ServletContext _servletContext;

}