/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext, useEffect, useState} from 'react';

import DetailsBox from './components/DetailsBox.es';
import ErrorMessage from './components/ErrorMessage.es';
import PictureBox from './components/PictureBox.es';
import StoreContext from './components/StoreContext.es';
import Icon from './components/utilities/Icon.es';

function App(props) {
	const {actions, state} = useContext(StoreContext);
	const [dataFetched, updateDataFetchedStatus] = useState(false);

	useEffect(() => {
		if (!state.app.initialized) {
			actions.initializeAppData({
				areaApiUrl: props.areaApiUrl,
				areaId: props.areaId,
				productApiUrl: props.productApiUrl,
				spritemap: props.spritemap
			});
		}

		if (state.app.initialized && !dataFetched) {
			actions.getArea(state.app.areaApiUrl, state.area.id);
			updateDataFetchedStatus(true);
		}
	}, [
		state.app.initialized,
		state.app.areaApiUrl,
		state.area.id,
		dataFetched,
		actions,
		props.spritemap,
		props.areaApiUrl,
		props.productApiUrl,
		props.areaId
	]);

	return (
		<div className="bom-admin-container container pt-3">
			<div className="row">
				<div className="col-12 col-xl-8">
					<PictureBox />
				</div>
				<div className="col-12 col-xl-4">
					<DetailsBox />
				</div>
			</div>
			{state.app.error && (
				<ErrorMessage
					closeIcon={
						<Icon
							spritemap={state.app.spritemap}
							symbol={'close'}
						/>
					}
					message={state.app.error}
					onClose={actions.dismissError}
				/>
			)}
		</div>
	);
}

export default App;
