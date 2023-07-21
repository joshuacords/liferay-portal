/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.connector.internal.http;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * @author Kyle Bischof
 */
public class ZendeskHttpDelete extends HttpEntityEnclosingRequestBase {

	public static final String METHOD_NAME = "DELETE";

	public ZendeskHttpDelete() {
	}

	public ZendeskHttpDelete(final String uri) {
		setURI(URI.create(uri));
	}

	public ZendeskHttpDelete(final URI uri) {
		setURI(uri);
	}

	public String getMethod() {
		return METHOD_NAME;
	}

}