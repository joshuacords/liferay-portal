/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.transformer;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = TransformerListener.class
)
public class ViewCounterTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(
		String output, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return replace(output, tokens);
	}

	/**
	 * Replace the counter token with the increment call.
	 *
	 * @return the processed string
	 */
	protected String replace(String s, Map<String, String> tokens) {
		if (!s.contains(_COUNTER_TOKEN)) {
			return s;
		}

		String articleResourcePK = tokens.get("article_resource_pk");

		StringBundler sb = new StringBundler(6);

		sb.append("<script type=\"text/javascript\">");
		sb.append("Liferay.Service('/assetentry/increment-view-counter',");
		sb.append("{userId:0, className:'");
		sb.append("com.liferay.journal.model.JournalArticle', classPK:");
		sb.append(articleResourcePK);
		sb.append("});</script>");

		return StringUtil.replace(s, _COUNTER_TOKEN, sb.toString());
	}

	private static final String _COUNTER_TOKEN = "@view_counter@";

	private static final Log _log = LogFactoryUtil.getLog(
		ViewCounterTransformerListener.class);

}