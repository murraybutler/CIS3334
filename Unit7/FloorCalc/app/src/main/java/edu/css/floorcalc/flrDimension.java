package edu.css.floorcalc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by murray.butler on 2/28/18.
 */

public class flrDimension implements Parcelable{
    private double width;
    private double length;

    public flrDimension(double iwidth, double ilength) {
        this.width = iwidth;
        this.length = ilength;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public flrDimension(Parcel in) {
        this.width = in.readDouble();
        this.length = in.readDouble();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(width);
        dest.writeDouble(length);
    }

    public static final Creator<flrDimension> CREATOR = new Creator<flrDimension>() {
        @Override
        public flrDimension createFromParcel(Parcel source) {
            return new flrDimension(source);
        }

        @Override
        public flrDimension[] newArray(int size) {
            return null;
        }
    };
}
