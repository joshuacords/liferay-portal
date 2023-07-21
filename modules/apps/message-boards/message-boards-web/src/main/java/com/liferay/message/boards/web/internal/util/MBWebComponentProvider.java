/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.util;

import com.liferay.message.boards.web.internal.display.context.MBDisplayContextProvider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = {})
public class MBWebComponentProvider {

	public static MBWebComponentProvider getMBWebComponentProvider() {
		return _mbWebComponentProvider;
	}

	public MBDisplayContextProvider getMBDisplayContextProvider() {
		return _mbDisplayContextProvider;
	}

	@Reference(unbind = "-")
	public void setMBDisplayContextProvider(
		MBDisplayContextProvider mbDisplayContextProvider) {

		_mbDisplayContextProvider = mbDisplayContextProvider;
	}

	@Activate
	protected void activate() {
		_mbWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_mbWebComponentProvider = null;
	}

	private static MBWebComponentProvider _mbWebComponentProvider;

	private MBDisplayContextProvider _mbDisplayContextProvider;

}