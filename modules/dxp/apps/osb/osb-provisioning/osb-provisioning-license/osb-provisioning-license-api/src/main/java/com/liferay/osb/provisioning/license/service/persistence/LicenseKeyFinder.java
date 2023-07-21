/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface LicenseKeyFinder {

	public int countByKeywords(
		String keywords, java.util.LinkedHashMap<String, Object> params);

	public int countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
		String createUserUuid, java.util.Date createDateGT,
		java.util.Date createDateLT, String modifiedUserUuid,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String accountName,
		java.util.Date startDateGT, java.util.Date startDateLT,
		long[] licenseEntryIds, String[] productKeys, String productName,
		String productId, String[] productVersions, long[] clusterIds,
		String owner, String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key,
		java.util.Date expirationDateGT, java.util.Date expirationDateLT,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			String createUserUuid, java.util.Date createDateGT,
			java.util.Date createDateLT, String modifiedUserUuid,
			java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, long[] clusterIds,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc);

}