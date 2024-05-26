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
package org.wicketstuff.jquery.ui.form.datepicker;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.io.IClusterable;

/**
 * Event listener shared by the {@link DatePicker} widget and the {@link DatePickerBehavior}
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public interface IDatePickerListener extends IClusterable
{
	/**
	 * Indicates whether the 'onSelect' event is enabled.<br>
	 * If true, the {@link #onSelect(AjaxRequestTarget, String)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isOnSelectEventEnabled();

	/**
	 * Triggered when a selection has been made
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param date the selected date
	 */
	void onSelect(AjaxRequestTarget target, String date);
}
