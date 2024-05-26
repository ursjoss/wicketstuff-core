/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.jquery.ui.plugins.wysiwyg.toolbar;

import org.apache.wicket.Component;
import org.wicketstuff.jquery.ui.plugins.wysiwyg.WysiwygEditor;

/**
 * Specifies a toolbar to be used by a {@link WysiwygEditor}
 *
 * @author sebfz1
 * @author solomax
 * @author andunslg
 */
public interface IWysiwygToolbar
{
	/**
	 * Attaches the {@link IWysiwygToolbar} to the editor {@link Component}
	 *
	 * @param editor the {@link Component} to attach the {@link IWysiwygToolbar} to
	 */
	void attachToEditor(Component editor);
}
