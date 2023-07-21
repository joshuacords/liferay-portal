/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import PropTypes from 'prop-types';
import React, {useContext, useState} from 'react';

import {ACTION_ITEM_TARGETS} from '../../utilities/actionItems/constants';
import {formatActionUrl} from '../../utilities/index';
import {
	openPermissionsModal,
	resolveModalSize
} from '../../utilities/modals/index';
import DatasetDisplayContext from '../dataset_display/DatasetDisplayContext';

const {MODAL_PERMISSIONS} = ACTION_ITEM_TARGETS;

function isNotALink(target, onClick) {
	return Boolean((target && target !== 'link') || onClick);
}

function ActionItem(props) {
	function handleClickOnLink(e) {
		e.preventDefault();

		props.handleAction({
			method: props.method,
			onClick: props.onClick,
			size: props.size || 'lg',
			target: props.target,
			title: props.title,
			url: props.href
		});

		props.closeMenu();
	}

	return (
		<ClayDropDown.Item
			data-senna-off
			href={props.href || '#'}
			onClick={
				isNotALink(props.target, props.onClick)
					? handleClickOnLink
					: null
			}
		>
			{props.icon && (
				<span className="pr-2">
					<ClayIcon symbol={props.icon} />
				</span>
			)}
			{props.label}
		</ClayDropDown.Item>
	);
}

function ActionsDropdownRenderer(props) {
	const {
		actionLoading,
		executeAsyncItemAction,
		highlightItems,
		openModal,
		openSidePanel
	} = useContext(DatasetDisplayContext);

	const [active, setActive] = useState(false);

	function handleAction({
		method = '',
		onClick = '',
		size = '',
		target = '',
		title = '',
		url = ''
	}) {
		if (!!target && target.includes('modal')) {
			switch (target) {
				case MODAL_PERMISSIONS:
					openPermissionsModal(url);
					break;
				default:
					openModal({
						size: resolveModalSize(target),
						title,
						url
					});
					break;
			}
		}

		if (target === 'sidePanel') {
			highlightItems([props.itemId]);
			openSidePanel({
				size: size || 'lg',
				title,
				url
			});
		}

		if (target === 'async' || target === 'headless') {
			executeAsyncItemAction(url, method);
		}

		if (onClick) {
			eval(onClick);
		}
	}

	const formattedActions = props.actions
		? props.actions.reduce((actions, action) => {
				if (action.permissionKey) {
					if (props.itemData.actions[action.permissionKey]) {
						if (action.target === 'headless') {
							return [
								...actions,
								{
									...action,
									...props.itemData.actions[
										action.permissionKey
									]
								}
							];
						}
						else {
							return [...actions, action];
						}
					}

					return actions;
				}

				return [...actions, action];
		  }, [])
		: [];

	if (!formattedActions || !formattedActions.length) {
		return null;
	}

	if (formattedActions.length === 1) {
		const action = formattedActions[0];

		if (action.id && !action.href) {
			return null;
		}

		const formattedHref = formatActionUrl(action.href, props.itemData);

		if (actionLoading) {
			return (
				<ClayButton
					className="btn-sm"
					disabled
					displayType="secondary"
					monospaced
				>
					<ClayLoadingIndicator small />
				</ClayButton>
			);
		}

		const content = action.icon ? (
			<ClayIcon symbol={action.icon} />
		) : (
			action.label
		);

		return isNotALink(action.target, action.onClick) ? (
			<ClayButton
				displayType="secondary"
				monospaced={Boolean(action.icon)}
				onClick={e => {
					e.preventDefault();

					return handleAction({
						method: action.method,
						onClick: action.onClick,
						size: action.size,
						target: action.target,
						title: action.title,
						url: formattedHref
					});
				}}
				small
			>
				{content}
			</ClayButton>
		) : (
			<ClayLink
				className="btn btn-secondary btn-sm"
				href={formattedHref}
				monospaced={Boolean(action.icon)}
			>
				{content}
			</ClayLink>
		);
	}

	if (actionLoading) {
		return (
			<ClayButton disabled displayType="secondary" monospaced small>
				<ClayLoadingIndicator small />
			</ClayButton>
		);
	}

	return (
		<ClayDropDown
			active={active}
			onActiveChange={setActive}
			trigger={
				<ClayButton
					className="component-action dropdown-toggle"
					displayType="unstyled"
				>
					<ClayIcon symbol="ellipsis-v" />
				</ClayButton>
			}
		>
			<ClayDropDown.ItemList>
				<ClayDropDown.Group>
					{formattedActions.map((action, i) => {
						return (
							<ActionItem
								key={i}
								{...action}
								closeMenu={() => setActive(false)}
								handleAction={handleAction}
								href={
									action.href &&
									formatActionUrl(action.href, props.itemData)
								}
							/>
						);
					})}
				</ClayDropDown.Group>
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);
}

ActionsDropdownRenderer.propTypes = {
	actions: PropTypes.arrayOf(
		PropTypes.shape({
			href: PropTypes.string,
			icon: PropTypes.string,
			label: PropTypes.string.isRequired,
			method: PropTypes.oneOf(['get', 'delete']),
			onClick: PropTypes.string,
			permissionKey: PropTypes.string,
			target: PropTypes.oneOf([
				'modal',
				'sidePanel',
				'link',
				'async',
				'headless'
			])
		})
	),
	itemData: PropTypes.object,
	itemId: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
};

export default ActionsDropdownRenderer;
