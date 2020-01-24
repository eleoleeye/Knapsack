package com.company.delivery.utils;

import com.company.delivery.parcel.Parcel;
import java.util.Comparator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComparatorUtils {

    public static Comparator<Parcel> getComparator(final String sortBy) {
        SortType sortType = SortType.getSortType(sortBy);
        switch (sortType) {
            case ID:
                return Comparator.comparing(Parcel::getId);
            case VALUE:
                return Comparator.comparing(Parcel::getValue);
            case WEIGHT:
                return Comparator.comparing(Parcel::getWeight);
            default:
                throw new IllegalStateException();
        }

    }
}

