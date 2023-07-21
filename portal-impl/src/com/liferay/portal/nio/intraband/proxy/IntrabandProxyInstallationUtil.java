/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.nio.intraband.proxy;

import com.liferay.petra.lang.ClassLoaderPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.proxy.IntrabandProxySkeleton;
import com.liferay.portal.kernel.nio.intraband.proxy.IntrabandProxySkeletonRegistryUtil;
import com.liferay.portal.kernel.nio.intraband.proxy.TargetLocator;
import com.liferay.portal.kernel.nio.intraband.rpc.IntrabandRPCUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;

import java.lang.reflect.Constructor;

import java.util.Arrays;
import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class IntrabandProxyInstallationUtil {

	public static void checkProxyMethodSignatures(
		String[] skeletonProxyMethodSignatures,
		String[] stubProxyMethodSignatures) {

		if (Arrays.equals(
				skeletonProxyMethodSignatures, stubProxyMethodSignatures)) {

			return;
		}

		String skeletonProxyMethodSignaturesString = Arrays.toString(
			skeletonProxyMethodSignatures);
		String stubProxyMethodSignaturesString = Arrays.toString(
			stubProxyMethodSignatures);

		throw new IllegalStateException(
			StringBundler.concat(
				"Skeleton and stub proxy method signatures do not match. ",
				"Skeleton is ", skeletonProxyMethodSignaturesString,
				". Stub is ", stubProxyMethodSignaturesString, "."));
	}

	public static String[] installSkeleton(
		Class<?> clazz, TargetLocator targetLocator) {

		return installSkeleton(clazz.getClassLoader(), clazz, targetLocator);
	}

	public static String[] installSkeleton(
		ClassLoader classLoader, Class<?> clazz, TargetLocator targetLocator) {

		try {
			Class<?> proxySkeletonClass = IntrabandProxyUtil.getSkeletonClass(
				classLoader, clazz);

			Constructor<? extends IntrabandProxySkeleton> constructor =
				(Constructor<? extends IntrabandProxySkeleton>)
					proxySkeletonClass.getConstructor(TargetLocator.class);

			IntrabandProxySkeletonRegistryUtil.register(
				clazz.getName(), constructor.newInstance(targetLocator));

			return IntrabandProxyUtil.getProxyMethodSignatures(
				proxySkeletonClass);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public static Future<String[]> installSkeleton(
		RegistrationReference registrationReference, Class<?> clazz,
		TargetLocator targetLocator) {

		return installSkeleton(
			registrationReference, clazz.getClassLoader(), clazz,
			targetLocator);
	}

	public static Future<String[]> installSkeleton(
		RegistrationReference registrationReference, ClassLoader classLoader,
		Class<?> clazz, TargetLocator targetLocator) {

		return IntrabandRPCUtil.execute(
			registrationReference,
			new InstallSkeletonProcessCallable(
				classLoader, clazz, targetLocator));
	}

	protected static class InstallSkeletonProcessCallable
		implements ProcessCallable<String[]> {

		@Override
		public String[] call() throws ProcessException {
			ClassLoader classLoader = ClassLoaderPool.getClassLoader(
				_contextName);

			try {
				return installSkeleton(
					classLoader, classLoader.loadClass(_skeletonId),
					_targetLocator);
			}
			catch (Exception exception) {
				throw new ProcessException(exception);
			}
		}

		protected InstallSkeletonProcessCallable(
			ClassLoader classLoader, Class<?> clazz,
			TargetLocator targetLocator) {

			_targetLocator = targetLocator;

			_contextName = ClassLoaderPool.getContextName(classLoader);
			_skeletonId = clazz.getName();
		}

		private static final long serialVersionUID = 1L;

		private final String _contextName;
		private final String _skeletonId;
		private final TargetLocator _targetLocator;

	}

}