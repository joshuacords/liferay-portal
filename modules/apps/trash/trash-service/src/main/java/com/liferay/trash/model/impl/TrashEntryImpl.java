/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.model.impl;

import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.trash.model.TrashEntry;

/**
 * @author Zsolt Berentey
 */
public class TrashEntryImpl extends TrashEntryBaseImpl {

	@Override
	public TrashEntry getRootEntry() {
		return _rootEntry;
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettingsProperties == null) {
			return super.getTypeSettings();
		}

		return _typeSettingsProperties.toString();
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(super.getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public String getTypeSettingsProperty(String key) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key);
	}

	@Override
	public String getTypeSettingsProperty(String key, String defaultValue) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key, defaultValue);
	}

	@Override
	public boolean isTrashEntry(Class<?> clazz, long classPK) {
		if (clazz == null) {
			return false;
		}

		return isTrashEntry(clazz.getName(), classPK);
	}

	@Override
	public boolean isTrashEntry(String className, long classPK) {
		if (className.equals(getClassName()) && (classPK == getClassPK())) {
			return true;
		}

		return false;
	}

	@Override
	public void setRootEntry(TrashEntry rootEntry) {
		_rootEntry = rootEntry;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettingsProperties = null;

		super.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	private TrashEntry _rootEntry;
	private UnicodeProperties _typeSettingsProperties;

}