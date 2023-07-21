/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.catalog;

/**
 * @author Marco Leo
 */
public interface CPMedia {

	public String getDownloadUrl();

	public long getId();

	public String getThumbnailUrl();

	public String getTitle();

	public String getUrl();

	public String mimeType();

}