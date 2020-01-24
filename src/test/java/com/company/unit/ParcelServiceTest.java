package com.company.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.company.delivery.parcel.MaxWeightLimit;
import com.company.delivery.parcel.Parcel;
import com.company.delivery.parcel.ParcelService;
import com.company.delivery.parcel.ParcelStore;
import com.company.delivery.truck.TruckData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParcelServiceTest {

    public static final int WEIGHT_LIMIT = 19;
    public static final int EXPECTED_IDS_SIZE = 3;
    public static final int EXPECTED_TOTAL_VALUE = 39;
    public static final int EXPECTED_TOTAL_WEIGHT = 19;

    private static final List<Parcel> parcels = Arrays.asList(
            new Parcel("P1", 10, 9),
            new Parcel("P2", 9, 8),
            new Parcel("P3", 8, 9),
            new Parcel("P4", 7, 8),
            new Parcel("P5", 6, 6),
            new Parcel("P6", 5, 5),
            new Parcel("P7", 4, 22),
            new Parcel("P8", EXPECTED_IDS_SIZE, EXPECTED_IDS_SIZE)
    );

    private static final List<Parcel> parcelsResultSortById = Arrays.asList(
            new Parcel("P3", 8, 9),
            new Parcel("P4", 7, 8),
            new Parcel("P7", 4, 22));

    private static final List<Parcel> parcelsResultSortByValue = Arrays.asList(
            new Parcel("P4", 7, 8),
            new Parcel("P3", 8, 9),
            new Parcel("P7", 4, 22));

    private static final List<Parcel> parcelsResultSortByWeight = Arrays.asList(
            new Parcel("P7", 4, 22),
            new Parcel("P4", 7, 8),
            new Parcel("P3", 8, 9));

    private static ParcelService parcelService;

    @BeforeAll
    public static void before() {
        parcelService = new ParcelService(new ParcelStore());
    }

    @ParameterizedTest
    @MethodSource("provideParcelsSortByValue")
    public void givenLoadTruck_whenSortByValue_thenCorrectResultSortByValue(List<Parcel> parcels,
                                                                            MaxWeightLimit maxWeight,
                                                                            String sortBy) {
        TruckData result = parcelService.loadOptimalParcels(parcels, maxWeight, sortBy);
        assertIterableEquals(parcelsResultSortByValue, result.getDetails());
        assertEquals(EXPECTED_TOTAL_VALUE, result.getTotalValue());
        assertEquals(EXPECTED_TOTAL_WEIGHT, result.getTotalWeight());
        assertEquals(EXPECTED_IDS_SIZE, result.getIds().size());
    }

    @ParameterizedTest
    @MethodSource("provideParcelsSortById")
    public void givenLoadTruck_whenSortById_thenCorrectResultSortById(List<Parcel> parcels, MaxWeightLimit maxWeight,
                                                                      String sortBy) {
        TruckData result = parcelService.loadOptimalParcels(parcels, maxWeight, sortBy);
        assertIterableEquals(parcelsResultSortById, result.getDetails());
        assertEquals(EXPECTED_TOTAL_VALUE, result.getTotalValue());
        assertEquals(EXPECTED_TOTAL_WEIGHT, result.getTotalWeight());
        assertEquals(EXPECTED_IDS_SIZE, result.getIds().size());
    }

    @ParameterizedTest
    @MethodSource("provideParcelsSortByWeight")
    public void givenLoadTruck_whenSortByWeight_thenCorrectResultSortByWeight(List<Parcel> parcels,
                                                                              MaxWeightLimit maxWeight, String sortBy) {
        TruckData result = parcelService.loadOptimalParcels(parcels, maxWeight, sortBy);
        assertIterableEquals(parcelsResultSortByWeight, result.getDetails());
        assertEquals(EXPECTED_TOTAL_VALUE, result.getTotalValue());
        assertEquals(EXPECTED_TOTAL_WEIGHT, result.getTotalWeight());
        assertEquals(EXPECTED_IDS_SIZE, result.getIds().size());
    }

    static Stream<Arguments> provideParcelsSortByValue() {
        return Stream.of(Arguments.of(parcels, new MaxWeightLimit(WEIGHT_LIMIT), "value"));
    }

    static Stream<Arguments> provideParcelsSortById() {
        return Stream.of(Arguments.of(parcels, new MaxWeightLimit(WEIGHT_LIMIT), "id"));
    }

    static Stream<Arguments> provideParcelsSortByWeight() {
        return Stream.of(Arguments.of(parcels, new MaxWeightLimit(WEIGHT_LIMIT), "weight"));
    }

}
