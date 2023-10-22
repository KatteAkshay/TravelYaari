package Travelyaari;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Travelyaari_AvailableBusName {

	public static void main(String[] args) throws Exception {
		// disable popups
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--disable-notifications");
				
				// initilize webdriver
				WebDriver driver = new ChromeDriver(op);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.manage().window().maximize();
				driver.get("https://www.travelyaari.com/offers");


				// click book now
				driver.findElement(By.xpath("//div[@class='book-btn']")).click();
				Thread.sleep(1000);
				
				
				//Enter from city
				driver.findElement(By.xpath("//input[@id='from-city']")).sendKeys("Pune");
				Thread.sleep(1000);
				
				
				// Enter to Destination
				driver.findElement(By.xpath("//input[@id='to-city']")).sendKeys("Bangalore");
				Thread.sleep(2000);
				
				
			
				// select destination date
				
				String months = "November";
				String yr     ="2023";
				String dt     = "18";
				
				// click on date textbox
				WebElement dateBox = driver.findElement(By.xpath("//input[@id='from-date']"));
				dateBox.click();
				
				// handle the drop down using select class
				Select month = new Select(driver.findElement(By.xpath("(//select[@class='pika-select pika-select-month'])[1]")));
			    month.selectByVisibleText(months);
				
			    Select year = new Select(driver.findElement(By.xpath("(//select[@class='pika-select pika-select-year'])[1]")));
			    year.selectByValue(yr);

			    dateBox.click();
			    
			    List<WebElement> cal = driver.findElements(By.xpath("(//table)[1]//td"));
			    
			    for(WebElement date : cal)
			    {
			    	String a = date.getText();
			    	
			    	if(a.equals(dt))
			    	{
			    		date.click();
			    		break;
			    	}
			    }
			
				  // search button ---> click
				   driver.findElement(By.xpath("//button[@class='ty-button ty-button-orange']")).click();

				  //list of all the seat 
				  List<WebElement> AvailableSeats = driver.findElements(By.xpath("//div[@class='st-offr-blk']"));
				 
				  // list of all the bus
				  List<WebElement> buses = driver.findElements(By.xpath("//div[@style='max-width: calc(100% - 80px); display: inline-block;']"));
				                   
				
				  for(int i=0;i<AvailableSeats.size();i++)   // using for loop iterate seat 
				  {
		               String seat = AvailableSeats.get(i).getText();   // 25 seat ------>   separate it using split method
		               String seatNum[]=seat.split(" ");
		               String num =seatNum[0];        // index 0= seat num      intdex 1 = seat string
		               int seatNumber = Integer.valueOf(num);          // converted number string to integer
		               
					  String busname = buses.get(i).getText();         // get the each bus name
					  
					  if(seatNumber > 4)      // compare using if -----> Give the condition
					  {
						  System.out.println("Bus Name = "+busname + "  Available Seat = "+ seatNumber + " seats" );
					  }
					 
				  }

				  
				  driver.close();	  

	}

}
