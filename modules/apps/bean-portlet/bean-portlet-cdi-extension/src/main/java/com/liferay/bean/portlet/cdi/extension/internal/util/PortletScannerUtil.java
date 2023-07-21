/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bean.portlet.cdi.extension.internal.util;

import com.liferay.bean.portlet.cdi.extension.internal.BeanMethod;
import com.liferay.bean.portlet.cdi.extension.internal.MethodType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventPortlet;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.HeaderPortlet;
import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceServingPortlet;
import javax.portlet.annotations.ActionMethod;
import javax.portlet.annotations.DestroyMethod;
import javax.portlet.annotations.EventMethod;
import javax.portlet.annotations.HeaderMethod;
import javax.portlet.annotations.InitMethod;
import javax.portlet.annotations.RenderMethod;
import javax.portlet.annotations.ServeResourceMethod;

/**
 * @author Neil Griffin
 */
public class PortletScannerUtil {

	public static void scanNonannotatedBeanMethods(
		BeanManager beanManager, Class<?> beanPortletClass,
		Set<BeanMethod> beanMethods) {

		Bean<?> bean = beanManager.resolve(
			beanManager.getBeans(beanPortletClass));

		if (Portlet.class.isAssignableFrom(beanPortletClass)) {
			try {
				Method processActionMethod = beanPortletClass.getMethod(
					"processAction", ActionRequest.class, ActionResponse.class);

				if (!processActionMethod.isAnnotationPresent(
						ActionMethod.class)) {

					beanMethods.add(
						new BeanMethod(
							beanManager, bean, processActionMethod,
							MethodType.ACTION));
				}

				Method destroyMethod = beanPortletClass.getMethod("destroy");

				if (!destroyMethod.isAnnotationPresent(DestroyMethod.class)) {
					beanMethods.add(
						new BeanMethod(
							beanManager, bean, destroyMethod,
							MethodType.DESTROY));
				}

				Method initMethod = beanPortletClass.getMethod(
					"init", PortletConfig.class);

				if (!initMethod.isAnnotationPresent(InitMethod.class)) {
					beanMethods.add(
						new BeanMethod(
							beanManager, bean, initMethod, MethodType.INIT));
				}

				Method renderMethod = beanPortletClass.getMethod(
					"render", RenderRequest.class, RenderResponse.class);

				if (!renderMethod.isAnnotationPresent(RenderMethod.class)) {
					beanMethods.add(
						new BeanMethod(
							beanManager, bean, renderMethod,
							MethodType.RENDER));
				}
			}
			catch (NoSuchMethodException noSuchMethodException) {
				_log.error(noSuchMethodException, noSuchMethodException);
			}
		}

		if (EventPortlet.class.isAssignableFrom(beanPortletClass)) {
			try {
				Method eventMethod = beanPortletClass.getMethod(
					"processEvent", EventRequest.class, EventResponse.class);

				if (!eventMethod.isAnnotationPresent(EventMethod.class)) {
					beanMethods.add(
						new BeanMethod(
							beanManager, bean, eventMethod, MethodType.EVENT));
				}
			}
			catch (NoSuchMethodException noSuchMethodException) {
				_log.error(noSuchMethodException, noSuchMethodException);
			}
		}

		if (HeaderPortlet.class.isAssignableFrom(beanPortletClass)) {
			try {
				Method renderHeadersMethod = beanPortletClass.getMethod(
					"renderHeaders", HeaderRequest.class, HeaderResponse.class);

				if (!renderHeadersMethod.isAnnotationPresent(
						HeaderMethod.class)) {

					beanMethods.add(
						new BeanMethod(
							beanManager, bean, renderHeadersMethod,
							MethodType.HEADER));
				}
			}
			catch (NoSuchMethodException noSuchMethodException) {
				_log.error(noSuchMethodException, noSuchMethodException);
			}
		}

		if (ResourceServingPortlet.class.isAssignableFrom(beanPortletClass)) {
			try {
				Method serveResourceMethod = beanPortletClass.getMethod(
					"serveResource", ResourceRequest.class,
					ResourceResponse.class);

				if (!serveResourceMethod.isAnnotationPresent(
						ServeResourceMethod.class)) {

					beanMethods.add(
						new BeanMethod(
							beanManager, bean, serveResourceMethod,
							MethodType.SERVE_RESOURCE));
				}
			}
			catch (NoSuchMethodException noSuchMethodException) {
				_log.error(noSuchMethodException, noSuchMethodException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletScannerUtil.class);

}