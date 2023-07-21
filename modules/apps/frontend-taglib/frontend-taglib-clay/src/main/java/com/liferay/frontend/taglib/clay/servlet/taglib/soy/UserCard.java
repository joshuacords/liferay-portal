/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

/**
 * @author Eudaldo Alonso
 */
public interface UserCard extends BaseClayCard {

	public default String getImageAlt() {
		return null;
	}

	public default String getImageSrc() {
		return null;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public default String getInitials() {
		return null;
	}

	public String getName();

	public default String getSubtitle() {
		return null;
	}

	public default String getUserColor() {
		return null;
	}

}