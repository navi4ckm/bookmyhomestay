package holidyiq;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Holiday_scraper {

	public static void main(String[] args) throws InterruptedException {
		int listingCnt = 0;
		boolean nextButton = true;
		WebDriver driver = new FirefoxDriver();
		//driver.get("http://www.holidayiq.com/hotels/chikmagalur/");
		//driver.get("http://www.holidayiq.com/hotels/sakleshpur/");
		//driver.get("http://www.holidayiq.com/hotels/coorg/");
		//driver.get("http://www.holidayiq.com/hotels/ooty/");
		driver.get("http://www.holidayiq.com/hotels/mangalore/");
		do{
			List<WebElement> list  = driver.findElements(By.className("hotel-list-sub-heading"));
			//System.out.println("Number of listing: "+list.size());
			String parentWindow = driver.getWindowHandle();
			for(int i=0;i<list.size();i++){
				//System.out.println("-------------------------------------------------------------------------------------");
				String adTitle=null;
				try{
					adTitle = list.get(i).getText();
				}catch(Exception e){
					System.out.println("Error in writing title");
				}
				
				System.out.println("Listing title: "+adTitle);
				try{
					System.out.println("Tariff: "+driver.findElement(By.xpath("//*[@id='hotel__FilterResult']/table["+(i+1)+"]/tbody/tr[2]/td[3]/div/div/div/span[1]")).getText());
				}catch(Exception e){
					System.out.println("No tariff");
				}
				
				
				listingCnt++;
				System.out.println("-------------------------------------------------------------------------------------");
				
			}
			
			try{
				driver.findElement(By.xpath("//*[@id='next']")).click();
			}catch(Exception e){
				nextButton = false;
			}
			Thread.sleep(3000);
		} while(nextButton);
		System.out.println("Total number of listing: "+listingCnt);
		driver.quit();

	}

}
