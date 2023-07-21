/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.composite;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.List;
import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Carlos Sierra Andrés
 */
@ProviderType
public abstract class BaseCompositeMFAChecker implements MFAChecker {

	public BaseCompositeMFAChecker(List<MFAChecker> mfaCheckers) {
		this.mfaCheckers = mfaCheckers;
	}

	@Override
	public String getLabel(Locale locale) {
		if (mfaCheckers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((mfaCheckers.size() * 2) - 1);

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}

			sb.append(mfaChecker.getLabel(locale));
		}

		return sb.toString();
	}

	public List<MFAChecker> getMFACheckers() {
		return mfaCheckers;
	}

	@Override
	public String getName() {
		if (mfaCheckers.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((mfaCheckers.size() * 2) - 1);

		for (MFAChecker mfaChecker : mfaCheckers) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA);
			}

			sb.append(mfaChecker.getName());
		}

		return sb.toString();
	}

	@Override
	public boolean isEnabled() {
		for (MFAChecker mfaChecker : mfaCheckers) {
			if (!mfaChecker.isEnabled()) {
				return false;
			}
		}

		return true;
	}

	protected List<MFAChecker> mfaCheckers;

}