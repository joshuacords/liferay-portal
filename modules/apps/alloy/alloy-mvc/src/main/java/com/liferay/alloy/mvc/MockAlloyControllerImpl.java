/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author William Newbury
 */
public class MockAlloyControllerImpl
	extends BaseAlloyControllerImpl implements AlloyController {

	public MockAlloyControllerImpl(
		BaseAlloyControllerImpl baseAlloyControllerImpl) {

		company = baseAlloyControllerImpl.company;

		try {
			user = UserLocalServiceUtil.getDefaultUser(company.getCompanyId());

			themeDisplay = baseAlloyControllerImpl.themeDisplay;

			themeDisplay = (ThemeDisplay)themeDisplay.clone();
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void afterPropertiesSet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Portlet getPortlet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpServletRequest getRequest() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getResponseContent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String translate(String pattern, Object... arguments) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateModel(BaseModel<?> baseModel, Object... properties)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Override
	protected void setAttachedModel(BaseModel<?> baseModel) throws Exception {
	}

}