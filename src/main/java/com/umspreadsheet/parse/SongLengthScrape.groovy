package com.umspreadsheet.parse

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.umspreadsheet.show.Show
@Grapes([
        @Grab(group='com.google.code.gson', module='gson', version='2.8.5'),
        @Grab(group='org.seleniumhq.selenium', module='selenium-java', version='3.14.0')
])

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

final int year = 2018

Map<Date, Show> showMap = new HashMap<>()

Gson gson = new Gson()
JsonArray showArray  = new JsonParser().parse(new FileReader("C:/Users/andre/Desktop/shows/2018.json"))
        .getAsJsonArray()
for (JsonElement showEl : showArray) {
    Show show = gson.fromJson(showEl, Show)
    showMap.put(show.getDate(), show)
}

System.setProperty("webdriver.chrome.driver", "C:/Users/andre/Desktop/chromedriver.exe")
WebDriver driver = new ChromeDriver()
driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

String url = "http://nugs.net/listen"
driver.get(url)

driver.findElementByXPath("/html/body/div[1]/header-component/header/div/ul/li[4]/a").click()

driver.findElementByXPath("//*[@id=\"ctl00_cphAuth_ctl00_frmUsername\"]")
        .sendKeys("andrewosborn93@gmail.com")
driver.findElementByXPath("//*[@id=\"ctl00_cphAuth_ctl00_frmPassword\"]")
        .sendKeys("rolltid3")
driver.findElementByXPath("//*[@id=\"ctl00_cphAuth_ctl00_btnSubmitButton\"]")
        .click()

driver.get("https://play.nugs.net/#/catalog/recording/19428")
String showId = driver.getCurrentUrl().split("/")[6]
String dateString = driver.findElementByXPath("//*[@id=\"container-${showId}\"]/div[1]/div[2]/div/time")
        .getAttribute("datetime").substring(0,10)
Date nugsDate = new SimpleDateFormat("yyy-MM-dd").parse(dateString)

Calendar cal = Calendar.getInstance()
cal.setTime(nugsDate)
while (cal.get(Calendar.YEAR) == year) {

    Show show = showMap.get(nugsDate)
    if (show) {
        List<WebElement> setDivs = driver.findElements(By.cssSelector("div[class*=set-]"))
        if (setDivs.size() == show.getSets().size()) {
            int setNum = 0
            for (WebElement div : setDivs) {
                String setName = (setDivs.size() > 1) ? div.findElement(By.tagName("h3")).text : null
                List<WebElement> setSongs = div.findElements(By.className("track-row"))
                if (show.getSets().get(setNum).getTracks().size() == setSongs.size()) {
                    int songNum = 0
                    for (WebElement row : setSongs) {
                        String song = row.findElement(By.className("song-title")).text
                        Long length = stringToSeconds(row.findElement(By.className("song-runtime")).text)
                        show.getSets().get(setNum).getTracks().get(songNum).setLength(length)
                        println "${song} ${length}"
                        songNum++
                    }
                }
                else {
                    println "${show.getDate()}: ${show.getSets().get(setNum).getName()} length mismatch"
                }
                setNum++
            }
        }
        else {
            println "${show.getDate()}: Set count mismatch"
        }
    }

    // Go to previous show page
    driver.findElementByClassName("prev-link").click()

    showId = driver.getCurrentUrl().split("/")[6]
    dateString = driver.findElementByTagName("time").getAttribute("datetime").substring(0,10)
    nugsDate = new SimpleDateFormat("yyy-MM-dd").parse(dateString)
    cal.setTime(nugsDate)
}

driver.close()

println "Writing file ${year}.json"

ObjectMapper mapper = new ObjectMapper()
mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
String json = mapper.writeValueAsString(new ArrayList<Show>(showMap.values()))
Files.write(Paths.get("C:/Users/andre/Desktop/shows/lengths/${year}.json"), json.getBytes())

static Long stringToSeconds(String lengthStr) {
    String[] tokens = lengthStr.split(":")
    Long min = Long.parseLong(tokens[0])
    Long sec = Long.parseLong(tokens[1])
    return (min * 60) + sec
}
