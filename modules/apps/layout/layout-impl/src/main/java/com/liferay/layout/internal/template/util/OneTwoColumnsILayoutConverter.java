/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.template.util;

import com.liferay.layout.util.template.LayoutConverter;
import com.liferay.layout.util.template.LayoutData;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "layout.template.id=1_2_columns_i",
	service = LayoutConverter.class
)
public class OneTwoColumnsILayoutConverter implements LayoutConverter {

	@Override
	public LayoutData convert(Layout layout) {
		return LayoutData.of(
			layout,
			layoutRow -> layoutRow.addLayoutColumns(
				layoutColumn -> layoutColumn.addPortlets(
					LayoutTypePortletConstants.COLUMN_PREFIX + 1)),
			layoutRow -> layoutRow.addLayoutColumns(
				layoutColumn -> {
					layoutColumn.addPortlets(
						LayoutTypePortletConstants.COLUMN_PREFIX + 2);
					layoutColumn.setSize(4);
				},
				layoutColumn -> {
					layoutColumn.addPortlets(
						LayoutTypePortletConstants.COLUMN_PREFIX + 3);
					layoutColumn.setSize(8);
				}));
	}

}