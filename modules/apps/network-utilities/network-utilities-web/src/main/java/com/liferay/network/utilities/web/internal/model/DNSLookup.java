/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.network.utilities.web.internal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class DNSLookup implements Serializable {

	public DNSLookup() {
	}

	public DNSLookup(String domain, String results) {
		_domain = domain;
		_results = results;
	}

	public String getDomain() {
		return _domain;
	}

	public String getResults() {
		return _results;
	}

	public void setDomain(String domain) {
		_domain = domain;
	}

	public void setResults(String results) {
		_results = results;
	}

	private String _domain;
	private String _results;

}