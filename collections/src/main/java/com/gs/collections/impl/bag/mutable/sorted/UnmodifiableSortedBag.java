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

package com.gs.collections.impl.bag.mutable.sorted;

import java.util.Collection;
import java.util.Comparator;

import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.api.bag.primitive.MutableBooleanBag;
import com.gs.collections.api.bag.primitive.MutableByteBag;
import com.gs.collections.api.bag.primitive.MutableCharBag;
import com.gs.collections.api.bag.primitive.MutableDoubleBag;
import com.gs.collections.api.bag.primitive.MutableFloatBag;
import com.gs.collections.api.bag.primitive.MutableIntBag;
import com.gs.collections.api.bag.primitive.MutableLongBag;
import com.gs.collections.api.bag.primitive.MutableShortBag;
import com.gs.collections.api.bag.sorted.ImmutableSortedBag;
import com.gs.collections.api.bag.sorted.MutableSortedBag;
import com.gs.collections.api.bag.sorted.SortedBag;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.Function2;
import com.gs.collections.api.block.function.primitive.BooleanFunction;
import com.gs.collections.api.block.function.primitive.ByteFunction;
import com.gs.collections.api.block.function.primitive.CharFunction;
import com.gs.collections.api.block.function.primitive.DoubleFunction;
import com.gs.collections.api.block.function.primitive.FloatFunction;
import com.gs.collections.api.block.function.primitive.IntFunction;
import com.gs.collections.api.block.function.primitive.LongFunction;
import com.gs.collections.api.block.function.primitive.ShortFunction;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.predicate.Predicate2;
import com.gs.collections.api.block.predicate.primitive.IntPredicate;
import com.gs.collections.api.block.procedure.primitive.ObjectIntProcedure;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import com.gs.collections.api.partition.bag.sorted.PartitionMutableSortedBag;
import com.gs.collections.api.tuple.Pair;
import com.gs.collections.impl.collection.mutable.UnmodifiableMutableCollection;

/**
 * An unmodifiable view of a SortedBag.
 *
 * @see MutableSortedBag#asUnmodifiable()
 * @since 4.2
 */
public class UnmodifiableSortedBag<T>
        extends UnmodifiableMutableCollection<T>
        implements MutableSortedBag<T>
{
    private static final long serialVersionUID = 1L;

    protected UnmodifiableSortedBag(MutableSortedBag<? extends T> sortedBag)
    {
        super(sortedBag);
    }

    /**
     * This method will take a MutableSortedBag and wrap it directly in a UnmodifiableSortedBag.
     */
    public static <E, S extends MutableSortedBag<E>> UnmodifiableSortedBag<E> of(S bag)
    {
        if (bag == null)
        {
            throw new IllegalArgumentException("cannot create an UnmodifiableSortedBag for null");
        }
        return new UnmodifiableSortedBag<E>(bag);
    }

    protected MutableSortedBag<T> getSortedBag()
    {
        return (MutableSortedBag<T>) this.getMutableCollection();
    }

    @Override
    public MutableSortedBag<T> asUnmodifiable()
    {
        return this;
    }

    @Override
    public MutableSortedBag<T> asSynchronized()
    {
        throw new UnsupportedOperationException("asSynchronized not implemented yet!");
    }

    @Override
    public ImmutableSortedBag<T> toImmutable()
    {
        throw new UnsupportedOperationException("toImmutable not implemented yet!");
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.getSortedBag().equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.getSortedBag().hashCode();
    }

    @Override
    public MutableSortedBag<T> newEmpty()
    {
        return this.getSortedBag().newEmpty();
    }

    public void addOccurrences(T item, int occurrences)
    {
        throw new UnsupportedOperationException("Cannot call addOccurrences() on " + this.getClass().getSimpleName());
    }

    public boolean removeOccurrences(Object item, int occurrences)
    {
        throw new UnsupportedOperationException("Cannot call removeOccurences() on " + this.getClass().getSimpleName());
    }

    @Override
    public MutableSortedBag<T> select(Predicate<? super T> predicate)
    {
        return this.getSortedBag().select(predicate);
    }

    @Override
    public <P> MutableSortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getSortedBag().selectWith(predicate, parameter);
    }

    @Override
    public MutableSortedBag<T> reject(Predicate<? super T> predicate)
    {
        return this.getSortedBag().reject(predicate);
    }

    @Override
    public <P> MutableSortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getSortedBag().rejectWith(predicate, parameter);
    }

    @Override
    public PartitionMutableSortedBag<T> partition(Predicate<? super T> predicate)
    {
        return this.getSortedBag().partition(predicate);
    }

    public int sizeDistinct()
    {
        return this.getSortedBag().sizeDistinct();
    }

    public int occurrencesOf(Object item)
    {
        return this.getSortedBag().occurrencesOf(item);
    }

    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        this.getSortedBag().forEachWithOccurrences(procedure);
    }

    public MutableMap<T, Integer> toMapOfItemToCount()
    {
        return this.getSortedBag().toMapOfItemToCount();
    }

    public String toStringOfItemToCount()
    {
        return this.getSortedBag().toStringOfItemToCount();
    }

    public MutableSortedBag<T> selectByOccurrences(IntPredicate predicate)
    {
        return this.getSortedBag().selectByOccurrences(predicate);
    }

    @Override
    public <S> MutableSortedBag<S> selectInstancesOf(Class<S> clazz)
    {
        return this.getSortedBag().selectInstancesOf(clazz);
    }

    @Override
    public <V> MutableBag<V> collect(Function<? super T, ? extends V> function)
    {
        return this.getSortedBag().collect(function);
    }

    @Override
    public MutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.getSortedBag().collectBoolean(booleanFunction);
    }

    @Override
    public MutableByteBag collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.getSortedBag().collectByte(byteFunction);
    }

    @Override
    public MutableCharBag collectChar(CharFunction<? super T> charFunction)
    {
        return this.getSortedBag().collectChar(charFunction);
    }

    @Override
    public MutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.getSortedBag().collectDouble(doubleFunction);
    }

    @Override
    public MutableFloatBag collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.getSortedBag().collectFloat(floatFunction);
    }

    @Override
    public MutableIntBag collectInt(IntFunction<? super T> intFunction)
    {
        return this.getSortedBag().collectInt(intFunction);
    }

    @Override
    public MutableLongBag collectLong(LongFunction<? super T> longFunction)
    {
        return this.getSortedBag().collectLong(longFunction);
    }

    @Override
    public MutableShortBag collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.getSortedBag().collectShort(shortFunction);
    }

    @Override
    public <V> MutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getSortedBag().flatCollect(function);
    }

    @Override
    public <P, A> MutableBag<A> collectWith(Function2<? super T, ? super P, ? extends A> function, P parameter)
    {
        return this.getSortedBag().collectWith(function, parameter);
    }

    @Override
    public <V> MutableBag<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        return this.getSortedBag().collectIf(predicate, function);
    }

    @Override
    public <V> MutableSortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.getSortedBag().groupBy(function);
    }

    @Override
    public <V> MutableSortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getSortedBag().groupByEach(function);
    }

    @Override
    public <S> MutableBag<Pair<T, S>> zip(Iterable<S> that)
    {
        return this.getSortedBag().zip(that);
    }

    @Override
    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.getSortedBag().zip(that, target);
    }

    @Override
    public MutableBag<Pair<T, Integer>> zipWithIndex()
    {
        return this.getSortedBag().zipWithIndex();
    }

    @Override
    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.getSortedBag().zipWithIndex(target);
    }

    public Comparator<? super T> comparator()
    {
        return this.getSortedBag().comparator();
    }

    @Override
    public MutableSortedBag<T> with(T element)
    {
        throw new UnsupportedOperationException("Cannot call with() on " + this.getClass().getSimpleName());
    }

    @Override
    public MutableSortedBag<T> without(T element)
    {
        throw new UnsupportedOperationException("Cannot call without() on " + this.getClass().getSimpleName());
    }

    @Override
    public MutableSortedBag<T> withAll(Iterable<? extends T> elements)
    {
        throw new UnsupportedOperationException("Cannot call withAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public MutableSortedBag<T> withoutAll(Iterable<? extends T> elements)
    {
        throw new UnsupportedOperationException("Cannot call withoutAll() on " + this.getClass().getSimpleName());
    }

    public int compareTo(SortedBag<T> o)
    {
        return this.getSortedBag().compareTo(o);
    }
}
