/*
 * Copyright 2013 Goldman Sachs.
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

package com.gs.collections.impl.stack.immutable.primitive;

import com.gs.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import com.gs.collections.api.stack.primitive.ImmutableBooleanStack;
import com.gs.collections.impl.list.mutable.primitive.BooleanArrayList;
import com.gs.collections.impl.stack.mutable.primitive.BooleanArrayStack;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableBooleanArrayStack}.
 */
public class ImmutableBooleanArrayStackTest extends AbstractImmutableBooleanStackTestCase
{
    @Override
    protected ImmutableBooleanStack classUnderTest()
    {
        return ImmutableBooleanArrayStack.newStackWith(true, false, true, false);
    }

    @Test
    public void newWithIterable()
    {
        Assert.assertEquals(BooleanArrayStack.newStackWith(true, true, false), this.newWithIterable(BooleanArrayList.newListWith(true, true, false)));
    }

    @Test
    public void newWithTopToBottom()
    {
        Assert.assertEquals(BooleanArrayStack.newStackFromTopToBottom(true, true, false), this.newWithTopToBottom(true, true, false));
    }

    @Test
    public void injectInto()
    {
        ImmutableBooleanArrayStack stack = ImmutableBooleanArrayStack.newStack(BooleanArrayStack.newStackFromTopToBottom(true, true, false, true, false));
        Integer total = stack.injectInto(Integer.valueOf(0), new ObjectBooleanToObjectFunction<Integer, Integer>()
        {
            public Integer valueOf(Integer result, boolean value)
            {
                if (value)
                {
                    return result += 2;
                }

                return result;
            }
        });
        Assert.assertEquals(Integer.valueOf(6), total);
    }
}
