package com.company.delivery.truck;

import com.company.delivery.parcel.Parcel;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TruckDataCollector implements Collector<Parcel, TruckData, TruckData> {

    @Override
    public Supplier<TruckData> supplier() {
        return TruckData::new;
    }

    @Override
    public BiConsumer<TruckData, Parcel> accumulator() {
        return (truckData, parcel) -> {
            truckData.getIds().add(parcel.getId());
            truckData.getDetails().add(parcel);
            truckData.setTotalValue(truckData.getTotalValue() + parcel.getValue());
            truckData.setTotalWeight(truckData.getTotalWeight() + parcel.getWeight());
        };
    }

    @Override
    public BinaryOperator<TruckData> combiner() {
        return (data1, data2) -> {
            data1.getDetails().addAll(data2.getDetails());
            data1.getIds().addAll(data2.getIds());
            data1.setTotalValue(data2.getTotalValue() + data2.getTotalValue());
            data1.setTotalWeight(data2.getTotalWeight() + data2.getTotalWeight());
            return data1;
        };
    }

    @Override
    public Function<TruckData, TruckData> finisher() {
        return (e -> e);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
}