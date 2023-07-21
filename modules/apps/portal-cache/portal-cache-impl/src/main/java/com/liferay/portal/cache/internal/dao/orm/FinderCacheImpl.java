/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.internal.dao.orm;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheHelperUtil;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.cache.PortalCacheManagerListener;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.servlet.filters.threadlocal.ThreadLocalFilterThreadLocal;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.map.LRUMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@Component(
	immediate = true, service = {CacheRegistryItem.class, FinderCache.class}
)
public class FinderCacheImpl
	implements CacheRegistryItem, FinderCache, PortalCacheManagerListener {

	@Override
	public void clearCache() {
		clearLocalCache();

		for (PortalCache<?, ?> portalCache : _portalCaches.values()) {
			portalCache.removeAll();
		}
	}

	@Override
	public void clearCache(String className) {
		clearLocalCache();

		PortalCache<?, ?> portalCache = _getPortalCache(className);

		portalCache.removeAll();
	}

	@Override
	public void clearLocalCache() {
		if (_isLocalCacheEnabled()) {
			_localCache.remove();
		}
	}

	@Override
	public void dispose() {
		_portalCaches.clear();
	}

	@Override
	public String getRegistryName() {
		return FinderCache.class.getName();
	}

	@Override
	public Object getResult(
		FinderPath finderPath, Object[] args,
		BasePersistenceImpl<? extends BaseModel<?>> basePersistenceImpl) {

		if (!_valueObjectFinderCacheEnabled ||
			!finderPath.isFinderCacheEnabled() ||
			!CacheRegistryUtil.isActive()) {

			return null;
		}

		Serializable cacheValue = null;
		String encodedArguments = finderPath.encodeArguments(args);
		Map<Serializable, Serializable> localCache = null;
		Serializable localCacheKey = null;

		if (_isLocalCacheEnabled()) {
			localCache = _localCache.get();

			localCacheKey = finderPath.encodeLocalCacheKey(encodedArguments);

			cacheValue = localCache.get(localCacheKey);
		}

		if (cacheValue == null) {
			PortalCache<Serializable, Serializable> portalCache =
				_getPortalCache(finderPath.getCacheName());

			cacheValue = portalCache.get(
				finderPath.encodeCacheKey(encodedArguments));

			if ((cacheValue != null) && (localCache != null)) {
				localCache.put(localCacheKey, cacheValue);
			}
		}

		if (cacheValue == null) {
			return null;
		}

		if (cacheValue instanceof EmptyResult) {
			EmptyResult emptyResult = (EmptyResult)cacheValue;

			if (emptyResult.matches(args)) {
				return Collections.emptyList();
			}

			return null;
		}

		if (!finderPath.isBaseModelResult()) {
			return cacheValue;
		}

		if (cacheValue instanceof List<?>) {
			List<Serializable> primaryKeys = (List<Serializable>)cacheValue;

			Set<Serializable> primaryKeysSet = new HashSet<>(primaryKeys);

			Map<Serializable, ? extends BaseModel<?>> map =
				basePersistenceImpl.fetchByPrimaryKeys(primaryKeysSet);

			if (map.size() < primaryKeysSet.size()) {
				return null;
			}

			List<Serializable> list = new ArrayList<>(primaryKeys.size());

			for (Serializable curPrimaryKey : primaryKeys) {
				list.add(map.get(curPrimaryKey));
			}

			return Collections.unmodifiableList(list);
		}

		Serializable result = _entityCache.loadResult(
			finderPath.isEntityCacheEnabled(), finderPath.getResultClass(),
			cacheValue, basePersistenceImpl);

		if (result == _NULL_MODEL) {
			return null;
		}

		return result;
	}

	@Override
	public void init() {
	}

	@Override
	public void invalidate() {
		clearCache();
	}

	@Override
	public void notifyPortalCacheAdded(String portalCacheName) {
	}

	@Override
	public void notifyPortalCacheRemoved(String portalCacheName) {
		if (portalCacheName.startsWith(_GROUP_KEY_PREFIX)) {
			_portalCaches.remove(
				portalCacheName.substring(_GROUP_KEY_PREFIX.length()));
		}
	}

	@Override
	public void putResult(FinderPath finderPath, Object[] args, Object result) {
		putResult(finderPath, args, result, true);
	}

	@Override
	public void putResult(
		FinderPath finderPath, Object[] args, Object result, boolean quiet) {

		if (!_valueObjectFinderCacheEnabled ||
			!finderPath.isFinderCacheEnabled() ||
			!CacheRegistryUtil.isActive() || (result == null)) {

			return;
		}

		Serializable cacheValue = (Serializable)result;

		if (result instanceof BaseModel<?>) {
			BaseModel<?> model = (BaseModel<?>)result;

			cacheValue = model.getPrimaryKeyObj();
		}
		else if (result instanceof List<?>) {
			List<?> objects = (List<?>)result;

			if (objects.isEmpty()) {
				cacheValue = new EmptyResult(args);
			}
			else if ((objects.size() > _valueObjectFinderCacheListThreshold) &&
					 (_valueObjectFinderCacheListThreshold > 0)) {

				_removeResult(finderPath, args);

				return;
			}
			else if (finderPath.isBaseModelResult()) {
				ArrayList<Serializable> primaryKeys = new ArrayList<>(
					objects.size());

				for (Object object : objects) {
					BaseModel<?> baseModel = (BaseModel<?>)object;

					primaryKeys.add(baseModel.getPrimaryKeyObj());
				}

				cacheValue = primaryKeys;
			}
		}

		String encodedArguments = finderPath.encodeArguments(args);

		Serializable cacheKey = finderPath.encodeCacheKey(encodedArguments);

		if (_isLocalCacheEnabled()) {
			Map<Serializable, Serializable> localCache = _localCache.get();

			localCache.put(
				finderPath.encodeLocalCacheKey(encodedArguments), cacheValue);
		}

		PortalCache<Serializable, Serializable> portalCache = _getPortalCache(
			finderPath.getCacheName());

		if (quiet) {
			PortalCacheHelperUtil.putWithoutReplicator(
				portalCache, cacheKey, cacheValue);
		}
		else {
			portalCache.put(cacheKey, cacheValue);
		}
	}

	@Override
	public void removeCache(String className) {
		_portalCaches.remove(className);

		String groupKey = _GROUP_KEY_PREFIX.concat(className);

		_multiVMPool.removePortalCache(groupKey);
	}

	@Override
	public void removeResult(FinderPath finderPath, Object[] args) {
		if (!_valueObjectFinderCacheEnabled ||
			!finderPath.isFinderCacheEnabled() ||
			!CacheRegistryUtil.isActive()) {

			return;
		}

		_removeResult(finderPath, args);
	}

	@Activate
	@Modified
	protected void activate() {
		_valueObjectFinderCacheEnabled = GetterUtil.getBoolean(
			_props.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_ENABLED));
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			_props.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		if (_valueObjectFinderCacheListThreshold == 0) {
			_valueObjectFinderCacheEnabled = false;
		}

		int localCacheMaxSize = GetterUtil.getInteger(
			_props.get(
				PropsKeys.VALUE_OBJECT_FINDER_THREAD_LOCAL_CACHE_MAX_SIZE));

		if (localCacheMaxSize > 0) {
			_localCache = new CentralizedThreadLocal<>(
				FinderCacheImpl.class + "._localCache",
				() -> new LRUMap(localCacheMaxSize));
		}
		else {
			_localCache = null;
		}

		PortalCacheManager<? extends Serializable, ? extends Serializable>
			portalCacheManager = _multiVMPool.getPortalCacheManager();

		portalCacheManager.registerPortalCacheManagerListener(this);
	}

	@Reference(unbind = "-")
	protected void setEntityCache(EntityCache entityCache) {
		_entityCache = entityCache;
	}

	@Reference(unbind = "-")
	protected void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		_props = props;
	}

	private PortalCache<Serializable, Serializable> _getPortalCache(
		String className) {

		PortalCache<Serializable, Serializable> portalCache = _portalCaches.get(
			className);

		if (portalCache != null) {
			return portalCache;
		}

		String groupKey = _GROUP_KEY_PREFIX.concat(className);

		portalCache =
			(PortalCache<Serializable, Serializable>)
				_multiVMPool.getPortalCache(groupKey);

		PortalCache<Serializable, Serializable> previousPortalCache =
			_portalCaches.putIfAbsent(className, portalCache);

		if (previousPortalCache != null) {
			return previousPortalCache;
		}

		return portalCache;
	}

	private boolean _isLocalCacheEnabled() {
		if (_localCache == null) {
			return false;
		}

		return ThreadLocalFilterThreadLocal.isFilterInvoked();
	}

	private void _removeResult(FinderPath finderPath, Object[] args) {
		String encodedArguments = finderPath.encodeArguments(args);

		if (_isLocalCacheEnabled()) {
			Map<Serializable, Serializable> localCache = _localCache.get();

			localCache.remove(finderPath.encodeLocalCacheKey(encodedArguments));
		}

		PortalCache<Serializable, Serializable> portalCache = _getPortalCache(
			finderPath.getCacheName());

		portalCache.remove(finderPath.encodeCacheKey(encodedArguments));
	}

	private static final String _GROUP_KEY_PREFIX =
		FinderCache.class.getName() + StringPool.PERIOD;

	private static final Object _NULL_MODEL;

	static {
		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"nullModel");

			field.setAccessible(true);

			_NULL_MODEL = field.get(null);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new ExceptionInInitializerError(reflectiveOperationException);
		}
	}

	private EntityCache _entityCache;
	private ThreadLocal<LRUMap> _localCache;
	private MultiVMPool _multiVMPool;
	private final ConcurrentMap<String, PortalCache<Serializable, Serializable>>
		_portalCaches = new ConcurrentHashMap<>();
	private Props _props;
	private boolean _valueObjectFinderCacheEnabled;
	private int _valueObjectFinderCacheListThreshold;

}