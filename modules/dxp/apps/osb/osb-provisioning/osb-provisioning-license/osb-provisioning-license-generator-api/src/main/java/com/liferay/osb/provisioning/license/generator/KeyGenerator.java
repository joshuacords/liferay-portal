/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.generator;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public interface KeyGenerator {

	public Properties decryptServerId(byte[] bytes) throws Exception;

	public String generate(Map<String, String> properties);

	public String generate(
		String accountName, String licenseEntryName, String licenseEntryType,
		int licenseVersion, String productName, String productId,
		String productVersionLabel, String owner, int maxClusterNodes,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, String sizing, String description, String hostName,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate);

	public Map<String, String> getProperties(
		String accountName, String licenseEntryName, String licenseEntryType,
		int licenseVersion, String productName, String productId,
		String productVersionLabel, String owner, int maxClusterNodes,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, String sizing, String description, String hostNames,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate);

	public String getServerId(
		String hostName, String ipAddresses, String macAddresses);

}