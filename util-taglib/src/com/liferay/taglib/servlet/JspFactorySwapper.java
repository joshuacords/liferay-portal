/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.servlet;

import com.liferay.portal.kernel.util.ServerDetector;

import javax.servlet.jsp.JspFactory;

/**
 * @author Shuyang Zhou
 */
public class JspFactorySwapper {

	public static void swap() {
		JspFactory jspFactory = JspFactory.getDefaultFactory();

		if (jspFactory instanceof AutoCloseJspFactoryWrapper) {
			return;
		}

		synchronized (JspFactorySwapper.class) {
			if (_jspFactory == null) {
				if (ServerDetector.isTomcat()) {
					jspFactory = new JspFactoryWrapper(jspFactory);
				}

				_jspFactory = new AutoCloseJspFactoryWrapper(jspFactory);
			}

			JspFactory.setDefaultFactory(_jspFactory);
		}
	}

	private static JspFactory _jspFactory;

}