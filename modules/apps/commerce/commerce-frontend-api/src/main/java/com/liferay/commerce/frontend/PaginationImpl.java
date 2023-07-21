/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend;

/**
 * @author Marco Leo
 */
public class PaginationImpl implements Pagination {

	public PaginationImpl(int itemsPerPage, int pageNumber) {
		_itemsPerPage = itemsPerPage;
		_pageNumber = pageNumber;
	}

	@Override
	public int getEndPosition() {
		return _pageNumber * _itemsPerPage;
	}

	@Override
	public int getItemsPerPage() {
		return _itemsPerPage;
	}

	@Override
	public int getPageNumber() {
		return _pageNumber;
	}

	@Override
	public int getStartPosition() {
		return (_pageNumber - 1) * _itemsPerPage;
	}

	private final int _itemsPerPage;
	private final int _pageNumber;

}