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
		
		String dispRow=null;
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.get("http://www.holidayiq.com/hotels/chikmagalur/");
		//driver.get("http://www.holidayiq.com/hotels/sakleshpur/");
		driver.get("http://www.holidayiq.com/hotels/coorg/");
		//driver.get("http://www.holidayiq.com/hotels/ooty/");
		//driver.get("http://www.holidayiq.com/hotels/mangalore/");
		
		
		//sign in
		driver.findElement(By.xpath("//*[@id='wrapper']/div[2]/div/div/span/div/a[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='reg_email']")).sendKeys("healberg.in@gmail.com");;
		driver.findElement(By.xpath("//*[@id='reg_pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id='email-login-btn']")).click();
		
		Thread.sleep(30000);
		
		do{
			List<WebElement> list  = driver.findElements(By.className("hotel-list-sub-heading"));
			//System.out.println("Number of listing: "+list.size());
			String parentWindow = driver.getWindowHandle();
			for(int i=0;i<list.size();i++){
				//System.out.println("-------------------------------------------------------------------------------------");
				dispRow="";
				String adTitle="";
				String address="";
				
				
				//write ad title
				try{
					adTitle = list.get(i).getText();
					//System.out.println("Listing title: "+adTitle);
					dispRow=dispRow+";"+adTitle;
					
				}catch(Exception e){
					//System.out.println("Error in writing title");
					dispRow=dispRow+";"+"No title";
				}
				
				//write address
				try{
					address = driver.findElement(By.xpath("//*[@id='hotel__FilterResult']/table["+(i+1)+"]/tbody/tr[2]/td[1]/div[2]/div[3]/span")).getText();
					//System.out.println("Listing title: "+adTitle);
					dispRow=dispRow+";"+address;
					
				}catch(Exception e){
					//System.out.println("Error in writing title");
					dispRow=dispRow+";"+"No address";
				}
				
				
				/*//write ad tariff
				try{
					//System.out.println("Tariff: "+driver.findElement(By.xpath("//*[@id='hotel__FilterResult']/table["+(i+1)+"]/tbody/tr[2]/td[3]/div/div/div/span[1]")).getText());
					dispRow=dispRow+";"+ driver.findElement(By.xpath("//*[@id='hotel__FilterResult']/table["+(i+1)+"]/tbody/tr[2]/td[3]/div/div/div/span[1]")).getText();
				}catch(Exception e){
					//System.out.println("No tariff");
					dispRow=dispRow+";"+"Error writing tariff";
				}*/
				
				dispRow=listingCnt+dispRow;
				System.out.println(dispRow);
				//open ad
				try{
					list.get(i).click();
				}catch(Exception e){
					
				}
				
				
				//iterate window handles
				Set <String> handles =driver.getWindowHandles();
				Iterator<String> it = handles.iterator();
				while (it.hasNext()){
					String newWin = it.next();
					if (!newWin.equals(parentWindow)){
						try{
							driver.switchTo().window(newWin);
							driver.manage().window().maximize();
							String pageTitle = driver.getTitle();
							if (pageTitle.contains(adTitle)){
								driver.findElement(By.xpath("//*[@id='SendContactDetails']/a")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//*[@id='contactInfoMobile_2']")).clear();
								driver.findElement(By.xpath("//*[@id='contactInfoMobile_2']")).sendKeys("8812345678");
								//driver.findElement(By.xpath("//*[@id='sendsmsForm']/fieldset/input[2]")).clear();
								//driver.findElement(By.xpath("//*[@id='sendsmsForm']/fieldset/input[2]")).sendKeys("holidayiq_112233445566@mailinator.com");
								driver.findElement(By.xpath("//*[@id='submitform']")).click();
								driver.close();
								
							}else{
								driver.close();
								
							}
						}catch(Exception e){
							
						}
					}
				}
					
				driver.switchTo().window(parentWindow);
				
				
				listingCnt++;
				//System.out.println(listingCnt);
				//System.out.println("-------------------------------------------------------------------------------------");
				
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
