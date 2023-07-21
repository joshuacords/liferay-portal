/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Andrea Di Giorgi
 */
public class Dimensions {

	public Dimensions(double width, double height, double depth) {
		_width = width;
		_height = height;
		_depth = depth;
	}

	public double getDepth() {
		return _depth;
	}

	public double getHeight() {
		return _height;
	}

	public double getWidth() {
		return _width;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{width=");
		sb.append(_width);
		sb.append(", height=");
		sb.append(_height);
		sb.append(", depth=");
		sb.append(_depth);
		sb.append("}");

		return sb.toString();
	}

	private final double _depth;
	private final double _height;
	private final double _width;

}