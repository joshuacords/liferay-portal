/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Token List Component.
 *
 * @deprecated since 7.2, unused
 * @module liferay-token-list
 */

AUI().add(
	'liferay-token-list',
	A => {
		var TPL_TOKEN = A.Template(
			'<tpl for=".">',
			'<span class="lfr-token" data-fieldValues="{fieldValues}" data-clearFields="{clearFields}">',
			'<span class="lfr-token-text">{text:this.getTokenText}</span>',

			'<a class="icon icon-remove lfr-token-close" href="javascript:;"></a>',
			'</span>',
			'</tpl>',
			{
				getTokenText(str, values) {
					if ('html' in values) {
						str = values.html;
					}
					else {
						str = A.Escape.html(str);
					}

					return str;
				}
			}
		);

		var TokenList = A.Component.create({
			ATTRS: {
				children: {
					validator: Array.isArray,
					value: []
				},
				cssClass: {
					value: 'lfr-token-list'
				}
			},

			NAME: 'liferaytokenlist',

			prototype: {
				_addToken() {
					var instance = this;

					var buffer = instance._buffer;

					instance.get('contentBox').append(TPL_TOKEN.parse(buffer));

					buffer.length = 0;
				},

				_defCloseFn(event) {
					event.item.remove();
				},

				_onClick(event) {
					var instance = this;

					instance.fire('close', {
						item: event.currentTarget.ancestor('.lfr-token')
					});
				},

				add(token) {
					var instance = this;

					if (token) {
						var buffer = instance._buffer;

						if (Array.isArray(token)) {
							instance._buffer = buffer.concat(token);
						}
						else {
							buffer.push(token);
						}

						instance._addTokenTask();
					}
				},

				bindUI() {
					var instance = this;

					var boundingBox = instance.get('boundingBox');

					boundingBox.delegate(
						'click',
						instance._onClick,
						'.lfr-token-close',
						instance
					);

					instance.publish('close', {
						defaultFn: A.bind('_defCloseFn', instance)
					});
				},

				initializer() {
					var instance = this;

					instance._buffer = [];

					instance._addTokenTask = A.debounce(
						instance._addToken,
						100
					);
				},

				renderUI() {
					var instance = this;

					instance.add(instance.get('children'));
				}
			}
		});

		Liferay.TokenList = TokenList;
	},
	'',
	{
		requires: ['aui-base', 'aui-template-deprecated', 'escape']
	}
);
