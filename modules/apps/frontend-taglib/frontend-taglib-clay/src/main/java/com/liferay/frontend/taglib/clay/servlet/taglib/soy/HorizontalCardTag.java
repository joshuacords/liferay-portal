/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayCardTag;

import java.util.Map;

/**
 * @author Julien Castelain
 */
public class HorizontalCardTag extends BaseClayCardTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayHorizontalCard");

		if (_horizontalCard != null) {
			_populateContext();
		}

		return super.doStartTag();
	}

	public void setHorizontalCard(HorizontalCard horizontalCard) {
		_horizontalCard = horizontalCard;

		super.setBaseClayCard(horizontalCard);
	}

	public void setIcon(String icon) {
		putValue("icon", icon);
	}

	public void setTitle(String title) {
		putValue("title", title);
	}

	private void _populateContext() {
		Map<String, Object> context = getContext();

		if (context.get("icon") == null) {
			setIcon(_horizontalCard.getIcon());
		}

		if (context.get("title") == null) {
			setTitle(_horizontalCard.getTitle());
		}
	}

	private HorizontalCard _horizontalCard;

}