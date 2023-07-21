/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.model;

import com.liferay.commerce.frontend.model.ImageField;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductLink {

	public ProductLink(
		long cpDefinitionLinkId, ImageField image, String name, String type,
		double order, String createDate) {

		_cpDefinitionLinkId = cpDefinitionLinkId;
		_image = image;
		_name = name;
		_type = type;
		_order = order;
		_createDate = createDate;
	}

	public long getCPDefinitionLinkId() {
		return _cpDefinitionLinkId;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public ImageField getImage() {
		return _image;
	}

	public String getName() {
		return _name;
	}

	public double getOrder() {
		return _order;
	}

	public String getType() {
		return _type;
	}

	private final long _cpDefinitionLinkId;
	private final String _createDate;
	private final ImageField _image;
	private final String _name;
	private final double _order;
	private final String _type;

}