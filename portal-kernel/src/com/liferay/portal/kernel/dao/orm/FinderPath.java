/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class FinderPath {

	public FinderPath(
		boolean entityCacheEnabled, boolean finderCacheEnabled,
		Class<?> resultClass, String cacheName, String methodName,
		String[] params) {

		this(
			entityCacheEnabled, finderCacheEnabled, resultClass, cacheName,
			methodName, params, -1);
	}

	public FinderPath(
		boolean entityCacheEnabled, boolean finderCacheEnabled,
		Class<?> resultClass, String cacheName, String methodName,
		String[] params, long columnBitmask) {

		_entityCacheEnabled = entityCacheEnabled;
		_finderCacheEnabled = finderCacheEnabled;
		_resultClass = resultClass;
		_cacheName = cacheName;
		_columnBitmask = columnBitmask;

		if (BaseModel.class.isAssignableFrom(_resultClass)) {
			_cacheKeyGeneratorCacheName = _BASE_MODEL_CACHE_KEY_GENERATOR_NAME;
		}
		else {
			_cacheKeyGeneratorCacheName = FinderCache.class.getName();
		}

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				_cacheKeyGeneratorCacheName);

		if (cacheKeyGenerator.isCallingGetCacheKeyThreadSafe()) {
			_cacheKeyGenerator = cacheKeyGenerator;
		}
		else {
			_cacheKeyGenerator = null;
		}

		_initCacheKeyPrefix(methodName, params);
		_initLocalCacheKeyPrefix();
	}

	public String encodeArguments(Object[] arguments) {
		String[] keys = new String[arguments.length * 2];

		for (int i = 0; i < arguments.length; i++) {
			int index = i * 2;

			keys[index] = StringPool.PERIOD;
			keys[index + 1] = StringUtil.toHexString(arguments[i]);
		}

		return StringUtil.toHexString(_getCacheKey(keys));
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #encodeCacheKey(String)}
	 */
	@Deprecated
	public Serializable encodeCacheKey(Object[] arguments) {
		String[] keys = new String[(arguments.length * 2) + 1];

		keys[0] = _cacheKeyPrefix;

		for (int i = 0; i < arguments.length; i++) {
			int index = (i * 2) + 1;

			keys[index] = StringPool.PERIOD;
			keys[index + 1] = StringUtil.toHexString(arguments[i]);
		}

		return _getCacheKey(keys);
	}

	public Serializable encodeCacheKey(String encodedArguments) {
		return _getCacheKey(new String[] {_cacheKeyPrefix, encodedArguments});
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #encodeLocalCacheKey(String)}
	 */
	@Deprecated
	public Serializable encodeLocalCacheKey(Object[] arguments) {
		String[] keys = new String[(arguments.length * 2) + 1];

		keys[0] = _localCacheKeyPrefix;

		for (int i = 0; i < arguments.length; i++) {
			int index = (i * 2) + 1;

			keys[index] = StringPool.PERIOD;
			keys[index + 1] = StringUtil.toHexString(arguments[i]);
		}

		return _getCacheKey(keys);
	}

	public Serializable encodeLocalCacheKey(String encodedArguments) {
		return _getCacheKey(
			new String[] {_localCacheKeyPrefix, encodedArguments});
	}

	public String getCacheName() {
		return _cacheName;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	public Class<?> getResultClass() {
		return _resultClass;
	}

	public boolean isBaseModelResult() {
		return BaseModel.class.isAssignableFrom(_resultClass);
	}

	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	private static Map<String, String> _getEncodedTypes() {
		return HashMapBuilder.put(
			Boolean.class.getName(), Boolean.class.getSimpleName()
		).put(
			Byte.class.getName(), Byte.class.getSimpleName()
		).put(
			Character.class.getName(), Character.class.getSimpleName()
		).put(
			Double.class.getName(), Double.class.getSimpleName()
		).put(
			Float.class.getName(), Float.class.getSimpleName()
		).put(
			Integer.class.getName(), Integer.class.getSimpleName()
		).put(
			Long.class.getName(), Long.class.getSimpleName()
		).put(
			Short.class.getName(), Short.class.getSimpleName()
		).put(
			String.class.getName(), String.class.getSimpleName()
		).build();
	}

	private Serializable _getCacheKey(String[] keys) {
		CacheKeyGenerator cacheKeyGenerator = _cacheKeyGenerator;

		if (cacheKeyGenerator == null) {
			cacheKeyGenerator = CacheKeyGeneratorUtil.getCacheKeyGenerator(
				_cacheKeyGeneratorCacheName);
		}

		return cacheKeyGenerator.getCacheKey(keys);
	}

	private void _initCacheKeyPrefix(String methodName, String[] params) {
		StringBundler sb = new StringBundler((params.length * 2) + 3);

		sb.append(methodName);
		sb.append(_PARAMS_SEPARATOR);

		for (String param : params) {
			sb.append(StringPool.PERIOD);
			sb.append(_encodedTypes.getOrDefault(param, param));
		}

		sb.append(_ARGS_SEPARATOR);

		_cacheKeyPrefix = sb.toString();
	}

	private void _initLocalCacheKeyPrefix() {
		_localCacheKeyPrefix = _cacheName.concat(
			StringPool.PERIOD
		).concat(
			_cacheKeyPrefix
		);
	}

	private static final String _ARGS_SEPARATOR = "_A_";

	private static final String _BASE_MODEL_CACHE_KEY_GENERATOR_NAME =
		FinderCache.class.getName() + "#BaseModel";

	private static final String _PARAMS_SEPARATOR = "_P_";

	private static final Map<String, String> _encodedTypes = _getEncodedTypes();

	private final CacheKeyGenerator _cacheKeyGenerator;
	private final String _cacheKeyGeneratorCacheName;
	private String _cacheKeyPrefix;
	private final String _cacheName;
	private final long _columnBitmask;
	private final boolean _entityCacheEnabled;
	private final boolean _finderCacheEnabled;
	private String _localCacheKeyPrefix;
	private final Class<?> _resultClass;

}