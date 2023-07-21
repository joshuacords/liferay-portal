/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.key;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author     Michael C. Han
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             HashCodeHexStringCacheKeyGenerator}
 */
@Deprecated
public class HashCodeCacheKeyGenerator extends BaseCacheKeyGenerator {

	@Override
	public CacheKeyGenerator clone() {
		return new HashCodeCacheKeyGenerator();
	}

	@Override
	public Integer getCacheKey(String key) {
		return key.hashCode();
	}

	@Override
	public Integer getCacheKey(String[] keys) {
		int hashCode = 0;
		int weight = 1;

		for (int i = keys.length - 1; i >= 0; i--) {
			String s = keys[i];

			hashCode = (s.hashCode() * weight) + hashCode;

			for (int j = s.length(); j > 0; j--) {
				weight *= 31;
			}
		}

		return hashCode;
	}

	@Override
	public Integer getCacheKey(StringBundler sb) {
		int hashCode = 0;
		int weight = 1;

		String[] array = sb.getStrings();

		for (int i = sb.index() - 1; i >= 0; i--) {
			String s = array[i];

			hashCode = (s.hashCode() * weight) + hashCode;

			for (int j = s.length(); j > 0; j--) {
				weight *= 31;
			}
		}

		return hashCode;
	}

}