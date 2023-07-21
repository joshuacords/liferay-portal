/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Edward C. Han
 * @generated
 */
@ProviderType
public interface MDRRuleGroupFinder {

	public int countByKeywords(
		long groupId, String keywords,
		java.util.LinkedHashMap<String, Object> params);

	public int countByG_N(
		long groupId, String name,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public int countByG_N(
		long groupId, String[] names,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByKeywords(
			long groupId, String keywords,
			java.util.LinkedHashMap<String, Object> params, int start, int end);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByKeywords(
			long groupId, String keywords,
			java.util.LinkedHashMap<String, Object> params, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.mobile.device.rules.model.MDRRuleGroup> obc);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByG_N(
			long groupId, String name,
			java.util.LinkedHashMap<String, Object> params,
			boolean andOperator);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByG_N(
			long groupId, String name,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByG_N(
			long groupId, String[] names,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end);

	public java.util.List<com.liferay.mobile.device.rules.model.MDRRuleGroup>
		findByG_N(
			long groupId, String[] names,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.mobile.device.rules.model.MDRRuleGroup> obc);

}