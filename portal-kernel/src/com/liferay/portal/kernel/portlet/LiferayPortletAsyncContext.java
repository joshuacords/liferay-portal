/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import javax.portlet.PortletAsyncContext;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;

/**
 * @author     Dante Wang
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface LiferayPortletAsyncContext extends PortletAsyncContext {

	public void addListener(AsyncListener asyncListener);

	public void doStart();

	public boolean isCalledDispatch();

	public void removeListener(AsyncListener asyncListener);

	public void reset(AsyncContext asyncContext);

}