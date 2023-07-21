/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.util;

import com.liferay.social.kernel.model.SocialActivityDefinition;

import java.util.List;

/**
 * @author Zsolt Berentey
 */
public class SocialConfigurationUtil {

	public static List<String> getActivityCounterNames() {
		return getSocialConfiguration().getActivityCounterNames();
	}

	public static List<String> getActivityCounterNames(
		boolean transientCounter) {

		return getSocialConfiguration().getActivityCounterNames(
			transientCounter);
	}

	public static List<String> getActivityCounterNames(int ownerType) {
		return getSocialConfiguration().getActivityCounterNames(ownerType);
	}

	public static List<String> getActivityCounterNames(
		int ownerType, boolean transientCounter) {

		return getSocialConfiguration().getActivityCounterNames(
			ownerType, transientCounter);
	}

	public static SocialActivityDefinition getActivityDefinition(
		String modelName, int activityType) {

		return getSocialConfiguration().getActivityDefinition(
			modelName, activityType);
	}

	public static List<SocialActivityDefinition> getActivityDefinitions(
		String modelName) {

		return getSocialConfiguration().getActivityDefinitions(modelName);
	}

	public static String[] getActivityModelNames() {
		return getSocialConfiguration().getActivityModelNames();
	}

	public static SocialConfiguration getSocialConfiguration() {
		return _socialConfiguration;
	}

	public static List<Object> read(ClassLoader classLoader, String[] xmls)
		throws Exception {

		return getSocialConfiguration().read(classLoader, xmls);
	}

	public static void removeActivityDefinition(
		SocialActivityDefinition activityDefinition) {

		getSocialConfiguration().removeActivityDefinition(activityDefinition);
	}

	public void setSocialConfiguration(
		SocialConfiguration socialConfiguration) {

		_socialConfiguration = socialConfiguration;
	}

	private static SocialConfiguration _socialConfiguration;

}