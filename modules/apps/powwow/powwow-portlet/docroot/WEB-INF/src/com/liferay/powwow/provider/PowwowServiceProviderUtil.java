/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.provider;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 * @author Marco Calderon
 */
public class PowwowServiceProviderUtil {

	public static Map<String, Serializable> addPowwowMeeting(
			long userId, long powwowServerId, long powwowMeetingId, String name,
			String providerType, Map<String, String> options)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.addPowwowMeeting(
			userId, powwowServerId, powwowMeetingId, name, options);
	}

	public static PowwowMeeting deletePowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.deletePowwowMeeting(powwowMeetingId);
	}

	public static PowwowMeeting endPowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.endPowwowMeeting(powwowMeetingId);
	}

	public static int getAddPowwowMeetingStrategy(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getAddPowwowMeetingStrategy();
	}

	public static List<String> getBrandingFeatures(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getBrandingFeatures();
	}

	public static String getBrandingLabel(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getBrandingLabel();
	}

	public static Map<String, String> getIndexFields(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.getIndexFields(powwowMeetingId);
	}

	public static long getJoinByPhoneAccessCode(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.getJoinByPhoneAccessCode(powwowMeetingId);
	}

	public static String getJoinByPhoneAccessCodeLabel(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getJoinByPhoneAccessCodeLabel();
	}

	public static List<String> getJoinByPhoneDefaultNumbers(
		String providerType) {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getJoinByPhoneDefaultNumbers();
	}

	public static Map<String, List<String>> getJoinByPhoneInternationalNumbers(
		String providerType) {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getJoinByPhoneInternationalNumbers();
	}

	public static String getJoinPowwowMeetingURL(
			long powwowMeetingId, String name, int type)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.getJoinPowwowMeetingURL(
			powwowMeetingId, name, type);
	}

	public static boolean getOptionAutoStartVideo(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.getOptionAutoStartVideo(powwowMeetingId);
	}

	public static String getOptionPassword(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.getOptionPassword(powwowMeetingId);
	}

	public static long getPowwowServerId(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getPowwowServerId(providerType);
	}

	public static String getPowwowServiceProviderName(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.getPowwowServiceProviderName();
	}

	public static boolean isFieldAPIKeyRequired(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isFieldAPIKeyRequired();
	}

	public static boolean isFieldSecretRequired(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isFieldSecretRequired();
	}

	public static boolean isFieldURLRequired(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isFieldURLRequired();
	}

	public static boolean isPowwowMeetingCreated(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.isPowwowMeetingCreated(powwowMeetingId);
	}

	public static boolean isPowwowMeetingRunning(long powwowMeetingId)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowMeetingId);

		return powwowServiceProvider.isPowwowMeetingRunning(powwowMeetingId);
	}

	public static boolean isServerActive(PowwowServer powwowServer) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			powwowServer.getProviderType());

		return powwowServiceProvider.isServerActive(powwowServer);
	}

	public static boolean isSupportsJoinByPhone(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isSupportsJoinByPhone();
	}

	public static boolean isSupportsOptionAutoStartVideo(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isSupportsOptionAutoStartVideo();
	}

	public static boolean isSupportsOptionPassword(String providerType) {
		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isSupportsOptionPassword();
	}

	public static boolean isSupportsPresettingParticipantName(
		String providerType) {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.isSupportsPresettingParticipantName();
	}

	public static Map<String, Serializable> updatePowwowMeeting(
			long powwowMeetingId, String name, String providerType, long userId,
			Map<String, String> options)
		throws PortalException {

		PowwowServiceProvider powwowServiceProvider = getPowwowServiceProvider(
			providerType);

		return powwowServiceProvider.updatePowwowMeeting(
			powwowMeetingId, name, userId, options);
	}

	protected static PowwowServiceProvider getPowwowServiceProvider(
			long powwowMeetingId)
		throws PortalException {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		return getPowwowServiceProvider(powwowMeeting.getProviderType());
	}

	protected static PowwowServiceProvider getPowwowServiceProvider(
		String providerType) {

		return PowwowServiceProviderFactory.getPowwowServiceProvider(
			providerType);
	}

}