/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.util.template;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class LayoutColumn {

	public static LayoutColumn of(
		Layout layout, UnsafeConsumer<LayoutColumn, Exception> unsafeConsumer) {

		LayoutColumn layoutColumn = new LayoutColumn(layout);

		try {
			unsafeConsumer.accept(layoutColumn);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		return layoutColumn;
	}

	public void addPortlets(String columnId) throws PortalException {
		List<String> portletIds = LayoutTypeSettingsInspectorUtil.getPortletIds(
			_layout.getTypeSettingsProperties(), columnId);

		for (String portletId : portletIds) {
			_addPortlet(portletId);
		}
	}

	public List<Long> getFragmentEntryLinkIds() {
		return _fragmentEntryLinkIds;
	}

	public int getSize() {
		return _size;
	}

	public void setSize(int size) {
		_size = size;
	}

	private LayoutColumn(Layout layout) {
		_layout = layout;
	}

	private void _addPortlet(String portletId) throws PortalException {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		FragmentEntryLink fragmentEntryLink =
			FragmentEntryLinkLocalServiceUtil.addFragmentEntryLink(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(), 0,
				0, PortalUtil.getClassNameId(Layout.class), _layout.getPlid(),
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK,
				JSONUtil.put(
					"instanceId", PortletIdCodec.decodeInstanceId(portletId)
				).put(
					"portletId", PortletIdCodec.decodePortletName(portletId)
				).toString(),
				StringPool.BLANK, 0, null, serviceContext);

		_fragmentEntryLinkIds.add(fragmentEntryLink.getFragmentEntryLinkId());
	}

	private final List<Long> _fragmentEntryLinkIds = new ArrayList<>();
	private final Layout _layout;
	private int _size = 12;

}