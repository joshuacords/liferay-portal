/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public interface AlloyController {

	public void afterPropertiesSet();

	public void execute() throws Exception;

	public Portlet getPortlet();

	public HttpServletRequest getRequest();

	public String getResponseContent();

	public ThemeDisplay getThemeDisplay();

	public long increment() throws Exception;

	public void indexModel(BaseModel<?> baseModel) throws Exception;

	public void persistModel(BaseModel<?> baseModel) throws Exception;

	public void setModel(BaseModel<?> baseModel, Object... properties)
		throws Exception;

	public void setPageContext(PageContext pageContext);

	public void setUser(User user);

	public String translate(String pattern, Object... arguments);

	public void updateModel(BaseModel<?> baseModel, Object... properties)
		throws Exception;

	public void updateModelIgnoreRequest(
			BaseModel<?> baseModel, Object... properties)
		throws Exception;

}