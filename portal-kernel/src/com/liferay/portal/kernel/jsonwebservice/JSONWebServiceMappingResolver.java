/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.jsonwebservice;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceMappingResolver {

	public JSONWebServiceMappingResolver(
		JSONWebServiceNaming jsonWebServiceNaming) {

		_jsonWebServiceNaming = jsonWebServiceNaming;
	}

	public String resolveHttpMethod(Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String httpMethod = null;

		if (jsonWebServiceAnnotation != null) {
			httpMethod = StringUtil.trim(jsonWebServiceAnnotation.method());
		}

		if ((httpMethod != null) && (httpMethod.length() != 0)) {
			return httpMethod;
		}

		return _jsonWebServiceNaming.convertMethodToHttpMethod(method);
	}

	public String resolvePath(Class<?> clazz, Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String path = null;

		if (jsonWebServiceAnnotation != null) {
			path = StringUtil.trim(jsonWebServiceAnnotation.value());
		}

		if ((path == null) || (path.length() == 0)) {
			path = _jsonWebServiceNaming.convertMethodToPath(method);
		}

		if (path.startsWith(StringPool.SLASH)) {
			return path;
		}

		path = StringPool.SLASH + path;

		String pathFromClass = null;

		jsonWebServiceAnnotation = clazz.getAnnotation(JSONWebService.class);

		if (jsonWebServiceAnnotation != null) {
			pathFromClass = StringUtil.trim(jsonWebServiceAnnotation.value());
		}

		if ((pathFromClass == null) || (pathFromClass.length() == 0)) {
			pathFromClass = _jsonWebServiceNaming.convertServiceClassToPath(
				clazz);
		}

		if (!pathFromClass.startsWith(StringPool.SLASH)) {
			pathFromClass = StringPool.SLASH + pathFromClass;
		}

		return pathFromClass + path;
	}

	private final JSONWebServiceNaming _jsonWebServiceNaming;

}