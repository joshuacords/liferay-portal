/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.toolbar.contributor.helper;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.web.internal.settings.DLPortletInstanceSettings;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(service = DLPortletToolbarContributorHelper.class)
public class DLPortletToolbarContributorHelper {

	public Folder getFolder(
		ThemeDisplay themeDisplay, PortletRequest portletRequest) {

		Folder folder = (Folder)portletRequest.getAttribute(
			WebKeys.DOCUMENT_LIBRARY_FOLDER);

		if (folder != null) {
			return folder;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		long rootFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		try {
			DLPortletInstanceSettings dlPortletInstanceSettings =
				DLPortletInstanceSettings.getInstance(
					themeDisplay.getLayout(), portletDisplay.getId());

			rootFolderId = dlPortletInstanceSettings.getRootFolderId();
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);
		}

		if (rootFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			try {
				folder = _dlAppLocalService.getFolder(rootFolderId);
			}
			catch (NoSuchFolderException | PrincipalException exception) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}

				folder = null;
			}
			catch (PortalException portalException) {
				_log.error(portalException, portalException);
			}
		}

		return folder;
	}

	public Boolean isShowActionsEnabled(
		ThemeDisplay themeDisplay, PortletRequest portletRequest) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletDisplayId = portletDisplay.getId();

		if (!portletDisplayId.equals(DLPortletKeys.DOCUMENT_LIBRARY_ADMIN)) {
			try {
				DLPortletInstanceSettings dlPortletInstanceSettings =
					DLPortletInstanceSettings.getInstance(
						themeDisplay.getLayout(), portletDisplayId);

				if (!dlPortletInstanceSettings.isShowActions()) {
					return false;
				}
			}
			catch (PortalException portalException) {
				_log.error(portalException, portalException);
			}
		}

		return true;
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLPortletToolbarContributorHelper.class);

	private DLAppLocalService _dlAppLocalService;

}