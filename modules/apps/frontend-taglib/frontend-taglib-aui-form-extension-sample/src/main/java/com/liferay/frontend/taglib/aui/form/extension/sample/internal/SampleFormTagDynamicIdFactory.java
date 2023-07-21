/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.aui.form.extension.sample.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.servlet.taglib.TagDynamicIdFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true, property = "tagClassName=com.liferay.taglib.aui.FormTag",
	service = TagDynamicIdFactory.class
)
public class SampleFormTagDynamicIdFactory implements TagDynamicIdFactory {

	@Override
	public String getTagDynamicId(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, Object tag) {

		String portletId = _portal.getPortletId(httpServletRequest);

		if (Validator.isNull(portletId)) {
			return null;
		}

		String name = BeanPropertiesUtil.getStringSilent(tag, "name");

		if (Validator.isNull(name)) {
			return null;
		}

		return portletId.concat(
			StringPool.DASH
		).concat(
			name
		);
	}

	@Reference
	private Portal _portal;

}