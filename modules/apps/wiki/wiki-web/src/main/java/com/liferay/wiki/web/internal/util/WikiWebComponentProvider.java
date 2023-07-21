/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.util;

import com.liferay.wiki.configuration.WikiGroupServiceConfiguration;
import com.liferay.wiki.web.internal.display.context.WikiDisplayContextProvider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = {})
public class WikiWebComponentProvider {

	public static WikiWebComponentProvider getWikiWebComponentProvider() {
		return _wikiWebComponentProvider;
	}

	public WikiDisplayContextProvider getWikiDisplayContextProvider() {
		return _wikiDisplayContextProvider;
	}

	public WikiGroupServiceConfiguration getWikiGroupServiceConfiguration() {
		return _wikiGroupServiceConfiguration;
	}

	@Reference(unbind = "-")
	public void setWikiDisplayContextProvider(
		WikiDisplayContextProvider wikiDisplayContextProvider) {

		_wikiDisplayContextProvider = wikiDisplayContextProvider;
	}

	@Activate
	protected void activate() {
		_wikiWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_wikiWebComponentProvider = null;
	}

	@Reference
	protected void setWikiGroupServiceConfiguration(
		WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = wikiGroupServiceConfiguration;
	}

	protected void unsetWikiGroupServiceConfiguration(
		WikiGroupServiceConfiguration wikiGroupServiceConfiguration) {

		_wikiGroupServiceConfiguration = null;
	}

	private static WikiWebComponentProvider _wikiWebComponentProvider;

	private WikiDisplayContextProvider _wikiDisplayContextProvider;
	private WikiGroupServiceConfiguration _wikiGroupServiceConfiguration;

}