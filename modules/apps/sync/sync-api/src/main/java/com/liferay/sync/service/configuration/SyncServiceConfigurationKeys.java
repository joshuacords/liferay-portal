/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.service.configuration;

/**
 * @author Dennis Ju
 */
public class SyncServiceConfigurationKeys {

	public static final String SYNC_ALLOW_USER_PERSONAL_SITES =
		"sync.allow.user.personal.sites";

	public static final String SYNC_CLIENT_AUTHENTICATION_RETRY_INTERVAL =
		"sync.client.authentication.retry.interval";

	public static final String SYNC_CLIENT_BATCH_FILE_MAX_SIZE =
		"sync.client.batch.file.max.size";

	public static final String SYNC_CLIENT_FORCE_SECURITY_MODE =
		"sync.client.force.security.mode";

	public static final String SYNC_CLIENT_MAX_CONNECTIONS =
		"sync.client.max.connections";

	public static final String SYNC_CLIENT_MAX_DOWNLOAD_RATE =
		"sync.client.max.download.rate";

	public static final String SYNC_CLIENT_MAX_UPLOAD_RATE =
		"sync.client.max.upload.rate";

	public static final String SYNC_CLIENT_MIN_BUILD_ANDROID =
		"sync.client.min.build.android";

	public static final String SYNC_CLIENT_MIN_BUILD_DESKTOP =
		"sync.client.min.build.desktop";

	public static final String SYNC_CLIENT_MIN_BUILD_IOS =
		"sync.client.min.build.ios";

	public static final String SYNC_CLIENT_POLL_INTERVAL =
		"sync.client.poll.interval";

	public static final String SYNC_FILE_CHECKSUM_THRESHOLD_SIZE =
		"sync.file.checksum.threshold.size";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String SYNC_FILE_DIFF_CACHE_DELETE_INTERVAL =
		"sync.file.diff.cache.delete.interval";

	public static final String SYNC_FILE_DIFF_CACHE_ENABLED =
		"sync.file.diff.cache.enabled";

	public static final String SYNC_FILE_DIFF_CACHE_EXPIRATION_TIME =
		"sync.file.diff.cache.expiration.time";

	public static final String SYNC_LAN_ENABLED = "sync.lan.enabled";

	public static final String SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS =
		"sync.mac.package.folder.extensions";

	public static final String SYNC_MAC_PACKAGE_METADATA_FILE_NAMES =
		"sync.mac.package.metadata.file.names";

	public static final String SYNC_PAGINATION_DELTA = "sync.pagination.delta";

	public static final String SYNC_SERVICES_ENABLED = "sync.services.enabled";

	public static final String SYNC_VERIFY = "sync.verify";

}