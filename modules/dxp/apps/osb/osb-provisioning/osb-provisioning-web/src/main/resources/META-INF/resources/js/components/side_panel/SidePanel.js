/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTabs from '@clayui/tabs';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {NotesProvider} from '../../hooks/notes';
import {PermissionsProvider} from '../../hooks/permissions';
import {
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../../utilities/constants';
import IconButton from '../IconButton';
import ExternalLinksTabPane from './ExternalLinksTabPane';
import NotesTabPane from './NotesTabPane';

const COLLAPSED = 'collapsed';
const EXPANDED = 'expanded';

function CollapsiblePanel({
	addNoteURL,
	externalLinks,
	handleCollapse,
	hasUpdateNotesPermission,
	hasUpdateSalesInfoPermission
}) {
	const [activeIndex, setActiveIndex] = useState(0);

	return (
		<>
			<ClayTabs modern>
				<ClayTabs.Item
					active={activeIndex === 0}
					innerProps={{
						'aria-controls': 'tabPaneNotes'
					}}
					onClick={() => setActiveIndex(0)}
				>
					{Liferay.Language.get('notes')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 1}
					innerProps={{
						'aria-controls': 'tabPaneSalesInfo'
					}}
					onClick={() => setActiveIndex(1)}
				>
					{Liferay.Language.get('sales-info')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 2}
					innerProps={{
						'aria-controls': 'tabPaneExternalLinks'
					}}
					onClick={() => setActiveIndex(2)}
				>
					{Liferay.Language.get('external-links')}
				</ClayTabs.Item>
				<ClayTabs.Item
					className="panel-collapse"
					onClick={handleCollapse}
				>
					<svg
						aria-label={Liferay.Language.get(
							'collapse-panel-button'
						)}
						role="img"
					>
						<use xlinkHref="#collapse" />
					</svg>
				</ClayTabs.Item>
			</ClayTabs>

			<ClayTabs.Content activeIndex={activeIndex}>
				<ClayTabs.TabPane id="tabPaneNotes">
					<PermissionsProvider
						permissions={{
							updatePermission: hasUpdateNotesPermission
						}}
					>
						<NotesTabPane
							addURL={addNoteURL}
							tabType={NOTE_TYPE_GENERAL}
						/>
					</PermissionsProvider>
				</ClayTabs.TabPane>
				<ClayTabs.TabPane id="tabPaneSalesInfo">
					<PermissionsProvider
						permissions={{
							updatePermission: hasUpdateSalesInfoPermission
						}}
					>
						<NotesTabPane
							addURL={addNoteURL}
							tabType={NOTE_TYPE_SALES}
						/>
					</PermissionsProvider>
				</ClayTabs.TabPane>
				<ClayTabs.TabPane id="tabPaneExternalLinks">
					<ExternalLinksTabPane links={externalLinks} />
				</ClayTabs.TabPane>
			</ClayTabs.Content>
		</>
	);
}

CollapsiblePanel.propTypes = {
	handleCollapse: PropTypes.func.isRequired
};

function SidePanel(props) {
	const [collapse, setCollapse] = useState(getCollapsedState());

	const handleCollapse = () => {
		const sidePanelPanelState = !collapse === true ? COLLAPSED : EXPANDED;

		sessionStorage.setItem('sidePanelPanelState', sidePanelPanelState);

		setCollapse(!collapse);
	};

	useEffect(() => {
		const account = document.getElementById('account');

		if (!account) {
			return;
		}

		if (collapse) {
			account.classList.add('full-view');
		}
		else {
			account.classList.remove('full-view');
		}
	}, [collapse]);

	function getCollapsedState() {
		if (!sessionStorage.getItem('sidePanelPanelState')) {
			sessionStorage.setItem('sidePanelPanelState', EXPANDED);
		}

		return sessionStorage.getItem('sidePanelPanelState') === COLLAPSED
			? true
			: false;
	}

	return (
		<NotesProvider initialNotes={props.notes}>
			{collapse ? (
				<IconButton
					cssClass="btn-unstyled panel-expand"
					labelName="expand-panel-button"
					onClick={handleCollapse}
					svgId="#expand"
					title={Liferay.Language.get('expand')}
				/>
			) : (
				<CollapsiblePanel handleCollapse={handleCollapse} {...props} />
			)}
		</NotesProvider>
	);
}

SidePanel.propTypes = {
	addNoteURL: PropTypes.string,
	externalLinks: PropTypes.arrayOf(
		PropTypes.shape({
			domain: PropTypes.string.isRequired,
			entityId: PropTypes.string,
			entityName: PropTypes.string,
			key: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
			url: PropTypes.string
		})
	),
	hasUpdateNotesPermission: PropTypes.bool,
	hasUpdateSalesInfoPermission: PropTypes.bool,
	notes: PropTypes.arrayOf(
		PropTypes.shape({
			content: PropTypes.string.isRequired,
			createDate: PropTypes.string.isRequired,
			creatorName: PropTypes.string.isRequired,
			creatorPortraitURL: PropTypes.string,
			edited: PropTypes.bool.isRequired,
			format: PropTypes.string.isRequired,
			htmlContent: PropTypes.string.isRequired,
			key: PropTypes.string.isRequired,
			pinned: PropTypes.bool.isRequired,
			status: PropTypes.oneOf([
				NOTE_STATUS_APPROVED,
				NOTE_STATUS_ARCHIVED
			]).isRequired,
			type: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES])
				.isRequired,
			updateNoteURL: PropTypes.string.isRequired
		})
	)
};

export default SidePanel;
