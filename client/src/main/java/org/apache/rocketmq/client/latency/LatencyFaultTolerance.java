/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.client.latency;

public interface LatencyFaultTolerance<T> {
    /**
     * @description 更新失败条目
     * @param name brkerNamer
     * @param currentLatency 消息发送故障延迟时间
     * @param notAvailableDuration  不可用持续时间，在这个时间内，broker将被规避
     * @return void
     * @author qrc
     * @date 2019/9/12
     */
    void updateFaultItem(final T name, final long currentLatency, final long notAvailableDuration);

    /**
     * @description 判断broker是否可用
     * @param name broker名称
     * @return boolean
     * @author qrc
     * @date 2019/9/12
     */
    boolean isAvailable(final T name);

    /**
     * @description 移除Fault条目，意味着Broker重新参与路由计算
     * @param name
     * @return void
     * @author qrc
     * @date 2019/9/12
     */
    void remove(final T name);

    /**
     * @description 尝试从规避的broker中选择一个可用的broker，如果没有找到，将返回null
     * @param
     * @return T
     * @author qrc
     * @date 2019/9/12
     */
    T pickOneAtLeast();
}
