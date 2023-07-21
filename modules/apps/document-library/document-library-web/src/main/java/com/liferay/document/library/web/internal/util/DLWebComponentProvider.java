/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.util;

import com.liferay.document.library.display.context.DLDisplayContextProvider;
import com.liferay.document.library.web.internal.display.context.DLAdminDisplayContextProvider;
import com.liferay.document.library.web.internal.display.context.IGDisplayContextProvider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = {})
public class DLWebComponentProvider {

	public static DLWebComponentProvider getDLWebComponentProvider() {
		return _dlWebComponentProvider;
	}

	public DLAdminDisplayContextProvider getDLAdminDisplayContextProvider() {
		return _dlAdminDisplayContextProvider;
	}

	public DLDisplayContextProvider getDLDisplayContextProvider() {
		return _dlDisplayContextProvider;
	}

	public IGDisplayContextProvider getIGDisplayContextProvider() {
		return _igDisplayContextProvider;
	}

	@Activate
	protected void activate() {
		_dlWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_dlWebComponentProvider = null;
	}

	@Reference(unbind = "-")
	protected void setDLDisplayContextProvider(
		DLDisplayContextProvider dlDisplayContextProvider) {

		_dlDisplayContextProvider = dlDisplayContextProvider;
	}

	@Reference(unbind = "-")
	protected void setIGDisplayContextProvider(
		IGDisplayContextProvider igDisplayContextProvider) {

		_igDisplayContextProvider = igDisplayContextProvider;
	}

	private static DLWebComponentProvider _dlWebComponentProvider;

	@Reference
	private DLAdminDisplayContextProvider _dlAdminDisplayContextProvider;

	private DLDisplayContextProvider _dlDisplayContextProvider;
	private IGDisplayContextProvider _igDisplayContextProvider;

}