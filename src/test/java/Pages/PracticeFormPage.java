package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PracticeFormPage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    public PracticeFormPage() {
        PageFactory.initElements(driver, this);
    }

    //-------------zatrebace---------------------------

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    //-------------------------------------------------




    //-------------------------------------------------

    @FindBy(css = ".element-list.collapse.show")
    public WebElement practiceFormButton;

    public void clickOnPracticeFormButton() {
        scrollToElement(practiceFormButton);
        practiceFormButton.click();
    }

    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    public void inputFirstName(String firstName) {
        scrollToElement(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(css = "input[placeholder='name@example.com']")
    public WebElement emailField;

    public void inputEmail(String mail) {
        emailField.clear();
        emailField.sendKeys(mail);
    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(className = "custom-control-label")
    public List<WebElement> genderHobbiesButtons;//u sebi ima elemente i za gender i za hobbie

    public void clickOnGender(String gender) {
        if (gender.equals("Male"))
            genderHobbiesButtons.get(0).click();
        else if (gender.equals("Female"))
            genderHobbiesButtons.get(1).click();
        else
            genderHobbiesButtons.get(2).click();

    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(css = "input[placeholder='Mobile Number']")
    public WebElement mobileField;

    public void inputMobile(String phoneNumber) {
        mobileField.sendKeys(phoneNumber);
    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(tagName = "option")
    public List<WebElement> monthYearOptions; // monthYearOptions su elementi koji u sebi imaju, kada bi
    //se nad njima pozvao metod getText() sledece stringove REDOM:
    //January, February, ...., December, 1900, 1901, ..., 2100

    @FindBy(className = "react-datepicker__day")
    public List<WebElement> days;


    //kad budem trazio godinu u monthYerasOptions imacu 201 godnu da pretraazujem
    //to ptilicno sporo radi, pa sam se odlucio da primenim binarnu pretragu
    public static int binarySearch(List<WebElement> array, int l, int r, int x) {
        while (l <= r) {
            int mid = (l + r) / 2;

            if (Integer.valueOf(array.get(mid).getText()) == x) {
                return mid;
            } else if (Integer.valueOf(array.get(mid).getText()) > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public void inputDateOfBirth(String date) {

        String[] dateParts = date.split("\\.");

        int day = Integer.valueOf(dateParts[0]);
        int month = Integer.valueOf(dateParts[1]) - 1;
        int year = Integer.valueOf(dateParts[2]);

        scrollToElement(dateOfBirthInput);
        dateOfBirthInput.click();

        for (int i = 0; i < 12; i++) {
            if (i == month)
                monthYearOptions.get(i).click();
        }

        int positionYear = binarySearch(monthYearOptions, 12, monthYearOptions.size() - 1, year);
        monthYearOptions.get(positionYear).click();



        //ovde treba voditi racuna da ne uzme dan iz prehodnog meseca
        //zato prvo trazim prvi dan u mesecu i odatle krecem pretragu
        int i = 0;
        while(Integer.valueOf(days.get(i).getText()) != 1) {
            i++;
        }

        for(int j = i; j < days.size(); j++) {
            if (Integer.valueOf(days.get(j).getText()) == day) {
                days.get(j).click();
                break;
            }
        }

    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(id = "subjectsInput")
    public WebElement subjectsInput;

    public void inputSubject(ArrayList<String> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            subjectsInput.sendKeys(subjects.get(i));
            subjectsInput.sendKeys(Keys.ENTER);
        }
    }
    //-------------------------------------------------




    //-------------------------------------------------
    public void clickOnHobbies(ArrayList<String> hobbies) {
        WebElement sports = genderHobbiesButtons.get(3);
        WebElement reading = genderHobbiesButtons.get(4);
        WebElement music = genderHobbiesButtons.get(5);

        for (int i = 0; i < hobbies.size(); i++) {
            if (hobbies.get(i).equalsIgnoreCase(sports.getText())) {
                sports.click();
                continue;
            }
            if (hobbies.get(i).equalsIgnoreCase(reading.getText())) {
                reading.click();
                continue;
            }
            if (hobbies.get(i).equalsIgnoreCase(music.getText())) {
                music.click();
            }

        }

    }
    //-------------------------------------------------



    //-------------------------------------------------
    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureInput;

    public void uploadPicture(String photo) {
        String imagePath = Paths.get("src/test/java/Pages/"+photo).toAbsolutePath().toString();
        uploadPictureInput.sendKeys(imagePath);
    }
    //-------------------------------------------------




    //-------------------------------------------------
    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;
    ;

    public void inputCurrentAddress(String address) {
        currentAddressInput.sendKeys(address);
    }
    //-------------------------------------------------



    //-------------------------------------------------
    @FindBy(id = "react-select-3-input")
    public WebElement stateField;

    @FindBy(id = "react-select-4-input")
    public WebElement cityField;

    public void inputState(String state) {
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);
    }

    public void inputCity(String city) {
        cityField.sendKeys(city);
        cityField.sendKeys(Keys.ENTER);
    }
    //-------------------------------------------------



    //-------------------------------------------------
    @FindBy(css = ".btn.btn-primary")
    public WebElement submitButton;

    public void clickOnSubmitButton() {
        submitButton.click();
    }
    //-------------------------------------------------

    @FindBy(css = "td")
    public List<WebElement> cells;

    //--------------------------------------------------
    public void assertOutput(String firstName, String lastName, String validEmail, String gender,
                                   String validPhoneNumber, String dateOfBirth, ArrayList<String> subjects,ArrayList<String> hobbies, String picture, String currentAddress,
                                   String state, String city) {

        Assert.assertEquals(cells.get(1).getText(),  firstName+" "+lastName);
        Assert.assertEquals(cells.get(3).getText(),  validEmail);
        Assert.assertEquals(cells.get(5).getText(),  gender);
        Assert.assertEquals(cells.get(7).getText(),  validPhoneNumber);

        String[] dateParts = dateOfBirth.split("\\.");
        String day = dateParts[0];
        String month =  dateParts[1];
        String year = dateParts[2];

        //za ovo verovatno postoji ugradjena funckija, ali me nije mrezlo rucno da radim (:
        String monthString = "";
        switch (month) {
            case "01" :
                monthString = "January";
                break;
            case "02" :
                monthString = "February";
                break;
            case "03" :
                monthString = "March";
                break;
            case "04" :
                monthString = "April";
                break;
            case "05" :
                monthString = "May";
                break;
            case "06" :
                monthString = "Jun";
                break;
            case "07" :
                monthString = "July";
                break;
            case "08" :
                monthString = "August";
                break;
            case "09" :
                monthString = "September";
                break;
            case "10" :
                monthString = "October";
                break;
            case "11" :
                monthString = "November";
                break;
            case "12" :
                monthString = "December";
                break;
            default: break;
        }

        String datum = day + " " + monthString + "," + year;
        Assert.assertEquals(cells.get(9).getText(), datum);


        for(int i = 0; i < subjects.size(); i++)
            Assert.assertTrue(cells.get(11).getText().contains(subjects.get(i)));

        for(int i = 0; i < hobbies.size(); i++)
            Assert.assertTrue(cells.get(13).getText().contains(hobbies.get(i)));

        Assert.assertEquals(cells.get(15).getText(), picture);

        Assert.assertEquals(cells.get(17).getText(),  currentAddress);
        Assert.assertEquals(cells.get(19).getText(),  state+" "+city);

    }
}
