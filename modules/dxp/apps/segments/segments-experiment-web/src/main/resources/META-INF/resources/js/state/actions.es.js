/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const addSegmentsExperiment = payload => ({
	payload,
	type: 'ADD_EXPERIMENT'
});

export const addVariant = payload => ({
	payload,
	type: 'ADD_VARIANT'
});

export const archiveExperiment = payload => ({
	payload,
	type: 'ARCHIVE_EXPERIMENT'
});

export const closeCreationModal = () => ({
	type: 'CREATE_EXPERIMENT_FINISH'
});

export const closeEditionModal = () => ({
	type: 'EDIT_EXPERIMENT_FINISH'
});

export const deleteArchivedExperiment = experimentId => ({
	payload: {
		experimentId
	},
	type: 'DELETE_ARCHIVED_EXPERIMENT'
});

export const openCreationModal = payload => ({
	payload,
	type: 'CREATE_EXPERIMENT_START'
});

export const openEditionModal = payload => ({
	payload,
	type: 'EDIT_EXPERIMENT_START'
});

export const updateSegmentsExperiment = payload => ({
	payload,
	type: 'UPDATE_EXPERIMENT'
});

export const updateVariant = payload => ({
	payload,
	type: 'UPDATE_VARIANT'
});

export const updateVariants = payload => ({
	payload,
	type: 'UPDATE_VARIANTS'
});
