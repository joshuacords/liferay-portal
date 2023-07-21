/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Store Utility
 *
 * @deprecated As of Athanasius(7.3.x), replaced by Liferay.Util.Session
 * @module liferay-store
 */

AUI.add(
	'liferay-store',
	A => {
		var Lang = A.Lang;

		var isObject = Lang.isObject;

		var TOKEN_SERIALIZE = 'serialize://';

		var Store = function(key, value) {
			var method;

			if (Lang.isFunction(value)) {
				method = 'get';

				if (Array.isArray(key)) {
					method = 'getAll';
				}
			}
			else {
				method = 'set';

				if (isObject(key)) {
					method = 'setAll';
				}
				else if (arguments.length == 1) {
					method = null;
				}
			}

			if (method) {
				Store[method].apply(Store, arguments);
			}
		};

		A.mix(Store, {
			_getValues(cmd, key, callback) {
				var instance = this;

				var config = {
					callback,
					data: {
						cmd,
						key
					}
				};

				if (cmd == 'getAll') {
					config.dataType = 'json';
				}

				instance._ioRequest(config);
			},

			_ioRequest(config) {
				config.data.p_auth = Liferay.authToken;

				var doAsUserIdEncoded = themeDisplay.getDoAsUserIdEncoded();

				if (doAsUserIdEncoded) {
					config.data.doAsUserId = doAsUserIdEncoded;
				}

				const body = new URLSearchParams();

				Object.keys(config.data).forEach(key => {
					if (Array.isArray(config.data[key])) {
						config.data[key].forEach(value => {
							body.append(key, value);
						});
					}
					else {
						body.set(key, config.data[key]);
					}
				});

				Liferay.Util.fetch(
					themeDisplay.getPathMain() + '/portal/session_click',
					{
						body,
						method: 'POST'
					}
				)
					.then(response => {
						if (config.dataType === 'json') {
							return response.json();
						}
						else {
							return response.text();
						}
					})
					.then(data => {
						if (config.dataType === 'json') {
							if (
								Lang.isString(data) &&
								data.indexOf(TOKEN_SERIALIZE) === 0
							) {
								try {
									data = JSON.parse(
										data.substring(TOKEN_SERIALIZE.length)
									);
								}
								catch (e) {}
							}
						}

						if (Liferay.Util.isFunction(config.callback)) {
							config.callback(data);
						}
					});
			},

			_setValues(data) {
				var instance = this;

				instance._ioRequest({
					data
				});
			},

			get(key, callback) {
				var instance = this;

				instance._getValues('get', key, callback);
			},

			getAll(keys, callback) {
				var instance = this;

				instance._getValues('getAll', keys, callback);
			},

			set(key, value) {
				var instance = this;

				var obj = {};

				if (isObject(value)) {
					value = TOKEN_SERIALIZE + JSON.stringify(value);
				}

				obj[key] = value;

				instance._setValues(obj);
			},

			setAll(obj) {
				var instance = this;

				instance._setValues(obj);
			}
		});

		Liferay.Store = Store;
	},
	''
);
