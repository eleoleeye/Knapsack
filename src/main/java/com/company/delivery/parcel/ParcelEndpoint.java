package com.company.delivery.parcel;

import com.company.delivery.truck.TruckData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parcels")
public class ParcelEndpoint {

    private final ParcelService parcelService;

    @PutMapping()
    public void loadParcels(@RequestBody List<Parcel> parcel) {
        parcelService.save(parcel);
    }

    @GetMapping("/truckLoad/{maxWeightCapacity}/{sortBy}")
    public TruckData truckLoad(@PathVariable Integer maxWeightCapacity, @PathVariable String sortBy) {
        return parcelService.truckLoad(maxWeightCapacity, sortBy);
    }

}
