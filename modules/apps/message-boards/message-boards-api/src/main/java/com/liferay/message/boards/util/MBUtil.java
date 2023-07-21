/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.util;

import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.model.MBBan;
import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ThemeConstants;
import com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Adolfo Pérez
 */
public class MBUtil {

	public static final String BB_CODE_EDITOR_WYSIWYG_IMPL_KEY =
		"editor.wysiwyg.portal-web.docroot.html.portlet.message_boards." +
			"edit_message.bb_code.jsp";

	public static final String EMOTICONS = "/emoticons";

	public static String getBBCodeHTML(String msgBody, String pathThemeImages) {
		return StringUtil.replace(
			BBCodeTranslatorUtil.getHTML(msgBody),
			ThemeConstants.TOKEN_THEME_IMAGES_PATH + EMOTICONS,
			pathThemeImages + EMOTICONS);
	}

	public static String getSubscriptionClassName(String className) {
		if (className.startsWith(MBDiscussion.class.getName())) {
			return className;
		}

		return StringBundler.concat(
			MBDiscussion.class.getName(), StringPool.UNDERLINE, className);
	}

	public static Date getUnbanDate(MBBan ban, int expireInterval) {
		Date banDate = ban.getCreateDate();

		Calendar cal = Calendar.getInstance();

		cal.setTime(banDate);

		cal.add(Calendar.DATE, expireInterval);

		return cal.getTime();
	}

	public static boolean isValidMessageFormat(String messageFormat) {
		String editorName = PropsUtil.get(BB_CODE_EDITOR_WYSIWYG_IMPL_KEY);

		if (editorName.equals("bbcode")) {
			editorName = "alloyeditor_bbcode";

			if (_log.isWarnEnabled()) {
				_log.warn(
					"Replacing unsupported BBCode editor with AlloyEditor " +
						"BBCode");
			}
		}

		if (messageFormat.equals("bbcode") &&
			!editorName.equals("alloyeditor_bbcode") &&
			!editorName.equals("ckeditor_bbcode")) {

			return false;
		}

		if (!ArrayUtil.contains(MBMessageConstants.FORMATS, messageFormat)) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(MBUtil.class);

}