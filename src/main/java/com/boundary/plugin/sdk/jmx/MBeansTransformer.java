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

package com.boundary.plugin.sdk.jmx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.google.gson.Gson;

public class MBeansTransformer<E> {
	
	private JMXClient client;
	private MBeanTransform<E> transform;

	MBeansTransformer(JMXClient client,MBeanTransform<E> transform,String prefix) {
		this.client = client;
		this.transform = transform;
		transform.setPrefix(prefix);
	}
	
	/**
	 * Iterates over the attributes of an MBean
	 * @param name {@link ObjectName}
	 */
	private void traverseAttributes(ObjectName name) {
		MBeanServerConnection connection = this.client.getMBeanServerConnection();
		MBeanInfo info;
		HashSet<String> checkTypes = new HashSet<String>();
		checkTypes.add("long");
		checkTypes.add("int");
		checkTypes.add("javax.management.openmbean.CompositeData");
		checkTypes.add("[Ljavax.management.openmbean.CompositeData;");
		try {
			info = connection.getMBeanInfo(name);
			MBeanAttributeInfo[] attributes = info.getAttributes();
			for (MBeanAttributeInfo attrInfo : attributes) {
				if (checkTypes.contains(attrInfo.getType())) {
					transform.beginAttribute(name,attrInfo);
					transform.endAttribute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void traverseMBeans() {
		MBeanServerConnection connection = this.client.getMBeanServerConnection();
		try {
			Set<ObjectName> mbeans = connection.queryNames(null, null);
			List<ObjectName> mbeanList = new ArrayList<ObjectName>(mbeans);
			Collections.sort(mbeanList);
			for (ObjectName obj : mbeans) {
				transform.beginMBean(obj);
				traverseAttributes(obj);
				transform.endMBean();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Transform MBeans data to a different by calling an implementation
	 * of {@link MBeanTransform}
	 */
	public void transform() {
		traverseMBeans();
	}
	
	public void convertToJson() {

		Gson gson = new Gson();
		System.out.print(gson.toJson(this.export()));
	}
	
	public E export() {
		E export = transform.getExport();
		return export;
	}
}
