/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.generator.internal;

import com.liferay.osb.provisioning.license.generator.KeyGenerator;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.helper.constants.ProductId;
import com.liferay.petra.encryptor.Encryptor;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.Key;
import java.security.MessageDigest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(immediate = true, service = KeyGenerator.class)
public class KeyGeneratorImpl implements KeyGenerator {

	public Properties decryptServerId(byte[] bytes) throws Exception {
		Properties serverProperties = new Properties();

		for (Key key : _keys) {
			bytes = Encryptor.decryptUnencodedAsBytes(key, bytes);
		}

		PropertiesUtil.load(serverProperties, new String(bytes));

		return serverProperties;
	}

	public String generate(Map<String, String> properties) {
		return _encrypt(properties);
	}

	public String generate(
		String accountName, String licenseEntryName, String licenseEntryType,
		int licenseVersion, String productName, String productId,
		String productVersionLabel, String owner, int maxClusterNodes,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, String sizing, String description, String hostName,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate) {

		Map<String, String> properties = getProperties(
			accountName, licenseEntryName, licenseEntryType, licenseVersion,
			productName, productId, productVersionLabel, owner, maxClusterNodes,
			maxServers, maxHttpSessions, maxConcurrentUsers, maxUsers, sizing,
			description, hostName, ipAddresses, macAddresses, serverIds,
			startDate, expirationDate);

		return _encrypt(properties);
	}

	public Map<String, String> getProperties(
		String accountName, String licenseEntryName, String licenseEntryType,
		int licenseVersion, String productName, String productId,
		String productVersionLabel, String owner, int maxClusterNodes,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, String sizing, String description, String hostNames,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate) {

		Arrays.sort(serverIds);

		Map<String, String> properties = new HashMap<>();

		properties.put("description", description);
		properties.put("owner", owner);
		properties.put("startDate", String.valueOf(startDate.getTime()));
		properties.put("type", licenseEntryType);
		properties.put("version", String.valueOf(licenseVersion));

		if (licenseVersion == 1) {
			long lifetime = expirationDate.getTime() - startDate.getTime();

			properties.put("lifetime", String.valueOf(lifetime));

			properties.put("productVersion", productVersionLabel);

			if (licenseEntryType.equals(LicenseType.CLUSTER) ||
				licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

				for (int i = 0; i < serverIds.length; i++) {
					String serverId = StringUtil.replace(
						serverIds[i], CharPool.DASH, CharPool.COLON);

					serverId = StringUtil.toLowerCase(serverId.trim());

					properties.put("macAddress." + i, serverId);
				}
			}
			else {
				String serverId = serverIds[0].trim();

				properties.put("serverId", serverId);
			}
		}
		else if (licenseVersion == 2) {
			properties.put("accountEntryName", accountName);
			properties.put(
				"expirationDate", String.valueOf(expirationDate.getTime()));
			properties.put("licenseEntryName", licenseEntryName);
			properties.put("productEntryName", productName);
			properties.put("productVersion", productVersionLabel);

			if (licenseEntryType.equals(LicenseType.CLUSTER) ||
				licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

				properties.put("maxServers", String.valueOf(maxServers));
			}

			if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
				licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

				properties.put(
					"maxHttpSessions", String.valueOf(maxHttpSessions));
			}

			if (licenseEntryType.equals(LicenseType.PRODUCTION)) {
				String serverIdsList = StringUtil.merge(serverIds);

				serverIdsList = StringUtil.toLowerCase(serverIdsList);
				serverIdsList = StringUtil.replace(
					serverIdsList, CharPool.DASH, CharPool.COLON);

				properties.put("serverIds", serverIdsList);
			}
		}
		else if (licenseVersion >= 3) {
			if (productId.equals(ProductId.PORTAL)) {
				properties.put("accountEntryName", accountName);
				properties.put("licenseEntryName", licenseEntryName);
				properties.put("productVersion", productVersionLabel);
			}
			else {
				properties.put("productId", productId);
				properties.put("productVersion", productVersionLabel);
			}

			properties.put(
				"expirationDate", String.valueOf(expirationDate.getTime()));
			properties.put("productEntryName", productName);

			if (licenseEntryType.equals(LicenseType.VIRTUAL_CLUSTER)) {
				properties.put(
					"max-cluster-nodes", String.valueOf(maxClusterNodes));
			}

			if (licenseEntryType.equals(LicenseType.CLUSTER) ||
				((licenseVersion >= 4) &&
				 (licenseEntryType.equals(LicenseType.LIMITED) ||
				  licenseEntryType.equals(LicenseType.PRODUCTION)))) {

				properties.put("maxServers", String.valueOf(maxServers));
			}

			if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
				licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

				properties.put(
					"maxHttpSessions", String.valueOf(maxHttpSessions));
			}

			if (licenseEntryType.equals(LicenseType.PER_USER)) {
				if (maxConcurrentUsers > 0) {
					properties.put(
						"maxConcurrentUsers",
						String.valueOf(maxConcurrentUsers));
				}

				if (maxUsers > 0) {
					properties.put("maxUsers", String.valueOf(maxUsers));
				}
			}

			if ((licenseVersion >= 6) && Validator.isNotNull(sizing)) {
				properties.put("instanceSize", sizing);
			}

			if (licenseEntryType.equals(LicenseType.CLUSTER) ||
				licenseEntryType.equals(LicenseType.LIMITED) ||
				licenseEntryType.equals(LicenseType.PER_USER) ||
				licenseEntryType.equals(LicenseType.PRODUCTION)) {

				properties.put("hostNames", hostNames);
				properties.put("ipAddresses", ipAddresses);
				properties.put(
					"macAddresses",
					StringUtil.replace(
						macAddresses, CharPool.DASH, CharPool.COLON));

				if (serverIds.length > 0) {
					properties.put("serverIds", StringUtil.merge(serverIds));
				}
			}
		}

		return properties;
	}

	public String getServerId(
		String hostName, String ipAddresses, String macAddresses) {

		try {
			Properties serverIdProperties = new Properties();

			serverIdProperties.put("hostName", hostName);
			serverIdProperties.put("ipAddresses", ipAddresses);
			serverIdProperties.put(
				"macAddresses",
				StringUtil.replace(
					macAddresses, CharPool.DASH, CharPool.COLON));

			UUID uuid = UUID.randomUUID();

			serverIdProperties.put("salt", uuid.toString());

			String propertiesString = PropertiesUtil.toString(
				serverIdProperties);

			byte[] bytes = propertiesString.getBytes(StringPool.UTF8);

			for (int i = _keys.length - 1; i >= 0; i--) {
				bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
			}

			return Base64.objectToString(bytes);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return StringPool.BLANK;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		try {
			Class<?> clazz = getClass();

			String[] keys = StringUtil.split(
				StringUtil.read(
					clazz.getClassLoader(),
					"com/liferay/osb/provisioning/license/generator" +
						"/dependencies/keys.txt"),
				StringPool.NEW_LINE);

			_keys[0] = (Key)Base64.stringToObject(keys[175]);
			_keys[1] = (Key)Base64.stringToObject(keys[542]);
			_keys[2] = (Key)Base64.stringToObject(keys[706]);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private String _digest(String text, String algorithm) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

		messageDigest.update(text.getBytes());

		byte[] bytes = messageDigest.digest();

		StringBuilder sb = new StringBuilder(bytes.length << 1);

		for (byte curByte : bytes) {
			int byte_ = curByte & 0xff;

			sb.append(_HEX_CHARACTERS[byte_ >> 4]);
			sb.append(_HEX_CHARACTERS[byte_ & 0xf]);
		}

		return sb.toString();
	}

	private String _digestsToString(List<String> digests) {
		StringBundler sb = new StringBundler(digests.size());

		for (String digest : digests) {
			sb.append(digest);
		}

		return sb.toString();
	}

	private String _encrypt(Map<String, String> properties) {
		int licenseVersion = GetterUtil.getInteger(properties.get("version"));
		String productId = properties.get("productId");

		try {
			if (licenseVersion == 1) {
				return _encryptVersion1(properties);
			}
			else if (licenseVersion >= 2) {
				return _encryptVersion2(productId, properties);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return StringPool.BLANK;
	}

	private String _encryptVersion1(Map<String, String> properties)
		throws Exception {

		StringBundler sb = new StringBundler(properties.size() * 4);

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			sb.append(entry.getKey());
			sb.append(StringPool.EQUAL);
			sb.append(entry.getValue());
			sb.append(StringPool.NEW_LINE);
		}

		String propertiesString = sb.toString();

		byte[] bytes = propertiesString.getBytes(StringPool.UTF8);

		for (int i = _keys.length - 1; i >= 0; i--) {
			bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
		}

		return Base64.objectToString(bytes);
	}

	private String _encryptVersion2(
			String productId, Map<String, String> properties)
		throws Exception {

		List<String> keys = new ArrayList<>(properties.keySet());

		Collections.sort(keys);

		List<String> digests = new ArrayList<>(properties.size());

		for (int i = 0; i < keys.size(); i++) {
			String text = properties.get(keys.get(i));

			String digest = _digest(text, _getAlgorithm(productId, i));

			digests.add(digest);
		}

		digests = _shortenDigests(digests);

		for (int i = 0; i < digests.size(); i++) {
			String digest = digests.get(i);

			String algorithm = _getAlgorithm(productId, i);

			digests.set(i, _digest(digest, algorithm));
		}

		if (Validator.isNull(productId) || productId.equals(ProductId.PORTAL)) {
			return _interweaveDigest(digests);
		}

		return _digestsToString(digests);
	}

	private String _getAlgorithm(String productId, int i) {
		if (Validator.isNull(productId) || productId.equals(ProductId.PORTAL)) {
			return _ALGORITHMS[i % _ALGORITHMS.length];
		}

		return _ALGORITHMS[2];
	}

	private String _interweaveDigest(List<String> digests) {
		int size = digests.size();

		int finalLength = 0;
		int shortestLength = Integer.MAX_VALUE;

		for (String digest : digests) {
			int length = digest.length();

			finalLength += length;

			if (length < shortestLength) {
				shortestLength = length;
			}
		}

		StringBuilder sb = new StringBuilder(finalLength);

		for (int i = 0; i < shortestLength; i++) {
			for (int j = 0; j < size; j++) {
				String digest = digests.get(j);

				sb.append(digest.charAt(i));
			}
		}

		for (String digest : digests) {
			if (digest.length() > shortestLength) {
				sb.append(digest.substring(shortestLength));
			}
		}

		return sb.toString();
	}

	private List<String> _shortenDigests(List<String> digests)
		throws Exception {

		int size = digests.size();

		int groupSize = size / 4;

		if ((groupSize * 4) < size) {
			groupSize++;
		}

		List<String> shortenedDigests = new ArrayList<>(4);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++) {
			String digest = digests.get(i);

			if ((i != 0) && ((i % groupSize) == 0)) {
				shortenedDigests.add(sb.toString());

				sb.setLength(0);
			}

			sb.append(digest);
		}

		if (shortenedDigests.size() < 4) {
			shortenedDigests.add(sb.toString());
		}

		return shortenedDigests;
	}

	private static final String[] _ALGORITHMS = {
		"MD5", "SHA-1", "SHA-256", "SHA-512"
	};

	private static final char[] _HEX_CHARACTERS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	private static final Log _log = LogFactoryUtil.getLog(
		KeyGeneratorImpl.class);

	private final Key[] _keys = new Key[3];

}