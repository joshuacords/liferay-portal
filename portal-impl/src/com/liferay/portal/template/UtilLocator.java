/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template;

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Raymond Augé
 */
public class UtilLocator {

	public static UtilLocator getInstance() {
		return _utilLocator;
	}

	public Object findUtil(String utilName) {
		Object bean = null;

		try {
			bean = PortalBeanLocatorUtil.locate(_getUtilName(utilName));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return bean;
	}

	public Object findUtil(String servletContextName, String utilName) {
		Object bean = null;

		try {
			bean = PortletBeanLocatorUtil.locate(
				servletContextName, _getUtilName(utilName));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return bean;
	}

	private UtilLocator() {
	}

	private String _getUtilName(String utilName) {
		if (!utilName.endsWith(BeanLocatorImpl.VELOCITY_SUFFIX)) {
			utilName += BeanLocatorImpl.VELOCITY_SUFFIX;
		}

		return utilName;
	}

	private static final Log _log = LogFactoryUtil.getLog(UtilLocator.class);

	private static final UtilLocator _utilLocator = new UtilLocator();

}