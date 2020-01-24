package com.company.delivery.truck;

import com.company.delivery.parcel.Parcel;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TruckData {

    private Integer totalWeight;
    private Integer totalValue;
    private List<String> ids;
    private List<Parcel> details;

    public TruckData() {
        this.ids = new ArrayList<>();
        this.details = new ArrayList<>();
        this.totalWeight = 0;
        this.totalValue = 0;
    }
}
