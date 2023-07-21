/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.search.Hits;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class SearchSpeedTag<R> extends SearchFormTag<R> {

	public Hits getHits() {
		return _hits;
	}

	public void setHits(Hits hits) {
		_hits = hits;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_hits = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute("liferay-ui:search:hits", _hits);
	}

	private static final String _PAGE = "/html/taglib/ui/search_speed/page.jsp";

	private Hits _hits;

}