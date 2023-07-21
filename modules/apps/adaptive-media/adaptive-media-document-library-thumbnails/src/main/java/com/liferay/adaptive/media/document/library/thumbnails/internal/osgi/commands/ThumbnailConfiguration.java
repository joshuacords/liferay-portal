/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.document.library.thumbnails.internal.osgi.commands;

import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Adolfo Pérez
 */
public class ThumbnailConfiguration {

	public ThumbnailConfiguration(int width, int height, Pattern pattern) {
		_width = width;
		_height = height;
		_pattern = pattern;
	}

	public long getFileVersionId(String fileName) {
		Matcher matcher = _pattern.matcher(fileName);

		if (!matcher.matches()) {
			return 0;
		}

		return GetterUtil.getLong(matcher.group(1));
	}

	public boolean matches(
		AMImageConfigurationEntry amImageConfigurationEntry) {

		Map<String, String> properties =
			amImageConfigurationEntry.getProperties();

		int maxWidth = GetterUtil.getInteger(properties.get("max-width"));
		int maxHeight = GetterUtil.getInteger(properties.get("max-height"));

		if ((_width != 0) && (_height != 0) && (_width == maxWidth) &&
			(_height == maxHeight)) {

			return true;
		}

		return false;
	}

	public Optional<AMImageConfigurationEntry> selectMatchingConfigurationEntry(
		Collection<AMImageConfigurationEntry> amImageConfigurationEntries) {

		Stream<AMImageConfigurationEntry> amImageConfigurationEntryStream =
			amImageConfigurationEntries.stream();

		return amImageConfigurationEntryStream.filter(
			this::matches
		).findFirst();
	}

	private final int _height;
	private final Pattern _pattern;
	private final int _width;

}