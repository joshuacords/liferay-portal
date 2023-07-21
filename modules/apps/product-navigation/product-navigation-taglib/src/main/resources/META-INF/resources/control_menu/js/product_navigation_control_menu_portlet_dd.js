/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-product-navigation-control-menu-portlet-dd',
	A => {
		var ControlMenu = Liferay.ControlMenu;
		var Layout = Liferay.Layout;

		var NAME = 'portletdd';

		var SELECTOR_ADD_CONTENT_ITEM = '.add-content-item';

		var STR_NODE = 'node';

		var PortletDragDrop = A.Component.create({
			ATTRS: {
				srcNode: {
					setter: A.one
				}
			},

			EXTENDS: A.Plugin.Base,

			NAME,

			NS: NAME,

			prototype: {
				_bindUIDragDrop() {
					var instance = this;

					var portletItemOptions = {
						delegateConfig: {
							container: instance.get('srcNode'),
							dragConfig: {
								clickPixelThresh: 0,
								clickTimeThresh: 0
							},
							invalid: '.lfr-portlet-used',
							target: false
						},
						dragNodes: '[data-draggable]',
						dropContainer(dropNode) {
							return dropNode.one(Layout.options.dropContainer);
						}
					};

					var defaultLayoutOptions = Layout.DEFAULT_LAYOUT_OPTIONS;

					if (defaultLayoutOptions) {
						portletItemOptions.on = defaultLayoutOptions.on;

						portletItemOptions.delegateConfig.dragConfig.plugins =
							defaultLayoutOptions.delegateConfig.dragConfig.plugins;
					}

					var portletItem = new ControlMenu['PortletItem'](
						portletItemOptions
					);

					portletItem.on('drag:end', instance._onDragEnd, instance);

					portletItem.delegate.dd.addInvalid(
						SELECTOR_ADD_CONTENT_ITEM
					);

					instance._portletItem = portletItem;

					Liferay.fire('initLayout');
				},

				_onDragEnd(event) {
					var instance = this;

					var portletItem = event.currentTarget;

					var appendNode = portletItem.appendNode;

					if (appendNode && appendNode.inDoc()) {
						var portletNode = event.target.get(STR_NODE);

						instance.fire('dragEnd', {
							appendNode,
							portletNode
						});
					}
				},

				initializer() {
					var instance = this;

					instance._bindUIDragDrop();
				}
			}
		});

		ControlMenu.PortletDragDrop = PortletDragDrop;
	},
	'',
	{
		requires: [
			'aui-base',
			'dd',
			'liferay-product-navigation-control-menu',
			'liferay-layout',
			'liferay-layout-column',
			'liferay-portlet-base'
		]
	}
);
