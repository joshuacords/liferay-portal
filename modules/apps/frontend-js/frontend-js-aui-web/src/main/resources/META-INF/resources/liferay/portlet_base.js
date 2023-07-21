/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-portlet-base',
	A => {
		var PortletBase = function(config) {
			var instance = this;

			var namespace;

			if ('namespace' in config) {
				namespace = config.namespace;
			}
			else {
				namespace = A.guid();
			}

			instance.NS = namespace;
			instance.ID = namespace.replace(/^_(.*)_$/, '$1');

			if (config.rootNode) {
				instance._setRootNode(config.rootNode);
			}
		};

		PortletBase.ATTRS = {
			namespace: {
				getter: '_getNS',
				writeOnce: true
			},
			rootNode: {
				getter: '_getRootNode',
				setter: '_setRootNode',
				valueFn() {
					var instance = this;

					return A.one('#p_p_id' + instance.NS);
				}
			}
		};

		PortletBase.prototype = {
			_formatSelectorNS(ns, selector) {
				return selector.replace(
					A.DOM._getRegExp('(#|\\[id=(\\"|\\\'))(?!' + ns + ')', 'g'),
					'$1' + ns
				);
			},

			_getNS() {
				var instance = this;

				return instance.NS;
			},

			_getRootNode() {
				var instance = this;

				return instance.rootNode;
			},

			_setRootNode(value) {
				var instance = this;

				var rootNode = A.one(value);

				instance.rootNode = rootNode;

				return rootNode;
			},

			all(selector, root) {
				var instance = this;

				root = A.one(root) || instance.rootNode || A;

				return root.all(
					instance._formatSelectorNS(instance.NS, selector)
				);
			},

			byId(id) {
				var instance = this;

				return A.one('#' + A.Lang.String.prefix(instance.NS, id));
			},

			ns(str) {
				var instance = this;

				return Liferay.Util.ns(instance.NS, str);
			},

			one(selector, root) {
				var instance = this;

				root = A.one(root) || instance.rootNode || A;

				return root.one(
					instance._formatSelectorNS(instance.NS, selector)
				);
			}
		};

		Liferay.PortletBase = PortletBase;
	},
	'',
	{
		requires: ['aui-base']
	}
);
