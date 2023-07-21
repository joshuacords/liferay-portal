/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-marketplace-messenger',
	A => {
		var NATIVE_MSG = !!window.postMessage;

		var MarketplaceMessenger = {
			_messages: [],
			_targetFrame: null,
			_targetURI: null,

			init(options, initMessage) {
				var instance = this;

				if (A.Lang.isString(options)) {
					instance._targetURI = options;
				}
				else if (A.Lang.isObject(options)) {
					var targetFrame = options.targetFrame;

					instance._targetFrame = A.one(targetFrame);

					instance._targetURI = options.targetURI;
				}

				if (initMessage) {
					instance.postMessage(initMessage);
				}
			},

			postMessage(message) {
				var instance = this;

				if (NATIVE_MSG) {
					A.postMessage(
						message,
						instance._targetURI,
						instance._targetFrame
					);
				}
				else {
					instance._messages.push(message);

					if (instance._messages.length == 1) {
						A.postMessage(
							message,
							instance._targetURI,
							instance._targetFrame
						);
					}
				}
			},

			receiveMessage(callback, validator) {
				var instance = this;

				validator = validator || instance._targetURI;

				if (NATIVE_MSG) {
					A.receiveMessage(callback, validator);
				}
				else {
					var wrappedCallback = function(event) {
						var response = event.responseData;

						callback(event);

						instance._messages.shift();

						var message = null;

						if (instance._messages.length > 0) {
							message = instance._messages[0];
						}
						else if (!response.empty) {
							message = {
								empty: true
							};
						}

						if (message) {
							A.postMessage(
								message,
								instance._targetURI,
								instance._targetFrame
							);
						}
					};

					A.receiveMessage(wrappedCallback, validator);
				}
			},

			setTargetFrame(targetFrame) {
				this._targetFrame = targetFrame;
			},

			setTargetURI(targetURI) {
				this._targetURI = targetURI;
			}
		};

		Liferay.MarketplaceMessenger = MarketplaceMessenger;
	},
	'',
	{
		requires: ['aui-messaging']
	}
);

AUI.add(
	'liferay-marketplace-util',
	A => {
		var MarketplaceUtil = {
			namespaceObject(namespace, object) {
				var returnObject = {};

				var keys = A.Object.keys(object);

				A.Array.each(keys, key => {
					returnObject[namespace + key] = object[key];
				});

				return returnObject;
			}
		};

		Liferay.MarketplaceUtil = MarketplaceUtil;
	},
	'',
	{
		requires: ['aui-base']
	}
);
