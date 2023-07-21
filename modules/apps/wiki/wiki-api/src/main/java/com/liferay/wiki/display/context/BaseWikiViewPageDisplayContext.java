/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.display.context;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public abstract class BaseWikiViewPageDisplayContext
	extends BaseWikiDisplayContext<WikiViewPageDisplayContext>
	implements WikiViewPageDisplayContext {

	public BaseWikiViewPageDisplayContext(
		UUID uuid, WikiViewPageDisplayContext parentDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		super(
			uuid, parentDisplayContext, httpServletRequest,
			httpServletResponse);
	}

}