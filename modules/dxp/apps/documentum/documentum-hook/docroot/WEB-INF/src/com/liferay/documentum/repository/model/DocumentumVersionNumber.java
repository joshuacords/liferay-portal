/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.model;

/**
 * @author Iván Zaera
 */
public class DocumentumVersionNumber
	implements Comparable<DocumentumVersionNumber> {

	public DocumentumVersionNumber(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	public DocumentumVersionNumber(String versionLabel) {
		String[] parts = versionLabel.split("\\.");

		major = Integer.valueOf(parts[0]);
		minor = Integer.valueOf(parts[1]);
	}

	@Override
	public int compareTo(DocumentumVersionNumber documentumVersionNumber) {
		if (major != documentumVersionNumber.major) {
			return major - documentumVersionNumber.major;
		}

		return minor - documentumVersionNumber.minor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentumVersionNumber)) {
			return false;
		}

		DocumentumVersionNumber documentumVersionNumber =
			(DocumentumVersionNumber)obj;

		if ((major == documentumVersionNumber.major) &&
			(minor == documentumVersionNumber.minor)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int result = major;

		return (31 * result) + minor;
	}

	public DocumentumVersionNumber increment(boolean incrementMajor) {
		if (incrementMajor) {
			return new DocumentumVersionNumber(major + 1, 0);
		}

		return new DocumentumVersionNumber(major, minor + 1);
	}

	@Override
	public String toString() {
		return major + "." + minor;
	}

	public final int major;
	public final int minor;

}