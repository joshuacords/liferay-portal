/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.version;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shuyang Zhou
 */
public class Version implements Comparable<Version> {

	public static Version parseVersion(String version) {
		if (version == null) {
			return new Version(0, 0, 0);
		}

		version = version.trim();

		if (version.isEmpty()) {
			return new Version(0, 0, 0);
		}

		version = version.trim();

		Matcher matcher = _versionPattern.matcher(version);

		if (!matcher.matches()) {
			return new Version(0, 0, 0);
		}

		return new Version(
			GetterUtil.getInteger(matcher.group(1)),
			GetterUtil.getInteger(matcher.group(3)),
			GetterUtil.getInteger(matcher.group(5)));
	}

	public Version(int major, int minor, int micro) {
		_major = major;
		_minor = minor;
		_micro = micro;
	}

	@Override
	public int compareTo(Version version) {
		int result = Integer.compare(_major, version._major);

		if (result != 0) {
			return result;
		}

		result = Integer.compare(_minor, version._minor);

		if (result != 0) {
			return result;
		}

		return Integer.compare(_micro, version._micro);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Version)) {
			return false;
		}

		Version version = (Version)object;

		if ((_major == version._major) && (_minor == version._minor) &&
			(_micro == version._micro)) {

			return true;
		}

		return false;
	}

	public int getMajor() {
		return _major;
	}

	public int getMicro() {
		return _micro;
	}

	public int getMinor() {
		return _minor;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _major);

		hash = HashUtil.hash(hash, _minor);

		return HashUtil.hash(hash, _micro);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append(_major);
		sb.append(StringPool.PERIOD);
		sb.append(_minor);
		sb.append(StringPool.PERIOD);
		sb.append(_micro);

		return sb.toString();
	}

	private static final Pattern _versionPattern = Pattern.compile(
		"(\\d{1,10})(\\.(\\d{1,10})(\\.(\\d{1,10})(\\.([-_\\da-zA-Z]+))?)?)?");

	private final int _major;
	private final int _micro;
	private final int _minor;

}