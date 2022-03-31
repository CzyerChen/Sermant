/*
 * Copyright (C) 2021-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.gray.dubbo.strategy.type;

import com.huawei.gray.dubbo.strategy.TypeStrategy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表匹配策略测试
 *
 * @author provenceee
 * @since 2021-12-01
 */
public class ListTypeStrategyTest {
    /**
     * 测试列表策略
     */
    @Test
    public void testValue() {
        TypeStrategy strategy = new ListTypeStrategy();
        List<String> list = new ArrayList<>();
        list.add("foo");
        list.add(null);

        // 正常情况
        Assert.assertEquals("foo", strategy.getValue(list, ".get(0)").orElse(null));

        // 测试null
        Assert.assertNotEquals("foo", strategy.getValue(list, ".get(1)").orElse(null));

        // 测试越界
        Assert.assertNotEquals("foo", strategy.getValue(list, ".get(2)").orElse(null));

        // 测试非数组
        Assert.assertNotEquals("foo", strategy.getValue("bar", ".get(0)").orElse(null));

        // 测试不等于
        Assert.assertNotEquals("bar", strategy.getValue(list, ".get(0)").orElse(null));
    }
}