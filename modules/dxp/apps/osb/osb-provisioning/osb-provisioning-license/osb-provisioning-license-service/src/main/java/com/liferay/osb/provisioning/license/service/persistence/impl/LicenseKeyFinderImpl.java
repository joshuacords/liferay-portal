/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence.impl;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.persistence.LicenseKeyFinder;
import com.liferay.osb.provisioning.license.service.persistence.LicenseKeyUtil;
import com.liferay.osb.provisioning.license.util.OSBCustomSQL;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = LicenseKeyFinder.class)
public class LicenseKeyFinderImpl
	extends LicenseKeyFinderBaseImpl implements LicenseKeyFinder {

	public static final String
		COUNT_BY_U_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A =
			LicenseKeyFinder.class.getName() +
				".countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A";

	public static final String
		FIND_BY_U_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A =
			LicenseKeyFinder.class.getName() +
				".findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A";

	public static final String JOIN_BY_ACTIVE =
		LicenseKeyFinder.class.getName() + ".joinByActive";

	public int countByKeywords(
		String keywords, LinkedHashMap<String, Object> params) {

		String[] accountNames = null;
		String[] productNames = null;
		String[] productIds = null;
		String[] owners = null;
		String[] descriptions = null;
		String[] hostNames = null;
		String[] ipAddresses = null;
		String[] macAddresses = null;
		String[] serverIds = null;
		String[] keys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			accountNames = _customSQL.keywords(keywords);
			productNames = _customSQL.keywords(keywords);
			productIds = _customSQL.keywords(keywords);
			owners = _customSQL.keywords(keywords);
			descriptions = _customSQL.keywords(keywords);
			hostNames = _customSQL.keywords(keywords);
			ipAddresses = _customSQL.keywords(keywords);
			macAddresses = _customSQL.keywords(keywords);
			serverIds = _customSQL.keywords(keywords);
			keys = _customSQL.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			null, null, null, null, null, null, keywords, keywords,
			accountNames, null, null, new long[0], new String[0], productNames,
			productIds, new String[0], new long[0], owners, descriptions,
			hostNames, ipAddresses, macAddresses, serverIds, keys, null, null,
			params, andOperator);
	}

	public int countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
		String createUserUuid, Date createDateGT, Date createDateLT,
		String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String accountName,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		String[] productVersions, long[] clusterIds, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		String[] accountNames = _osbCustomSQL.keywords(accountName);
		String[] productNames = _customSQL.keywords(productName);
		String[] owners = _customSQL.keywords(owner);
		String[] descriptions = _customSQL.keywords(description);
		String[] hostNames = _customSQL.keywords(hostName);
		String[] ipAddresses = _customSQL.keywords(ipAddress);
		String[] macAddresses = _customSQL.keywords(macAddress);
		String[] serverIds = _customSQL.keywords(serverId);
		String[] keys = _customSQL.keywords(key);

		return countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountNames, createDateGT, createDateLT, licenseEntryIds,
			productKeys, productNames, new String[] {productId},
			productVersions, clusterIds, owners, descriptions, hostNames,
			ipAddresses, macAddresses, serverIds, keys, expirationDateGT,
			expirationDateLT, params, andOperator);
	}

	public List<LicenseKey>
		findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String productName, String productId,
			String[] productVersions, long[] clusterIds, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc) {

		String[] accountNames = _osbCustomSQL.keywords(accountName);
		String[] productNames = _customSQL.keywords(productName);
		String[] owners = _customSQL.keywords(owner);
		String[] descriptions = _customSQL.keywords(description);
		String[] hostNames = _customSQL.keywords(hostName);
		String[] ipAddresses = _customSQL.keywords(ipAddress);
		String[] macAddresses = _customSQL.keywords(macAddress);
		String[] serverIds = _customSQL.keywords(serverId);
		String[] keys = _customSQL.keywords(key);

		return findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountNames, createDateGT, createDateLT, licenseEntryIds,
			productKeys, productNames, new String[] {productId},
			productVersions, clusterIds, owners, descriptions, hostNames,
			ipAddresses, macAddresses, serverIds, keys, expirationDateGT,
			expirationDateLT, params, andOperator, start, end, obc);
	}

	protected int countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
		String createUserUuid, Date createDateGT, Date createDateLT,
		String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String[] accountNames,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		String[] productKeys, String[] productNames, String[] productIds,
		String[] productVersions, long[] clusterIds, String[] owners,
		String[] descriptions, String[] hostNames, String[] ipAddresses,
		String[] macAddresses, String[] serverIds, String[] keys,
		Date expirationDateGT, Date expirationDateLT,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		accountNames = _customSQL.keywords(accountNames);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		productNames = _customSQL.keywords(productNames);
		productIds = _customSQL.keywords(productIds);
		owners = _customSQL.keywords(owners);
		descriptions = _customSQL.keywords(descriptions);
		hostNames = _customSQL.keywords(hostNames);
		ipAddresses = _customSQL.keywords(ipAddresses);
		macAddresses = _customSQL.keywords(macAddresses);
		serverIds = _customSQL.keywords(serverIds);
		keys = _customSQL.keywords(keys);

		Timestamp expirationDateGT_TS = CalendarUtil.getTimestamp(
			expirationDateGT);
		Timestamp expirationDateLT_TS = CalendarUtil.getTimestamp(
			expirationDateLT);

		String sql = _customSQL.get(
			getClass(),
			COUNT_BY_U_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A);

		sql = replaceSQL(
			sql, createUserUuid, modifiedUserUuid, accountNames,
			licenseEntryIds, productKeys, productNames, productIds,
			productVersions, clusterIds, owners, descriptions, hostNames,
			ipAddresses, macAddresses, serverIds, keys, params, andOperator);

		String selectSql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		sql = StringUtil.replace(selectSql, "[$SELECT_SQL$]", sql);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params, createUserUuid, createDateGT_TS, createDateLT_TS,
				modifiedUserUuid, modifiedDateGT_TS, modifiedDateLT_TS,
				accountKey, productPurchaseKey, accountNames, startDateGT_TS,
				startDateLT_TS, licenseEntryIds, productKeys, productNames,
				productIds, productVersions, clusterIds, owners, descriptions,
				hostNames, ipAddresses, macAddresses, serverIds, keys,
				expirationDateGT_TS, expirationDateLT_TS);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<LicenseKey>
		findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String[] accountNames,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String[] productNames, String[] productIds,
			String[] productVersions, long[] clusterIds, String[] owners,
			String[] descriptions, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, String[] keys,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc) {

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		accountNames = _customSQL.keywords(accountNames);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		productNames = _customSQL.keywords(productNames);
		productIds = _customSQL.keywords(productIds);
		owners = _customSQL.keywords(owners);
		descriptions = _customSQL.keywords(descriptions);
		hostNames = _customSQL.keywords(hostNames);
		ipAddresses = _customSQL.keywords(ipAddresses);
		macAddresses = _customSQL.keywords(macAddresses);
		serverIds = _customSQL.keywords(serverIds);
		keys = _customSQL.keywords(keys);

		Timestamp expirationDateGT_TS = CalendarUtil.getTimestamp(
			expirationDateGT);
		Timestamp expirationDateLT_TS = CalendarUtil.getTimestamp(
			expirationDateLT);

		String sql = _customSQL.get(
			getClass(),
			FIND_BY_U_C_M_M_AK_PPK_A_S_L_P_P_P_P_CI_O_D_H_I_M_S_E_A);

		sql = replaceSQL(
			sql, createUserUuid, modifiedUserUuid, accountNames,
			licenseEntryIds, productKeys, productNames, productIds,
			productVersions, clusterIds, owners, descriptions, hostNames,
			ipAddresses, macAddresses, serverIds, keys, params, andOperator);

		sql = _customSQL.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("licenseKeyId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params, createUserUuid, createDateGT_TS, createDateLT_TS,
				modifiedUserUuid, modifiedDateGT_TS, modifiedDateLT_TS,
				accountKey, productPurchaseKey, accountNames, startDateGT_TS,
				startDateLT_TS, licenseEntryIds, productKeys, productNames,
				productIds, productVersions, clusterIds, owners, descriptions,
				hostNames, ipAddresses, macAddresses, serverIds, keys,
				expirationDateGT_TS, expirationDateLT_TS);

			List<Long> licenseKeyIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<LicenseKey> licenseKeys = new ArrayList<>(
				licenseKeyIds.size());

			for (Long licenseKeyId : licenseKeyIds) {
				LicenseKey licenseKey = LicenseKeyUtil.findByPrimaryKey(
					licenseKeyId);

				licenseKeys.add(licenseKey);
			}

			return licenseKeys;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getJoin(entry.getKey(), value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("active")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACTIVE);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(entry.getKey(), value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("active")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACTIVE);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				String substring = join.substring(pos + 5);

				join = substring.concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected String replaceSQL(
		String sql, String createUserUuid, String modifiedUserUuid,
		String[] accountNames, long[] licenseEntryIds, String[] productKeys,
		String[] productNames, String[] productIds, String[] productVersions,
		long[] clusterIds, String[] owners, String[] descriptions,
		String[] hostNames, String[] ipAddresses, String[] macAddresses,
		String[] serverIds, String[] keys, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		if (Validator.isNull(createUserUuid)) {
			sql = StringUtil.removeSubstring(sql, _USER_ID_SQL);
		}

		if (Validator.isNull(modifiedUserUuid)) {
			sql = StringUtil.removeSubstring(sql, _MODIFIED_USER_ID_SQL);
		}

		if (ArrayUtil.isEmpty(productKeys)) {
			sql = StringUtil.removeSubstring(sql, _PRODUCT_KEY_SQL);
		}
		else {
			sql = _customSQL.replaceKeywords(
				sql, "Provisioning_LicenseKey.productKey", StringPool.EQUAL,
				false, productKeys);
		}

		if (ArrayUtil.isEmpty(productVersions)) {
			sql = StringUtil.removeSubstring(sql, _PRODUCT_VERSION_SQL);
		}
		else {
			sql = _customSQL.replaceKeywords(
				sql, "Provisioning_LicenseKey.productVersion", StringPool.EQUAL,
				false, productVersions);
		}

		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.accountName)", StringPool.LIKE,
			true, accountNames);
		sql = _customSQL.replaceKeywords(
			sql, "Provisioning_LicenseKey.licenseEntryId", false,
			licenseEntryIds);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.productName)", StringPool.LIKE,
			false, productNames);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.productId)", StringPool.EQUAL,
			false, productIds);
		sql = _customSQL.replaceKeywords(
			sql, "Provisioning_LicenseKey.clusterId", false, clusterIds);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.owner)", StringPool.LIKE, false,
			owners);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.description)", StringPool.LIKE,
			false, descriptions);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.hostName)", StringPool.LIKE,
			false, hostNames);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.ipAddresses)", StringPool.LIKE,
			false, ipAddresses);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.macAddresses)", StringPool.LIKE,
			false, macAddresses);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.serverId)", StringPool.LIKE,
			false, serverIds);
		sql = _customSQL.replaceKeywords(
			sql, "LOWER(Provisioning_LicenseKey.key_)", StringPool.LIKE, false,
			keys);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = _customSQL.replaceAndOperator(sql, andOperator);

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (value instanceof Boolean) {
				Boolean valueBoolean = (Boolean)value;

				if (valueBoolean != null) {
					qPos.add(valueBoolean);
				}
			}
			else if (value instanceof Integer) {
				Integer valueInteger = (Integer)value;

				if (valueInteger != null) {
					qPos.add(valueInteger);
				}
			}
			else if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (Long valueLong : valueArray) {
					if (Validator.isNotNull(valueLong)) {
						qPos.add(valueLong);
					}
				}
			}
			else if (value instanceof String) {
				String valueString = (String)value;

				if (Validator.isNotNull(valueString)) {
					qPos.add(valueString);
				}
			}
			else if (value instanceof String[]) {
				String[] valueArray = (String[])value;

				for (String valueString : valueArray) {
					qPos.add(valueString);
				}
			}
		}
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params,
		String createUserUuid, Timestamp createDateGT, Timestamp createDateLT,
		String modifiedUserUuid, Timestamp modifiedDateGT,
		Timestamp modifiedDateLT, String accountKey, String productPurchaseKey,
		String[] accountNames, Timestamp startDateGT, Timestamp startDateLT,
		long[] licenseEntryIds, String[] productKeys, String[] productNames,
		String[] productIds, String[] productVersions, long[] clusterIds,
		String[] owners, String[] descriptions, String[] hostNames,
		String[] ipAddresses, String[] macAddresses, String[] serverIds,
		String[] keys, Timestamp expirationDateGT, Timestamp expirationDateLT) {

		setJoin(qPos, params);

		if (Validator.isNotNull(createUserUuid)) {
			qPos.add(createUserUuid);
		}

		qPos.add(createDateGT);
		qPos.add(createDateGT);
		qPos.add(createDateLT);
		qPos.add(createDateLT);

		if (Validator.isNotNull(modifiedUserUuid)) {
			qPos.add(modifiedUserUuid);
		}

		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateLT);
		qPos.add(modifiedDateLT);
		qPos.add(accountKey);
		qPos.add(accountKey);
		qPos.add(productPurchaseKey);
		qPos.add(productPurchaseKey);
		qPos.add(accountNames, 2);
		qPos.add(startDateGT);
		qPos.add(startDateGT);
		qPos.add(startDateLT);
		qPos.add(startDateLT);
		qPos.add(licenseEntryIds);
		qPos.add(productKeys, 2);
		qPos.add(productNames, 2);
		qPos.add(productIds, 2);
		qPos.add(productVersions, 2);
		qPos.add(clusterIds);
		qPos.add(owners, 2);
		qPos.add(descriptions, 2);
		qPos.add(hostNames, 2);
		qPos.add(ipAddresses, 2);
		qPos.add(macAddresses, 2);
		qPos.add(serverIds, 2);
		qPos.add(keys, 2);
		qPos.add(expirationDateGT);
		qPos.add(expirationDateGT);
		qPos.add(expirationDateLT);
		qPos.add(expirationDateLT);
	}

	private static final String _MODIFIED_USER_ID_SQL =
		"(Provisioning_LicenseKey.modifiedUserUuid = ?) [$AND_OR_CONNECTOR$]";

	private static final String _PRODUCT_KEY_SQL =
		"(Provisioning_LicenseKey.productKey = ? [$AND_OR_NULL_CHECK$]) " +
			"[$AND_OR_CONNECTOR$]";

	private static final String _PRODUCT_VERSION_SQL =
		"(Provisioning_LicenseKey.productVersion = ? [$AND_OR_NULL_CHECK$]) " +
			"[$AND_OR_CONNECTOR$]";

	private static final String _USER_ID_SQL =
		"(Provisioning_LicenseKey.userUuid = ?) [$AND_OR_CONNECTOR$]";

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private OSBCustomSQL _osbCustomSQL;

}