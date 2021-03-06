package me.ibrokhim.lab3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;

public class Student implements Parcelable {
    @NonNull
    public String firstName;
    @NonNull
    public String middleName;
    @NonNull
    public String lastName;

    public Student(@NonNull String firstName, @NonNull String middleName, @NonNull String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    protected Student(Parcel in) {
        firstName = in.readString();
        middleName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.firstName == other.firstName
            && this.middleName == other.middleName
            && this.lastName == other.lastName
        ;
    }

    @Override
    public int hashCode() {
        return ObjectsCompat.hash(firstName, middleName, lastName);
    }

    public String getFullname() {
        return firstName + " " + middleName + " " + lastName;
    }
}
