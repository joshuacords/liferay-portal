/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.sites.kernel.util.Sites;

import java.util.Date;

/**
 * @author Raymond Augé
 */
public class LayoutSetPrototypeLayoutModelListener
	extends BaseModelListener<Layout> {

	@Override
	public void onAfterCreate(Layout layout) {
		updateLayoutSetPrototype(layout, layout.getModifiedDate());
	}

	@Override
	public void onAfterRemove(Layout layout) {
		updateLayoutSetPrototype(layout, new Date());
	}

	@Override
	public void onAfterUpdate(Layout layout) {
		updateLayoutSetPrototype(layout, layout.getModifiedDate());
	}

	protected void updateLayoutSetPrototype(Layout layout, Date modifiedDate) {
		if (layout == null) {
			return;
		}

		Group group = layout.getGroup();

		if ((group == null) || !group.isLayoutSetPrototype()) {
			return;
		}

		try {
			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
					group.getClassPK());

			layoutSetPrototype.setModifiedDate(modifiedDate);

			LayoutSetPrototypeLocalServiceUtil.updateLayoutSetPrototype(
				layoutSetPrototype);

			LayoutSet layoutSet = layoutSetPrototype.getLayoutSet();

			layoutSet.setModifiedDate(modifiedDate);

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			settingsProperties.remove(Sites.MERGE_FAIL_COUNT);

			LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutSetPrototypeLayoutModelListener.class);

}