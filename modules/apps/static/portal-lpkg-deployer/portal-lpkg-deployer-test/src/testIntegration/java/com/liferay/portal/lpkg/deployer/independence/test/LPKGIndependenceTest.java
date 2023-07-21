/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lpkg.deployer.independence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.FrameworkWiring;

/**
 * @author Matthew Tambara
 */
@RunWith(Arquillian.class)
public class LPKGIndependenceTest {

	@Test
	public void testLPKGIndependence() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(LPKGIndependenceTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		Path tempPath = Paths.get(
			PropsValues.MODULE_FRAMEWORK_BASE_DIR, "temp");

		File tempFile = tempPath.toFile();

		Bundle systemBundle = bundleContext.getBundle(0);

		FrameworkWiring frameworkWiring = systemBundle.adapt(
			FrameworkWiring.class);

		for (File lpkgDir : tempFile.listFiles()) {
			List<Bundle> bundles = new ArrayList<>();

			try {
				File[] lpkgFiles = lpkgDir.listFiles();

				for (File lpkgFile : lpkgFiles) {
					bundles.addAll(
						_installBundlesFromFile(bundleContext, lpkgFile));
				}

				_assertBundlesResolve(frameworkWiring, bundles);
			}
			finally {
				for (Bundle installedBundle : bundles) {
					installedBundle.uninstall();
				}
			}
		}
	}

	private void _assertBundlesResolve(
		FrameworkWiring frameworkWiring, List<Bundle> bundles) {

		List<Bundle> unresolvedBundles = new ArrayList<>();

		if (!frameworkWiring.resolveBundles(bundles)) {
			for (Bundle bundle : bundles) {
				if (bundle.getState() != Bundle.RESOLVED) {
					unresolvedBundles.add(bundle);
				}
			}
		}

		Assert.assertTrue(
			"Unable to resolve " + unresolvedBundles,
			unresolvedBundles.isEmpty());
	}

	private List<Bundle> _installBundlesFromFile(
			BundleContext bundleContext, File lpkgFile)
		throws Exception {

		List<Bundle> bundles = new ArrayList<>();

		try (ZipFile zipFile = new ZipFile(lpkgFile)) {
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();

				String name = zipEntry.getName();

				if (name.endsWith(".jar")) {
					try (InputStream inputStream = zipFile.getInputStream(
							zipEntry)) {

						bundles.add(
							bundleContext.installBundle(
								zipEntry.getName(), inputStream));
					}
				}
			}
		}

		return bundles;
	}

}