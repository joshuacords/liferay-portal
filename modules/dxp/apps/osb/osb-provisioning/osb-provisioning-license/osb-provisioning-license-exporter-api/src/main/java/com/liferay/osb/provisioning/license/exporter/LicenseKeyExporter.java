/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.exporter;

import java.util.Date;

/**
 * @author Amos Fong
 */
public interface LicenseKeyExporter {

	public String aggregateXMLs(String[] xmls) throws Exception;

	public String getFileName(
		String productName, String productVersion, String name);

	public String getFileName(String[] productNames, String[] names);

	public String toEncodedLicenseFile(String serverId, String key);

	public String toLI(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, String productVersion, String owner,
			int maxClusterNodes, int maxServers, int maxHttpSessions,
			long maxConcurrentUsers, long maxUsers, String sizing,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, Date startDate,
			Date expirationDate)
		throws Exception;

	public String toXML(
			String accountName, String licenseEntryName, String licenseType,
			int licenseVersion, String productName, String productId,
			String productVersion, String owner, int maxClusterNodes,
			int maxServers, int maxHttpSessions, long maxConcurrentUsers,
			long maxUsers, String sizing, String description,
			String[] hostNames, String[] ipAddresses, String[] macAddresses,
			String[] serverIds, Date startDate, Date expirationDate,
			Date createDate)
		throws Exception;

	public String toXML(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, String productVersion, String owner,
			int maxClusterNodes, int maxServers, int maxHttpSessions,
			long maxConcurrentUsers, long maxUsers, String sizing,
			String description, String hostNames, String ipAddresses,
			String macAddresses, String serverIds, Date startDate,
			Date expirationDate, Date createDate)
		throws Exception;

}