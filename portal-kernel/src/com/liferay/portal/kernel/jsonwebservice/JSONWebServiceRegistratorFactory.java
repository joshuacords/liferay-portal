/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.jsonwebservice;

/**
 * @author Miguel Pastor
 */
public class JSONWebServiceRegistratorFactory {

	public JSONWebServiceRegistrator build(
		JSONWebServiceScannerStrategy jsonWebServiceScannerStrategy) {

		return _jsonWebServiceRegistratorBuilder.build(
			jsonWebServiceScannerStrategy);
	}

	public void setJSONWebServiceRegistratorBuilder(
		JSONWebServiceRegistratorBuilder jsonWebServiceRegistratorBuilder) {

		_jsonWebServiceRegistratorBuilder = jsonWebServiceRegistratorBuilder;
	}

	private JSONWebServiceRegistratorBuilder _jsonWebServiceRegistratorBuilder;

}