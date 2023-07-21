/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

/**
 * @author Chema Balsas
 */
public class StripeTag extends AlertTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayStripe");
		setHydrate(true);
		setModuleBaseName("alert");

		return super.doStartTag();
	}

	@Override
	public void setAutoclose(Boolean autoClose) {
		putValue("autoClose", autoClose);
	}

	@Override
	public void setCloseable(Boolean closeable) {
		putValue("closeable", closeable);
	}

	@Override
	public void setDestroyOnHide(Boolean destroyOnHide) {
		putValue("destroyOnHide", destroyOnHide);
	}

	@Override
	public void setMessage(String message) {
		putValue("message", message);
	}

	@Override
	public void setStyle(String style) {
		putValue("style", style);
	}

	@Override
	public void setTitle(String title) {
		putValue("title", title);
	}

	@Override
	public void setType(String type) {
		putValue("type", type);
	}

}