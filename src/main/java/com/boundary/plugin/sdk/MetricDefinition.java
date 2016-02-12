// Copyright 2014-2015 Boundary, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.boundary.plugin.sdk;

import java.io.Serializable;

/**
 * Defines a Boundary Metric
 * 
 * @see <a href="https://help.boundary.com/hc/en-us/articles/201990001-Create-or-Update-Metric">https://help.boundary.com/hc/en-us/articles/201990001-Create-or-Update-Metric</a>
 */
public class MetricDefinition implements Serializable {
	
	private static final long serialVersionUID = -3245328652144720304L;
	
	protected String name;
	protected String displayName;
	protected String displayNameShort;
	protected String description;
	protected long defaultResolutionMS;
	protected MetricAggregate defaultAggregate;
	protected MetricUnit unit;
	protected boolean isDisabled;
	
	/**
	 * Name of the metric, must be globally unique if creating
	 * @return {@link String} metric identifier
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Short name to use when referring to the metric (optional if updating)
	 * @return {@link String}
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Terse short name when referring to the metric and space is limited,
	 * less than 15 characters preferred.
	 * 
	 * Optional if updating
	 * 
	 * @return {@link String}
	 */
	public String getDisplayNameShort() {
		return displayNameShort;
	}
	
	/**
	 * Description of the metric (optional if updating)
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Expected polling time of data in milliseconds.
	 * Used to improve rendering of graphs
	 * for non-one-second polled metrics.
	 * 
	 * Optional if updating.
	 * 
	 * @return long
	 */
	public long getDefaultResolutionMS() {
		return defaultResolutionMS;
	}
	
	/**
	 * When graphing (or grouping at the 1 second interval) the aggregate function
	 * that makes most sense for this metric. Can be:
	 * <ul>
	 * <li>avg</li>
	 * <li>max</li>
	 * <li>min</li>
	 * </ul>
	 * 
	 * Optional if updating
	 * @return {@link MetricAggregate}
	 */
	public MetricAggregate getDefaultAggregate() {
		return defaultAggregate;
	}
	
	/**
	 * The units of measurement for the metric can be :
	 * <ul>
	 * <li>percent</li>
	 * <li>number</li>
	 * <li>bytecount</li>
	 * <li>duration</li>
	 * </ul>
	 * 
	 * Optional if updating
	 * 
	 * @return {@link MetricUnit}
	 */
	public MetricUnit getUnit() {
		return unit;
	}
	
	/**
	 * Is this metric disabled
	 * 
	 * Optional if updating
	 * 
	 * @return boolean
	 */
	public boolean getIsDisabled() {
		return isDisabled;
	}

	@Override
	public String toString() {
		return "MetricDefinition [name=" + name + ", displayName="
				+ displayName + ", displayNameShort=" + displayNameShort
				+ ", description=" + description + ", defaultResolutionMS="
				+ defaultResolutionMS + ", defaultAggregate="
				+ defaultAggregate + ", unit=" + unit + ", isDisabled="
				+ isDisabled + "]";
	}

}
