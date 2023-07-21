/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext, useEffect, useState} from 'react';

import Connector from '../utilities/data_connectors/Connector.es';
import BaseContainer from './BaseContainer.es';
import Breadcrumbs from './Breadcrumbs.es';
import ErrorMessage from './ErrorMessage.es';
import FolderViewer from './FolderViewer.es';
import Loading from './Loading.es';
import {StoreContext} from './StoreContext.es';
import AreaViewer from './areas/AreaViewer.es';

export function PartFinder(props) {
	const [initialized, setInitialized] = useState(false);
	const [page, updatePage] = useState('base');
	const {actions, state} = useContext(StoreContext);

	useEffect(() => {
		if (props.connectorSettings) {
			new Connector(props.connectorSettings);
		}
	}, [props.connectorSettings]);

	function updateData() {
		const filteredUrl = /^.*(folderId|areaId)=([0-9a-zA-Z-]+)/.exec(
			props.history.location.search
		);
		const id = filteredUrl ? filteredUrl[2] : null;
		const queryParam = filteredUrl ? filteredUrl[1] : 'folderId';

		switch (queryParam) {
			case 'folderId':
				actions.getFolder(props.foldersEndpoint, id);
				updatePage('folder');
				break;
			case 'areaId':
				actions.getArea(props.areasEndpoint, id);
				updatePage('area');
				break;
			default:
				break;
		}
	}

	function initialize() {
		actions.initialize({
			areasEndpoint: props.areasEndpoint,
			basePathUrl: props.basePathUrl,
			basename: props.basename || '/',
			foldersEndpoint: props.foldersEndpoint,
			history: props.history,
			spritemap: props.spritemap
		});

		props.history.listen(() => {
			updateData();
		});

		updateData();
		setInitialized(true);
	}

	useEffect(() => {
		if (!initialized) {
			initialize();
		}
	});

	if (state.app.error) {
		return <ErrorMessage />;
	}

	if (state.app.loading) {
		return <Loading />;
	}

	return (
		<div className="content">
			<Breadcrumbs data={state.app.breadcrumbs} />
			{page === 'base' && <BaseContainer />}
			{page === 'area' && <AreaViewer />}
			{page === 'folder' && <FolderViewer />}
		</div>
	);
}

export default PartFinder;
