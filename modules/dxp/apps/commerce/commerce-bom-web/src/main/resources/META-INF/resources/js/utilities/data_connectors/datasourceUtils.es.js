/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function createFilterObj(field, value = null, operations = 'contains') {
	return {
		field,
		operations,
		value
	};
}

export function compose(...fns) {
	return (...args) =>
		fns.reduceRight(
			(params, f) => (Array.isArray(params) ? f(...params) : f(params)),
			args
		);
}

export function pipe(...fns) {
	return (...args) =>
		fns.reduce(
			(params, f) => (Array.isArray(params) ? f(...params) : f(params)),
			args
		);
}

export function isPromise(p) {
	return !!p && p instanceof Promise;
}

export function wrapIntoPromise(action) {
	return (...params) => {
		const result = action.apply(null, params);

		return isPromise(result)
			? result
			: new Promise((resolve, _) => resolve(result));
	};
}

export const crudMethodsMapping = new Map([
	['read', 'get'],
	['create', 'post'],
	['update', 'put'],
	['delete', 'delete']
]);

export function getFetchParams(crudOperation, remoteOption, serializedData) {
	const method =
		(remoteOption && remoteOption.method) ||
		crudMethodsMapping.get(crudOperation);

	const baseUrl = remoteOption.url || remoteOption;
	const url = method === 'get' ? baseUrl + serializedData : baseUrl;
	const body = method !== 'get' ? serializedData : null;
	const credential = remoteOption.credential;

	return [
		url,
		{
			...(body || {}),
			...(credential || {})
		}
	];
}

export function formatRemoteKey(remoteKey, remoteValue) {
	const remoteDefaultValue = {
		credential: 'include',
		method: crudMethodsMapping.get(remoteKey)
	};

	if (typeof remoteValue === 'string') {
		remoteDefaultValue.url = remoteValue;
	}
	else {
		remoteDefaultValue.url = remoteValue.url;
		remoteDefaultValue.method =
			remoteValue.method || remoteDefaultValue.method;
		remoteDefaultValue.credential =
			remoteValue.credential || remoteDefaultValue.credential;
	}

	return remoteDefaultValue;
}

export function formatSetting(settingName, settingValue) {
	switch (settingName) {
		case 'remote': {
			const remoteKeys = Object.keys(settingValue);
			const formattedRemote = remoteKeys.reduce((acc, remoteKey) => {
				const remoteValue = settingValue[remoteKey];

				return {
					...acc,
					[remoteKey]: formatRemoteKey(remoteKey, remoteValue)
				};
			}, {});

			return [settingName, formattedRemote];
		}
		default:
			return [settingName, settingValue];
	}
}

export function isSettingValid(settingName) {
	switch (settingName) {
		case 'componentType':
		case 'remote':
		case 'on':
			return true;
		default:
			throw new Error(`Setting "${settingName}" not supported`);
	}
}

export function serializeParams(params, type = 'json') {
	let serializedParams = null;
	switch (type) {
		case 'json':
			serializedParams = JSON.stringify(params);
			break;
		default:
			throw new Error(`Serialization type "${type}" not supported`);
	}

	return serializedParams;
}
