/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.mailbox;

import com.liferay.mail.reader.attachment.AttachmentHandler;
import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.model.Folder;
import com.liferay.mail.reader.model.MailFile;
import com.liferay.mail.reader.model.Message;
import com.liferay.mail.reader.model.MessagesDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.io.IOException;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * @author Scott Lee
 */
public interface Mailbox {

	public Account addAccount(
			String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			boolean defaultSender)
		throws PortalException;

	public Folder addFolder(String displayName) throws PortalException;

	public void deleteAccount() throws PortalException;

	public void deleteAttachment(long attachmentId) throws PortalException;

	public void deleteFolder(long folderId) throws PortalException;

	public void deleteMessages(long folderId, long[] messageIds)
		throws PortalException;

	public Account getAccount();

	public AttachmentHandler getAttachment(long attachmentId)
		throws IOException, PortalException;

	public Message getMessage(
			long folderId, String keywords, int messageNumber,
			String orderByField, String orderByType)
		throws PortalException;

	public MessagesDisplay getMessagesDisplay(
			long folderId, String keywords, int pageNumber, int messagesPerPage,
			String orderByField, String orderByType)
		throws PortalException;

	public User getUser();

	public boolean hasNewMessages(long folderId) throws PortalException;

	public void moveMessages(long folderId, long[] messageIds)
		throws PortalException;

	public InternetAddress[] parseAddresses(String addresses)
		throws PortalException;

	public void renameFolder(long folderId, String displayName)
		throws PortalException;

	public Message saveDraft(
			long accountId, long messageId, String to, String cc, String bcc,
			String subject, String body, List<MailFile> mailFiles)
		throws PortalException;

	public void sendMessage(long accountId, long messageId)
		throws PortalException;

	public void setAccount(Account account);

	public void setUser(User user);

	public void synchronize() throws PortalException;

	public void synchronizeFolder(long folderId) throws PortalException;

	public void synchronizeMessage(long messageId) throws PortalException;

	public void synchronizePage(
			long folderId, int pageNumber, int messagesPerPage)
		throws PortalException;

	public Account updateAccount(
			long accountId, String personalName, String password,
			boolean savePassword, String signature, boolean useSignature,
			String folderPrefix, boolean defaultSender)
		throws PortalException;

	public void updateFlags(
			long folderId, long[] messageIds, int flag, boolean value)
		throws PortalException;

	public void updateFolders() throws PortalException;

	public void updateFolders(
			long inboxFolderId, long draftFolderId, long sentFolderId,
			long trashFolderId)
		throws PortalException;

	public void validateAccount(
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password)
		throws PortalException;

}