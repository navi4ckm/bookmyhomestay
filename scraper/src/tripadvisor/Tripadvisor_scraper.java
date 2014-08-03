package tripadvisor;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tripadvisor_scraper {

	public static void main(String[] args) {

		WebDriver ff = new FirefoxDriver();
		ff.navigate().to("http://www.tripadvisor.in/Hotels-g297629-Chikamagalur_Karnataka-Hotels.html");
		//ff.get("http://www.tripadvisor.in/Hotels-g297629-Chikamagalur_Karnataka-Hotels.html");
		List<WebElement> title  = ff.findElements(By.className("property_title"));
		System.out.println("number of titles:"+title.size());
		String parentWindow = ff.getWindowHandle();
		for (int i=0;i<title.size();i++){
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			String adTitle = title.get(i).getText();
			System.out.println("Ad title: "+adTitle);
			title.get(i).click();
			Set <String> handles =ff.getWindowHandles();
			Iterator<String> it = handles.iterator();
			while (it.hasNext()){
				String newWin = it.next();
				if (!newWin.equals(parentWindow)){
					try{
							ff.switchTo().window(newWin);
							String pageTitle = ff.getTitle();
							if (pageTitle.contains(adTitle)){
								//System.out.println("Type: "+ff.findElement(By.xpath("//*[@id='HEADING_GROUP']/div[2]/address/span[2]")).getText());
								//System.out.println("Address: "+ff.findElement(By.className("format_address")).getText());
								//System.out.println("Additional information:");
								System.out.println(ff.findElement(By.xpath("//*[@class='bx03 address']/h3")).getText());
								System.out.println(ff.findElement(By.xpath("//*[@class='bx03 address']/div")).getText());
								//System.out.println("Address: "+ff.findElement(By.xpath("//*[@id='HEADING_GROUP']/div[2]/address/span[4]")).getText());
								//System.out.println("Title: "+ ff.getTitle());
								System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
								ff.close();
							}
							ff.close();
						}catch(Exception e){
							
						}
					}else{
						//ff.close();
					}
				
			}
			ff.switchTo().window(parentWindow);
			
			
		}
		
		ff.quit();

	}

}
