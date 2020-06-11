package com.google.sps.data;

import com.google.auto.value.AutoValue;

/** An iceberg sighting */
@AutoValue
public abstract class Iceberg {
    public static Iceberg create(double lat, double lng, String size) {
        return new AutoValue_Iceberg(lat,lng,size);
    }

    abstract double lat();
    abstract double lng();
    abstract String size();
}
