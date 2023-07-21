/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.bookmarks.taglib.internal.util;

import com.liferay.social.bookmarks.SocialBookmark;
import com.liferay.social.bookmarks.SocialBookmarksRegistry;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class SocialBookmarksRegistryUtil {

	public static SocialBookmark getSocialBookmark(String type) {
		SocialBookmarksRegistry socialBookmarksRegistry =
			_socialBookmarksRegistryUtil._socialBookmarksRegistry;

		return socialBookmarksRegistry.getSocialBookmark(type);
	}

	public static List<String> getSocialBookmarksTypes() {
		SocialBookmarksRegistry socialBookmarksRegistry =
			_socialBookmarksRegistryUtil._socialBookmarksRegistry;

		return socialBookmarksRegistry.getSocialBookmarksTypes();
	}

	public static String[] getValidTypes(String[] types) {
		List<String> supportedTypes = getSocialBookmarksTypes();
		List<String> validTypes = new ArrayList<>();

		for (String type : types) {
			if (supportedTypes.contains(type)) {
				validTypes.add(type);
			}
		}

		return validTypes.toArray(new String[0]);
	}

	@Activate
	protected void activate() {
		_socialBookmarksRegistryUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_socialBookmarksRegistryUtil = null;
	}

	private static SocialBookmarksRegistryUtil _socialBookmarksRegistryUtil;

	@Reference
	private SocialBookmarksRegistry _socialBookmarksRegistry;

}