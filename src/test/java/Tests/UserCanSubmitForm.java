package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UserCanSubmitForm extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://demoqa.com/");
    }

    @Test
    public void userCanSubmitFormWithAllFieldsFilled()  {
        homepagePage.clickOnCard("Forms");
        practiceFormPage.clickOnPracticeFormButton();

        String firstName = "Taylor";
        String lastName = "Swift";
        String validEmail = "enigma@christopher.com";
        String gender  = "Female";
        String validPhoneNumber = "1313131313";
        String dateOfBirth = "13.12.1988.";//format: dd.mm.gggg.
        String picture = "tay.png";
        String currentAddress = "Cornelia Street 42";
        String state = "Rajasthan";
        String city  = "Jaipur";


        practiceFormPage.inputFirstName(firstName);
        practiceFormPage.inputLastName(lastName);
        practiceFormPage.inputEmail(validEmail);
        practiceFormPage.clickOnGender(gender);
        practiceFormPage.inputMobile(validPhoneNumber);
        practiceFormPage.inputDateOfBirth(dateOfBirth);

        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Maths"); subjects.add("Computer Science"); subjects.add("Arts");
        practiceFormPage.inputSubject(subjects);

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Sports"); hobbies.add("Music");
        practiceFormPage.clickOnHobbies(hobbies);

        practiceFormPage.uploadPicture(picture);
        practiceFormPage.inputCurrentAddress(currentAddress);
        practiceFormPage.inputState(state);
        practiceFormPage.inputCity(city);

        practiceFormPage.clickOnSubmitButton();

        Assert.assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("closeLargeModal")).isDisplayed());

        practiceFormPage.assertOutput(firstName, lastName, validEmail, gender, validPhoneNumber,
                dateOfBirth, subjects, hobbies, picture, currentAddress, state, city);
    }



}
