package com.anelcc.harrypotter.products;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductViewModel implements Parcelable {

    private Product product;
    public String title, author, image;
    public boolean isFavorite;
    public int position;

    public ProductViewModel(Product product) {
        this.product = product;
    }

    protected ProductViewModel(Parcel in) {
        product = in.readParcelable(Product.class.getClassLoader());
        title = in.readString();
        author = in.readString();
        image = in.readString();
        isFavorite = in.readByte() != 0;
        position = in.readInt();
    }

    public static final Creator<ProductViewModel> CREATOR = new Creator<ProductViewModel>() {
        @Override
        public ProductViewModel createFromParcel(Parcel in) {
            return new ProductViewModel(in);
        }

        @Override
        public ProductViewModel[] newArray(int size) {
            return new ProductViewModel[size];
        }
    };

    public String getTitle() {
        return product.getTitle();
    }

    public String getAuthor() {
        return product.getAuthor();
    }

    public String getImage() {
        return product.getImageURL();
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getPosition() {
        return position;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product, flags);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(image);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeInt(position);
    }
}
