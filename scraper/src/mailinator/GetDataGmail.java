package mailinator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetDataGmail {

	public static void main(String[] args) throws InterruptedException {
		String disp = "";
		WebDriver dr = new FirefoxDriver();
		dr.manage().window().maximize();
		dr.get("http://gmail.com");
		//dr.findElement(By.xpath("//*[@id='gmail-sign-in']")).click();
		dr.findElement(By.xpath("//*[@id='Email']")).sendKeys("healberg.in@gmail.com");
		dr.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("heal1234");
		dr.findElement(By.xpath("//*[@id='signIn']")).click();
		Thread.sleep(20000);
		for(int i=0;i<100;i++){
			
			
				String subject = null;
				WebElement sub = null;
				boolean mailOpen = false;
				disp="";
				try{
					sub = dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div/div[5]/div[1]/div/table/tbody/tr["+(i+1)+"]/td[6]/div/div/div/span[1]/b"));
					subject = sub.getText();
					if(subject.contains("Call")){
						subject = subject.replaceAll("Call", "");
						subject = subject.replaceAll(": contact details you requested", "");
						disp = disp + ";" + subject;
						sub.click();
						mailOpen = true;
						Thread.sleep(2000);
						
					}
				}catch(Exception e){
					mailOpen = false;
				}
				
				String ph = null;
				String webUrl = null;
				if(mailOpen==true){
					
					try{
						//ph = dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div[2]/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[6]/div/div[1]/table/tbody/tr[2]/td[2]")).getText();
						ph = dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[3]/div/div[2]/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[6]/div/div[1]/table/tbody/tr[2]/td[2]")).getText();
						
					}catch(Exception e){
						ph = "No phone";
					}
					
					try{
						//webUrl = dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div[2]/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[6]/div/div[1]/table/tbody/tr[3]/td[2]/a")).getText();
						webUrl = dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[3]/div/div[2]/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[6]/div/div[1]/table/tbody/tr[3]/td[2]/a")).getText();
					}catch(Exception e){
						webUrl = "No website";
					}
					
					disp = disp + ";" + ph;
					disp = disp + ";" + webUrl;
					disp = i+disp;
					System.out.println(disp);
					try{
						//dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/div/div[1]/div/div/div")).click();
						dr.findElement(By.xpath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[1]/div[3]/div[1]/div/div[1]/div/div/div")).click();
					}catch(Exception e){
						
					}
					
					Thread.sleep(2000);
				}
				
				
				
				
			
			
		}
		
		
		
		

	}

}
