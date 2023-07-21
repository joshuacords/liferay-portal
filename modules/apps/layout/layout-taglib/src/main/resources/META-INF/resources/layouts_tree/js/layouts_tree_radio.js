/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-layouts-tree-radio',
	A => {
		var Lang = A.Lang;

		var LABEL_TPL =
			'<span class="{cssClass}" title="{title}">{label}</span>';

		var STR_HOST = 'host';

		var LayoutsTreeRadio = A.Component.create({
			ATTRS: {
				showRootNode: {
					value: false
				}
			},

			EXTENDS: A.Plugin.Base,

			NAME: 'layoutstreeradio',

			NS: 'radio',

			prototype: {
				_formatNode() {
					var currentRetVal = A.Do.currentRetVal;

					return new A.Do.AlterReturn(
						'Modified type attribute',
						A.merge(currentRetVal, {
							type: 'liferay-radio'
						})
					);
				},

				_formatNodeLabel(node, cssClass, label, title) {
					return new A.Do.AlterReturn(
						'Modified node label',
						Lang.sub(LABEL_TPL, {
							cssClass,
							label,
							title
						})
					);
				},

				_formatRootNode(rootConfig) {
					var instance = this;

					var host = instance.get(STR_HOST);

					var labelEl = rootConfig.label;

					if (!instance.get('showRootNode')) {
						labelEl = A.Node.create('<span class="hide"></span>');

						host.get('boundingBox').addClass(
							'lfr-tree-root-node-hidden'
						);
					}

					return new A.Do.AlterReturn(
						'Modified cssClass, label and type attributes',
						A.merge(A.Do.currentRetVal, {
							labelEl,
							type: 'liferay-radio'
						})
					);
				},

				_onNodeCheckedChange(event) {
					var instance = this;

					if (event.newVal) {
						instance.get(STR_HOST).fire('radioNodeCheckedChange', {
							node: event.target
						});
					}
				},

				destructor() {
					var instance = this;

					new A.EventHandle(instance._eventHandles).detach();
				},

				initializer() {
					var instance = this;

					var host = instance.get(STR_HOST);

					instance._eventHandles = [
						instance.afterHostEvent(
							'*:checkedChange',
							instance._onNodeCheckedChange,
							instance
						),
						instance.doAfter(
							'_formatNode',
							instance._formatNode,
							instance
						),
						instance.doAfter(
							'_formatNodeLabel',
							instance._formatNodeLabel,
							instance
						),
						instance.doAfter(
							'_formatRootNode',
							instance._formatRootNode,
							instance
						)
					];

					host.get('boundingBox').addClass('lfr-tree-radio');
				}
			}
		});

		A.Plugin.LayoutsTreeRadio = LayoutsTreeRadio;
	},
	'',
	{
		requires: ['aui-tree-node', 'liferay-layouts-tree-node-radio']
	}
);
