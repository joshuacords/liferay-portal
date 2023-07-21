/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.admin.kernel.util;

/**
 * @author     Eudaldo Alonso
 * @deprecated As of Wilberforce (7.0.x), with a replacement. Theme developers
 *             must eventually switch from using Velocity  templates that
 *             leverage this taglib wrapper mechanism, to using FreeMarker
 *             templates that leverage the
 *             <code>liferay-product-navigation:control-menu</code> tag.
 */
@Deprecated
public class PortalProductNavigationControlMenuApplicationType {

	public interface ProductNavigationControlMenu {

		public static final String CLASS_NAME =
			"com.liferay.admin.kernel.util." +
				"PortalProductNavigationControlMenuApplicationType" +
					"$ProductNavigationControlMenu";

	}

}