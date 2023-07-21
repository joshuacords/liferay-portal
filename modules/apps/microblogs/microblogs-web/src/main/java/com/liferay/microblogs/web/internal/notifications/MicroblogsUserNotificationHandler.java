/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.web.internal.notifications;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + MicroblogsPortletKeys.MICROBLOGS,
	service = UserNotificationHandler.class
)
public class MicroblogsUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public MicroblogsUserNotificationHandler() {
		setPortletId(MicroblogsPortletKeys.MICROBLOGS);
	}

	@Override
	protected String getBodyContent(JSONObject jsonObject) {
		return HtmlUtil.stripHtml(jsonObject.getString("entryTitle"));
	}

	@Override
	protected String getTitle(
		JSONObject jsonObject, AssetRenderer<?> assetRenderer,
		ServiceContext serviceContext) {

		String title = StringPool.BLANK;

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			serviceContext.getLocale(),
			MicroblogsUserNotificationHandler.class);

		MicroblogsEntry microblogsEntry =
			_microblogsEntryLocalService.fetchMicroblogsEntry(
				assetRenderer.getClassPK());

		String userFullName = HtmlUtil.escape(
			_portal.getUserName(microblogsEntry.getUserId(), StringPool.BLANK));

		int notificationType = jsonObject.getInt("notificationType");

		if (notificationType ==
				MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-commented-on-your-post", userFullName);
		}
		else if (notificationType ==
					MicroblogsEntryConstants.
						NOTIFICATION_TYPE_REPLY_TO_REPLIED) {

			long parentMicroblogsEntryUserId =
				microblogsEntry.fetchParentMicroblogsEntryUserId();

			User user = _userLocalService.fetchUser(
				parentMicroblogsEntryUserId);

			if (user != null) {
				title = ResourceBundleUtil.getString(
					resourceBundle, "x-also-commented-on-x's-post",
					userFullName, user.getFullName());
			}
		}
		else if (notificationType ==
					MicroblogsEntryConstants.
						NOTIFICATION_TYPE_REPLY_TO_TAGGED) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-commented-on-a-post-you-are-tagged-in",
				userFullName);
		}
		else if (notificationType ==
					MicroblogsEntryConstants.NOTIFICATION_TYPE_TAG) {

			title = ResourceBundleUtil.getString(
				resourceBundle, "x-tagged-you-in-a-post", userFullName);
		}

		return title;
	}

	@Reference(unbind = "-")
	protected void setMicroblogsEntryLocalService(
		MicroblogsEntryLocalService microblogsEntryLocalService) {

		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private MicroblogsEntryLocalService _microblogsEntryLocalService;

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;

}