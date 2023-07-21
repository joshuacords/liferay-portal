/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.patcher;

import java.io.File;

import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class PatcherUtil {

	public static boolean applyPatch(File patchFile) {
		return getPatcher().applyPatch(patchFile);
	}

	public static String[] getFixedIssues() {
		return getPatcher().getFixedIssues();
	}

	public static String[] getInstalledPatches() {
		return getPatcher().getInstalledPatches();
	}

	public static File getPatchDirectory() {
		return getPatcher().getPatchDirectory();
	}

	public static Patcher getPatcher() {
		return _patcher;
	}

	public static int getPatchingToolVersion() {
		return getPatcher().getPatchingToolVersion();
	}

	public static String getPatchingToolVersionDisplayName() {
		return getPatcher().getPatchingToolVersionDisplayName();
	}

	public static String[] getPatchLevels() {
		return getPatcher().getPatchLevels();
	}

	public static Properties getProperties() {
		return getPatcher().getProperties();
	}

	public static String getSeparationId() {
		return getPatcher().getSeparationId();
	}

	public static boolean hasInconsistentPatchLevels() {
		return getPatcher().hasInconsistentPatchLevels();
	}

	public static boolean isConfigured() {
		return getPatcher().isConfigured();
	}

	public static boolean isSeparated() {
		return getPatcher().isSeparated();
	}

	public static void verifyPatchLevels() throws PatchInconsistencyException {
		getPatcher().verifyPatchLevels();
	}

	public void setPatcher(Patcher patcher) {
		_patcher = patcher;
	}

	private static Patcher _patcher;

}