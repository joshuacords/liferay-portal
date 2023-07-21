/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.internal.messaging;

import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.Date;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseEntitlementsMessageListener
	extends BaseMessageListener {

	protected void addEntitlements(
		long companyId, long entitlementDefinitionId, long classNameId,
		String name, String definition) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Adding entitlements for " + name);
			}

			_addEntitlements(
				companyId, entitlementDefinitionId, classNameId, definition);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Finished in " + stopWatch.getTime() + "ms");
		}
	}

	protected void processEntitlementDefinition(
		long companyId, long entitlementDefinitionId, long classNameId,
		String name, String definition) {

		addEntitlements(
			companyId, entitlementDefinitionId, classNameId, name, definition);

		removeEntitlements(entitlementDefinitionId, name, definition);
	}

	protected void removeEntitlements(
		long entitlementDefinitionId, String name, String definition) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Removing entitlements for " + name);
			}

			_removeEntitlements(entitlementDefinitionId, definition);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Finished in " + stopWatch.getTime() + "ms");
		}
	}

	@Reference
	protected EntitlementDefinitionLocalService
		entitlementDefinitionLocalService;

	@Reference
	protected EntitlementLocalService entitlementLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	protected ModuleServiceLifecycle moduleServiceLifecycle;

	@Reference
	protected Portal portal;

	@Reference
	protected UserLocalService userLocalService;

	private void _addEntitlements(
			long companyId, long entitlementDefinitionId, long classNameId,
			String definition)
		throws Exception {

		int count = 0;

		long defaultUserId = userLocalService.getDefaultUserId(companyId);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataAccess.getConnection();

			String columnName = null;

			if (classNameId == portal.getClassNameId(Account.class)) {
				columnName = "Koroneiki_Account.accountId";
			}
			else {
				columnName = "Koroneiki_Contact.contactId";
			}

			preparedStatement = connection.prepareStatement(
				_getAddSql(definition, columnName));

			preparedStatement.setLong(1, entitlementDefinitionId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long classPK = resultSet.getLong(columnName);

				entitlementLocalService.addEntitlement(
					defaultUserId, entitlementDefinitionId, classNameId,
					classPK);

				count++;
			}
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug("Added " + count + " entitlements");
			}

			DataAccess.cleanUp(connection, preparedStatement, resultSet);
		}
	}

	private String _getAddSql(String definition, String columnName) {
		StringBundler sb = new StringBundler(6);

		sb.append(definition);

		if (StringUtil.containsIgnoreCase(
				definition, "where", StringPool.BLANK)) {

			sb.append(" and ");
		}
		else {
			sb.append(" where ");
		}

		sb.append(columnName);
		sb.append(" not in (select Koroneiki_Entitlement.classPK from ");
		sb.append("Koroneiki_Entitlement where ");
		sb.append("Koroneiki_Entitlement.entitlementDefinitionId = ?)");

		Timestamp now = CalendarUtil.getTimestamp(new Date());

		return StringUtil.replace(sb.toString(), "[$NOW$]", now.toString());
	}

	private String _getRemoveSql(String definition) {
		StringBundler sb = new StringBundler(6);

		sb.append("select Koroneiki_Entitlement.entitlementId from ");
		sb.append("Koroneiki_Entitlement where ");
		sb.append("(Koroneiki_Entitlement.entitlementDefinitionId = ?) and ");
		sb.append("(Koroneiki_Entitlement.classPK not in (");
		sb.append(definition);
		sb.append("))");

		Timestamp now = CalendarUtil.getTimestamp(new Date());

		return StringUtil.replace(sb.toString(), "[$NOW$]", now.toString());
	}

	private void _removeEntitlements(
			long entitlementDefinitionId, String definition)
		throws Exception {

		int count = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataAccess.getConnection();

			preparedStatement = connection.prepareStatement(
				_getRemoveSql(definition));

			preparedStatement.setLong(1, entitlementDefinitionId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long entitlementId = resultSet.getLong(
					"Koroneiki_Entitlement.entitlementId");

				entitlementLocalService.deleteEntitlement(entitlementId);

				count++;
			}
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug("Removed " + count + " entitlements");
			}

			DataAccess.cleanUp(connection, preparedStatement, resultSet);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseEntitlementsMessageListener.class);

}