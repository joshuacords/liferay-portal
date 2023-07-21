/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.portlet.configuration.icon;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.web.internal.constants.KBWebKeys;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;

import javax.portlet.PortletRequest;

/**
 * @author Sergio González
 */
public abstract class BaseGetKBArticlePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	protected KBArticle getKBArticle(PortletRequest portletRequest) {
		KBArticle kbArticle = (KBArticle)portletRequest.getAttribute(
			KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		if (kbArticle != null) {
			return kbArticle;
		}

		return (KBArticle)portletRequest.getAttribute(
			KBWebKeys.KNOWLEDGE_BASE_PARENT_KB_ARTICLE);
	}

}