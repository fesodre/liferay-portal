/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.bookmarks.uad.exporter;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.uad.constants.BookmarksUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the bookmarks entry UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link BookmarksEntryUADExporter}.
 * </p>
 *
* @author Pei-Jung Lan
 */
public abstract class BaseBookmarksEntryUADExporter
	extends DynamicQueryUADExporter<BookmarksEntry> {

	@Override
	public Class<BookmarksEntry> getTypeClass() {
		return BookmarksEntry.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return bookmarksEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return BookmarksUADConstants.USER_ID_FIELD_NAMES_BOOKMARKS_ENTRY;
	}

	@Override
	protected String toXmlString(BookmarksEntry bookmarksEntry) {
		StringBundler sb = new StringBundler(36);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bookmarks.model.BookmarksEntry");
		sb.append("</model-name>");

		sb.append("<column><column-name>entryId</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getEntryId());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>userId</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getUserId());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>userName</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>statusByUserId</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getStatusByUserId());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>statusByUserName</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getStatusByUserName());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>name</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getName());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>url</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getUrl());
		sb.append("]]></column-value></column>");

		sb.append("<column><column-name>description</column-name>");
		sb.append("<column-value><![CDATA[");
		sb.append(bookmarksEntry.getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected BookmarksEntryLocalService bookmarksEntryLocalService;

}