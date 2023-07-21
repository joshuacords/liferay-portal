/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.web.internal.portlet.configuration.icon;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.trash.model.TrashEntry;
import com.liferay.trash.web.internal.constants.TrashPortletKeys;
import com.liferay.trash.web.internal.display.context.TrashDisplayContext;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TrashPortletKeys.TRASH, "path=/view_content.jsp"
	},
	service = PortletConfigurationIcon.class
)
public class DeleteTrashPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "delete");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			TrashDisplayContext trashDisplayContext = new TrashDisplayContext(
				_portal.getHttpServletRequest(portletRequest),
				_portal.getLiferayPortletRequest(portletRequest),
				_portal.getLiferayPortletResponse(portletResponse));

			PortletURL deleteURL = _portal.getControlPanelPortletURL(
				portletRequest, TrashPortletKeys.TRASH,
				PortletRequest.ACTION_PHASE);

			deleteURL.setParameter(ActionRequest.ACTION_NAME, "deleteEntries");

			deleteURL.setParameter(
				"redirect", trashDisplayContext.getViewContentRedirectURL());
			deleteURL.setParameter(
				"className", trashDisplayContext.getClassName());
			deleteURL.setParameter(
				"classPK", String.valueOf(trashDisplayContext.getClassPK()));

			return deleteURL.toString();
		}
		catch (Exception exception) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 200.0;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		TrashDisplayContext trashDisplayContext = new TrashDisplayContext(
			_portal.getHttpServletRequest(portletRequest), null, null);

		TrashHandler trashHandler = trashDisplayContext.getTrashHandler();

		if (trashHandler == null) {
			return false;
		}

		if (trashHandler.isContainerModel()) {
			return false;
		}

		TrashEntry trashEntry = trashDisplayContext.getTrashEntry();

		try {
			if (!trashHandler.isDeletable(trashEntry.getClassPK())) {
				return false;
			}
		}
		catch (Exception exception) {
			return false;
		}

		return true;
	}

	@Reference
	private Portal _portal;

}