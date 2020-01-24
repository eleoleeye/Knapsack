package com.company.delivery.parcel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

@Service
public class ParcelStore {

    private List<Parcel> parcels = new CopyOnWriteArrayList<>();

    public boolean save(final List<Parcel> newParcels) {
        return parcels.addAll(newParcels);
    }

    public List<Parcel> get() {
        return parcels;
    }

    public void removeAllParcelsForNextTestData() {
        parcels.clear();
    }
}
