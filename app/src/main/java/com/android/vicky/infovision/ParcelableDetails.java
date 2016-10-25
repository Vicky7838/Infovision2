package com.android.vicky.infovision;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vicky on 10/25/2016.
 */

public class ParcelableDetails implements Parcelable {
    private DetailsModel detailsModel;

    public DetailsModel getDetailsModel() {
        return detailsModel;
    }

    public ParcelableDetails(DetailsModel laptop) {
        super();
        this.detailsModel = laptop;
    }

    private ParcelableDetails(Parcel in) {
        detailsModel = new DetailsModel();
        detailsModel.setName(in.readString());
        detailsModel.setEmail(in.readString());
        detailsModel.setPhone(in.readString());
        detailsModel.setPassword(in.readString());
        detailsModel.setAge(in.readString());
        detailsModel.setGender(in.readString());
        detailsModel.setProfession(in.readString());
    }

    /*
     * you can use hashCode() here.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /*
     * Actual object Serialization/flattening happens here. You need to
     * individually Parcel each property of your object.
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(detailsModel.getName());
        parcel.writeString(detailsModel.getEmail());
        parcel.writeString(detailsModel.getPhone());
        parcel.writeString(detailsModel.getPassword());
        parcel.writeString(detailsModel.getAge());
        parcel.writeString(detailsModel.getGender());
        parcel.writeString(detailsModel.getProfession());

    }

    /*
     * Parcelable interface must also have a static field called CREATOR,
     * which is an object implementing the Parcelable.Creator interface.
     * Used to un-marshal or de-serialize object from Parcel.
     */
    public static final Parcelable.Creator<ParcelableDetails> CREATOR =
            new Parcelable.Creator<ParcelableDetails>() {
                public ParcelableDetails createFromParcel(Parcel in) {
                    return new ParcelableDetails(in);
                }

                public ParcelableDetails[] newArray(int size) {
                    return new ParcelableDetails[size];
                }
            };
}
