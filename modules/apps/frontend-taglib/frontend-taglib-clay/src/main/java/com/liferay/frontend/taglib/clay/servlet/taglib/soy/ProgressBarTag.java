/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;

/**
 * @author Chema Balsas
 */
public class ProgressBarTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayProgressBar");
		setHydrate(true);
		setModuleBaseName("progress-bar");

		return super.doStartTag();
	}

	public void setMaxValue(int maxValue) {
		putValue("maxValue", maxValue);
	}

	public void setMinValue(int minValue) {
		putValue("minValue", minValue);
	}

	public void setStatus(String status) {
		putValue("status", status);
	}

	public void setValue(int value) {
		putValue("value", value);
	}

}