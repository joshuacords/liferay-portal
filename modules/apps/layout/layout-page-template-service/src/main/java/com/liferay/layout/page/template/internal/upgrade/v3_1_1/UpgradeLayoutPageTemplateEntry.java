/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.upgrade.v3_1_1;

import com.liferay.layout.page.template.internal.validator.LayoutPageTemplateEntryValidator;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Mariano Álvaro Sáiz
 */
public class UpgradeLayoutPageTemplateEntry extends UpgradeProcess {

	public UpgradeLayoutPageTemplateEntry(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {

		_layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeLayoutPageTemplateEntryName();
	}

	private String _generateValidString(String value) {
		StringBundler sb = new StringBundler(value.length());

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);

			if (LayoutPageTemplateEntryValidator.isBlacklistedChar(c)) {
				sb.append(CharPool.DASH);
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	private String _getUniqueColumnValue(String value, String column) {
		Set<String> uniqueValues = _columnUniqueValues.get(column);

		int maxLength = ModelHintsUtil.getMaxLength(
			UpgradeLayoutPageTemplateEntry.class.getName(), column);

		String currentValue = value;

		for (int i = 1;; i++) {
			if (!uniqueValues.contains(currentValue)) {
				break;
			}

			String suffix = StringPool.DASH + i;

			String prefix = value.substring(
				0, Math.min(maxLength - suffix.length(), value.length()));

			currentValue = prefix + suffix;
		}

		uniqueValues.remove(value);
		uniqueValues.add(currentValue);

		return currentValue;
	}

	private void _loadDistinctNames() throws Exception {
		Set<String> names = _columnUniqueValues.get("name");

		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select distinct name from LayoutPageTemplateEntry")) {

			while (rs.next()) {
				names.add(rs.getString("name"));
			}
		}
	}

	private void _updateLayoutPrototypeName(
			long layoutPrototypeId, String oldName, String newName)
		throws PortalException {

		LayoutPrototype layoutPrototype =
			_layoutPrototypeLocalService.getLayoutPrototype(layoutPrototypeId);

		Map<Locale, String> nameMap = layoutPrototype.getNameMap();

		Locale locale = LocaleUtil.fromLanguageId(
			layoutPrototype.getDefaultLanguageId());

		String defaultName = nameMap.get(locale);

		if (!StringUtil.equals(defaultName, oldName)) {
			return;
		}

		nameMap.put(
			locale, StringUtil.replaceFirst(defaultName, oldName, newName));

		_layoutPrototypeLocalService.updateLayoutPrototype(
			layoutPrototypeId, nameMap, layoutPrototype.getDescriptionMap(),
			layoutPrototype.isActive(), new ServiceContext());
	}

	private void _upgradeLayoutPageTemplateEntryName() throws Exception {
		_loadDistinctNames();

		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select layoutPageTemplateEntryId, layoutPrototypeId, name " +
					"from LayoutPageTemplateEntry");
			PreparedStatement ps = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update LayoutPageTemplateEntry set name = ? where " +
						"layoutPageTemplateEntryId = ?"))) {

			while (rs.next()) {
				String name = rs.getString("name");

				if (LayoutPageTemplateEntryValidator.isValidName(name)) {
					continue;
				}

				long layoutPageTemplateEntryId = rs.getLong(
					"layoutPageTemplateEntryId");

				String newName = _generateValidString(name);

				ps.setString(1, _getUniqueColumnValue(newName, "name"));

				ps.setLong(2, layoutPageTemplateEntryId);

				long layoutPrototypeId = rs.getLong("layoutPrototypeId");

				_updateLayoutPrototypeName(layoutPrototypeId, name, newName);

				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

	private final Map<String, Set<String>> _columnUniqueValues =
		HashMapBuilder.<String, Set<String>>put(
			"layoutPageTemplateEntryKey", new HashSet<String>()
		).put(
			"name", new HashSet<String>()
		).build();
	private final LayoutPrototypeLocalService _layoutPrototypeLocalService;

}