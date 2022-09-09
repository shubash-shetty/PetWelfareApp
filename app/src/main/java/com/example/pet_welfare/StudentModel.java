package com.example.pet_welfare;

import java.io.Serializable;

public class StudentModel
{
    String Name;
    String Age;
    String Weight;
    String Category;
    String OwnerName;
    String BuyingPrice;
    String PricePerYear;
    String OwnerPhoneNumber;
    String Address;
    String Gender;
    String Vaccinated;
    String VaccinatedNo;

    String image;
    String Breed;

    public StudentModel() {
    }

    public StudentModel(String name, String age, String weight, String gender, String breed, String image,String owner, String ownerphn,String perprice,String buyprice,String category,String address,String vacc,String novacc) {
        this.Name = name;
        this.Age = age;
        this.Weight = weight;
        this.Gender = gender;
        this.Breed = breed;
        this.image=image;
        this.Category=category;
        this.OwnerName=owner;
        this.OwnerPhoneNumber=ownerphn;
        this.Address=address;
        this.BuyingPrice=buyprice;
        this.PricePerYear=perprice;
        this.Vaccinated=vacc;
        this.VaccinatedNo=novacc;
    }


    public String getVaccinated() {
        return Vaccinated;
    }

    public void setVaccinated(String vacc) {
        this.Vaccinated = vacc;
    }

    public String getVaccinatedNo() {
        return VaccinatedNo;
    }

    public void setVaccinatedNo(String novacc) {
        this.VaccinatedNo = novacc;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String owner) {
        this.OwnerName = owner;
    }

    public String getOwnerPhoneNumber() {
        return OwnerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerphn) {
        this.OwnerPhoneNumber = ownerphn;
    }

    public String getBuyingPrice() {
        return BuyingPrice;
    }

    public void setBuyingPrice(String buyprice) {
        this.BuyingPrice = buyprice;
    }

    public String getPricePerYear() {
        return PricePerYear;
    }

    public void setPricePerYear(String perprice) {
        this.PricePerYear = perprice;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }






    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        this.Age = age;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        this.Breed = breed;
    }
}
