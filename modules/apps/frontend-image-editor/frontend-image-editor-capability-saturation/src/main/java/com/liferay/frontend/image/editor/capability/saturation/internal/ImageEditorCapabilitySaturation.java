/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.capability.saturation.internal;

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
		"com.liferay.frontend.image.editor.capability.category=adjust",
		"com.liferay.frontend.image.editor.capability.controls=saturation",
		"com.liferay.frontend.image.editor.capability.icon=effects",
		"com.liferay.frontend.image.editor.capability.name=saturation",
		"com.liferay.frontend.image.editor.capability.type=tool"
	},
	service = ImageEditorCapability.class
)
public class ImageEditorCapabilitySaturation extends BaseImageEditorCapability {

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return ResourceBundleUtil.getString(resourceBundle, "saturation");
	}

	@Override
	public String getName() {
		return "saturation";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.image.editor.capability.saturation)"
	)
	private ServletContext _servletContext;

}