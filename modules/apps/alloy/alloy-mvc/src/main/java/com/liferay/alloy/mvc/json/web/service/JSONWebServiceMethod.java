/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc.json.web.service;

import com.liferay.petra.string.StringPool;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.portlet.PortletRequest;

/**
 * @author Ethan Bustad
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JSONWebServiceMethod {

	public String lifecycle() default PortletRequest.RENDER_PHASE;

	public String methodName() default StringPool.BLANK;

	public String[] parameterNames() default {};

	public Class<?>[] parameterTypes() default {};

}