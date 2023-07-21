/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.impl.GroupImpl;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceChannelImpl extends CommerceChannelBaseImpl {

	public CommerceChannelImpl() {
	}

	@Override
	public Group getGroup() {
		if (getCommerceChannelId() > 0) {
			try {
				return CommerceChannelLocalServiceUtil.getCommerceChannelGroup(
					getCommerceChannelId());
			}
			catch (Exception e) {
				_log.error("Unable to get commerce channel group", e);
			}
		}

		return new GroupImpl();
	}

	@Override
	public long getGroupId() {
		Group group = getGroup();

		return group.getGroupId();
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		super.setTypeSettings(typeSettings);

		_typeSettingsProperties = null;
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties();
		}

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceChannelImpl.class);

	private UnicodeProperties _typeSettingsProperties;

}