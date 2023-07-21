/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.util;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String BIND_SASL_HOSTNAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.BIND_SASL_HOSTNAME));

	public static final String[] EMAIL_ADDRESSES_WHITELIST =
		PortletProps.getArray(PortletPropsKeys.EMAIL_ADDRESSES_WHITELIST);

	public static final String[] HOSTS_ALLOWED = PortletProps.getArray(
		PortletPropsKeys.HOSTS_ALLOWED);

	public static final int LDAP_BIND_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.LDAP_BIND_PORT));

	public static final int LDAPS_BIND_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.LDAPS_BIND_PORT));

	public static final String POSIX_GROUP_ID = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.POSIX_GROUP_ID));

	public static final String[] SAMBA_DOMAIN_NAMES = PortletProps.getArray(
		PortletPropsKeys.SAMBA_DOMAIN_NAMES);

	public static final String[] SAMBA_HOSTS_ALLOWED = PortletProps.getArray(
		PortletPropsKeys.SAMBA_HOSTS_ALLOWED);

	public static final int SEARCH_MAX_SIZE = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SEARCH_MAX_SIZE));

	public static final int SEARCH_MAX_TIME = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SEARCH_MAX_TIME));

	public static final String SSL_KEYSTORE_FILE_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SSL_KEYSTORE_FILE_NAME));

	public static final char[] SSL_KEYSTORE_PASSWORD;

	public static final String SSL_PROTOCOL = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SSL_PROTOCOL));

	static {
		String sslKeystorePassword = GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.SSL_KEYSTORE_PASSWORD));

		SSL_KEYSTORE_PASSWORD = sslKeystorePassword.toCharArray();
	}

}