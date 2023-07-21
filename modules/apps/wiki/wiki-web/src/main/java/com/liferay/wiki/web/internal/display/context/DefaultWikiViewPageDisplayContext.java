/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.display.context;

import com.liferay.wiki.display.context.WikiViewPageDisplayContext;
import com.liferay.wiki.model.WikiPage;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public class DefaultWikiViewPageDisplayContext
	implements WikiViewPageDisplayContext {

	public DefaultWikiViewPageDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, WikiPage wikiPage) {
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	private static final UUID _UUID = UUID.fromString(
		"6666DA6E-4647-472D-B66A-DF4B9037D5D4");

}