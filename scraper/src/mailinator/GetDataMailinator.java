package mailinator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetDataMailinator {

	public static void main(String[] args) throws InterruptedException {
		String disp = null;
		WebDriver dr = new FirefoxDriver();
		dr.manage().window().maximize();
		dr.get("http://mailinator.com");
		dr.findElement(By.xpath("//*[@id='inboxfield']")).sendKeys("holidayiq_112233445566");
		dr.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[2]/div/div/btn")).click();
		Thread.sleep(5000);
		
		List<WebElement> mailList  = dr.findElements(By.className("subject"));
		
		System.out.println("Total mail: "+mailList.size());
		
		for(int i=0;i<mailList.size();i++){
			
			if(mailList.get(i).getText().contains("Call")){
				mailList.get(i).click();
				Thread.sleep(2000);
				disp = "";
				String subject = null;
				subject = dr.findElement(By.xpath("//*[@id='mailshowdivhead']/div[2]/div/div[1]/div/div[3]/div[2]")).getText();
				subject = subject.replaceAll("Call", "");
				subject = subject.replaceAll(": contact details you requested", "");
				disp = disp + ";" + subject;
				
				dr.switchTo().frame(1);
				
				String ph = null;
				try{
					ph = dr.findElement(By.xpath("html/body/div[1]/div/div/table/tbody/tr[2]/td[2]")).getText();
				}catch(Exception e){
					ph = "No phone";
				}
				
				String webUrl = null;
				
				try{
					webUrl = dr.findElement(By.xpath("html/body/div[1]/div/div/table/tbody/tr[3]/td[2]")).getText();
				}catch(Exception e){
					webUrl = "No website";
				}
				
				
				
				dr.switchTo().defaultContent();
				
				disp = disp + ";" + ph;
				disp = disp + ";" + webUrl;
				System.out.println(disp);
				dr.findElement(By.xpath("//*[@id='showmailpane']/div/div/div[1]/a")).click();
				Thread.sleep(2000);
			}
			
		}
		
		
		
		

	}

}
