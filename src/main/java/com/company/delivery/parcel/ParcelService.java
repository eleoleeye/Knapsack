package com.company.delivery.parcel;

import static com.company.delivery.utils.ComparatorUtils.getComparator;
import static java.util.Comparator.comparing;

import com.company.delivery.truck.TruckData;
import com.company.delivery.truck.TruckDataCollector;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelStore parcelStore;

    void save(final List<Parcel> parcels) {
        parcelStore.save(parcels);
    }

    TruckData truckLoad(final Integer maxWeightCapacity, final String sortBy) {
        List<Parcel> parcels = parcelStore.get();
        TruckData result = loadOptimalParcels(parcels, new MaxWeightLimit(maxWeightCapacity), sortBy);
        parcelStore.removeAllParcelsForNextTestData();
        return result;
    }

    public TruckData loadOptimalParcels(final List<Parcel> parcels, final MaxWeightLimit weightLimit, final String sortBy) {
        return parcels.stream()
                .sorted(comparing(this::getBenefitIndex).reversed())
                .filter(parcel -> hasFreeSpace(weightLimit, parcel))
                .map(parcel -> addWeightToTruck(weightLimit, parcel))
                .sorted(getComparator(sortBy))
                .collect(new TruckDataCollector());
    }

    private boolean hasFreeSpace(final MaxWeightLimit limit, final Parcel parcel) {
        return limit.getWeight() - parcel.getWeight() >= 0;
    }

    private Parcel addWeightToTruck(final MaxWeightLimit limit, final Parcel parcel) {
        limit.setWeight(limit.getWeight() - parcel.getWeight());
        return parcel;
    }

    private Double getBenefitIndex(Parcel parcel) {
        return (double) parcel.getValue() / (double) parcel.getWeight();
    }
}
