package com.example.pokemoncarmelo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon")
public class Pokemon implements Parcelable {

        @PrimaryKey(autoGenerate = true)
        private long id;

        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "type")
        private String type;

        @ColumnInfo(name = "imageID")
        private String imageID;

        protected Pokemon(Parcel in) {
            id = in.readLong();
            name = in.readString();
            type = in.readString();
            imageID = in.readString();
        }

        public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
            @Override
            public Pokemon createFromParcel(Parcel in) {
                return new Pokemon(in);
            }

            @Override
            public Pokemon[] newArray(int size) {
                return new Pokemon[size];
            }
        };

        public Pokemon(){

        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImageID() {
            return imageID;
        }

        public void setImageID(String imageID) {
            this.imageID = imageID;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(name);
            dest.writeString(type);
            dest.writeString(imageID);
        }

}
