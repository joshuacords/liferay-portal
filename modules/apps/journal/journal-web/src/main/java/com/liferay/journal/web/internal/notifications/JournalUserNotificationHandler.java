/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.notifications;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.web.internal.asset.model.JournalArticleAssetRenderer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = UserNotificationHandler.class
)
public class JournalUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public JournalUserNotificationHandler() {
		setPortletId(JournalPortletKeys.JOURNAL);
	}

	@Override
	protected String getTitle(
		JSONObject jsonObject, AssetRenderer<?> assetRenderer,
		ServiceContext serviceContext) {

		String title = StringPool.BLANK;

		ResourceBundleLoader resourceBundleLoader =
			ResourceBundleLoaderUtil.
				getResourceBundleLoaderByBundleSymbolicName(
					"com.liferay.journal.lang");

		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(
			serviceContext.getLocale());

		JournalArticleAssetRenderer journalArticleAssetRenderer =
			(JournalArticleAssetRenderer)assetRenderer;

		JournalArticle journalArticle =
			journalArticleAssetRenderer.getArticle();

		long userId = GetterUtil.getLong(
			jsonObject.getLong("userId"), journalArticle.getUserId());

		String userFullName = HtmlUtil.escape(
			_portal.getUserName(userId, StringPool.BLANK));

		int notificationType = jsonObject.getInt("notificationType");

		if (notificationType ==
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-added-a-new-web-content-article",
				userFullName);
		}
		else if (notificationType ==
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-updated-a-web-content-article",
				userFullName);
		}
		else if (notificationType ==
					JournalArticleConstants.
						NOTIFICATION_TYPE_MOVE_ENTRY_FROM_FOLDER) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-moved-a-web-content-from-a-folder",
				userFullName);
		}
		else if (notificationType ==
					JournalArticleConstants.
						NOTIFICATION_TYPE_MOVE_ENTRY_FROM_TRASH) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-restored-a-web-content-from-the-recycle-bin",
				userFullName);
		}
		else if (notificationType ==
					JournalArticleConstants.
						NOTIFICATION_TYPE_MOVE_ENTRY_TO_FOLDER) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-moved-a-web-content-to-a-folder",
				userFullName);
		}
		else if (notificationType ==
					JournalArticleConstants.
						NOTIFICATION_TYPE_MOVE_ENTRY_TO_TRASH) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-moved-a-web-content-to-the-recycle-bin",
				userFullName);
		}

		return title;
	}

	@Reference
	private Portal _portal;

}