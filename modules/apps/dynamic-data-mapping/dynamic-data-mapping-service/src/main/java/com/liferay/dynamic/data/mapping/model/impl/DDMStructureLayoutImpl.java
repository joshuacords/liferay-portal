/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.cache.CacheField;

/**
 * @author Marcellus Tavares
 */
public class DDMStructureLayoutImpl extends DDMStructureLayoutBaseImpl {

	@Override
	public DDMFormLayout getDDMFormLayout() {
		if (_ddmFormLayout == null) {
			try {
				_ddmFormLayout =
					DDMStructureLayoutLocalServiceUtil.
						getStructureLayoutDDMFormLayout(this);
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				return new DDMFormLayout();
			}
		}

		return new DDMFormLayout(_ddmFormLayout);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStructureLayoutImpl.class);

	@CacheField(methodName = "DDMFormLayout", propagateToInterface = true)
	private DDMFormLayout _ddmFormLayout;

}