/**
 * Copyright 2011 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dbist;

import net.sf.common.util.BeanUtil;

import org.dbist.dml.Dml;

/**
 * The main factory and util class of Dbist framework
 * 
 * @author Steve M. Jung
 * @since 2 June 2011 (version 0.0.1)
 */
public class Dbist {
	public static Dml getDml() throws Exception {
		return BeanUtil.get("dml", Dml.class);
	}
	public static Dml getDml(String name) throws Exception {
		return BeanUtil.get(name, Dml.class);
	}
}
