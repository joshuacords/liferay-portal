/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

/**
 * @author Eudaldo Alonso
 */
public class TrashViewContentActionDropdownItemsProvider {

	public TrashViewContentActionDropdownItemsProvider(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, String className,
		long classPK) {

		_liferayPortletResponse = liferayPortletResponse;
		_className = className;
		_classPK = classPK;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_trashHandler = TrashHandlerRegistryUtil.getTrashHandler(className);
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				if (_trashHandler.isMovable(_classPK)) {
					add(_getMoveActionDropdownItem());
				}

				if (_trashHandler.isDeletable(_classPK)) {
					add(_getDeleteActionDropdownItem());
				}
			}
		};
	}

	private DropdownItem _getDeleteActionDropdownItem() {
		return new DropdownItem() {
			{
				putData("action", "deleteEntry");

				PortletURL deleteEntryURL =
					_liferayPortletResponse.createActionURL();

				deleteEntryURL.setParameter(
					ActionRequest.ACTION_NAME, "deleteEntries");
				deleteEntryURL.setParameter(
					"redirect", _themeDisplay.getURLCurrent());
				deleteEntryURL.setParameter("className", _className);
				deleteEntryURL.setParameter(
					"classPK", String.valueOf(_classPK));

				putData("deleteEntryURL", deleteEntryURL.toString());

				setLabel(LanguageUtil.get(_themeDisplay.getLocale(), "delete"));
			}
		};
	}

	private DropdownItem _getMoveActionDropdownItem() throws Exception {
		return new DropdownItem() {
			{
				putData("action", "moveEntry");

				PortletURL moveEntryURL =
					_liferayPortletResponse.createRenderURL();

				moveEntryURL.setParameter(
					"mvcPath", "/view_container_model.jsp");
				moveEntryURL.setParameter(
					"classNameId",
					String.valueOf(PortalUtil.getClassNameId(_className)));
				moveEntryURL.setParameter("classPK", String.valueOf(_classPK));
				moveEntryURL.setParameter(
					"containerModelClassNameId",
					String.valueOf(
						PortalUtil.getClassNameId(
							_trashHandler.getContainerModelClassName(
								_classPK))));
				moveEntryURL.setWindowState(LiferayWindowState.POP_UP);

				putData("moveEntryURL", moveEntryURL.toString());

				setLabel(
					LanguageUtil.get(_themeDisplay.getLocale(), "restore"));
			}
		};
	}

	private final String _className;
	private final long _classPK;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final ThemeDisplay _themeDisplay;
	private final TrashHandler _trashHandler;

}