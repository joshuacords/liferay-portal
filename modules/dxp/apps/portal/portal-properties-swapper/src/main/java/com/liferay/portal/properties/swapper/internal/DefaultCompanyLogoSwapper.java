/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.properties.swapper.internal;

import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import java.lang.reflect.Method;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(enabled = false, immediate = true, service = {})
public class DefaultCompanyLogoSwapper {

	@Activate
	public void activate(BundleContext bundleContext) {
		if (PropsHelperUtil.isCustomized(
				PropsKeys.IMAGE_DEFAULT_COMPANY_LOGO)) {

			return;
		}

		Bundle bundle = bundleContext.getBundle();

		PropsUtil.set(
			PropsKeys.IMAGE_DEFAULT_COMPANY_LOGO,
			bundle.getBundleId() +
				";com/liferay/portal/properties/swapper/internal" +
					"/default_company_logo.png");

		ImageTool imageTool = ImageToolUtil.getImageTool();

		Class<?> clazz = imageTool.getClass();

		try {
			Method method = clazz.getMethod("afterPropertiesSet");

			method.invoke(imageTool);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to swap default company logo",
					reflectiveOperationException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultCompanyLogoSwapper.class);

}