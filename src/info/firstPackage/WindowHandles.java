package info.firstPackage;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandles {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//testing
		System.out.println("Window Handles example");
		System.setProperty("webdriver.chrome.driver", "D://selenium//chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		System.out.println("Browser initialization completed");
		driver.manage().window().maximize();
		System.out.println("Maximize window");
		driver.get("https://www.toolsqa.com/automation-practice-switch-windows/");
		System.out.println("hit the url :"+driver.getCurrentUrl());
		System.out.println("window handle");
		String ParentWindow=driver.getWindowHandle();
		System.out.println("Parent window :"+ParentWindow);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='Demo Website for Practice Automation']")).click();
System.out.println("clicked on link");
		Thread.sleep(10000);
		Set<String> allwindows =driver.getWindowHandles();
		
		System.out.println("multi window handles n store it in set object");
		
		
//		for(String handle:allwindows) {
//			if(!handle.equals(ParentWindow)) {
//				driver.switchTo().window(handle);
//				Thread.sleep(1000);
//				if(driver.getCurrentUrl().equals("https://demoqa.com/")) {
//					WebElement e=driver.findElement(By.xpath("//*/h1[contains(text(),'Home')]"));
//				System.out.println("text :"+ e.getText());	
//				}
//			}else {
//				System.out.println("window name :"+handle);
//			}
//		}
		System.out.println("size of all windows :"+allwindows.size());
		
		for(int i=0;i<allwindows.size();i++	) {
			
			if(!allwindows.toArray()[i].equals(ParentWindow)){
				driver.switchTo().window((String)allwindows.toArray()[i]);
				String childwindow= (String)allwindows.toArray()[i];
				System.out.println(" child window name :"+(String)allwindows.toArray()[i]);
				Thread.sleep(1000);
				if(driver.getCurrentUrl().equals("https://demoqa.com/")) {
					WebElement e=driver.findElement(By.xpath("//*/h1[contains(text(),'Home')]"));
					driver.findElement(By.xpath("//*[text()='Automation Practice Switch Windows']")).click();
					driver.findElement(By.id("button1")).click();
					driver.manage().window().maximize();
					Thread.sleep(1000);
					driver.getWindowHandles();
					Set<String> multiwindows=driver.getWindowHandles();
					System.out.println("size of multiwindows"+multiwindows.size());
					for(int j=0;j<multiwindows.size();j++) {
						if(!multiwindows.toArray()[j].equals(childwindow)&&!multiwindows.toArray()[j].equals(ParentWindow)) {
							String thirdwindow = (String)multiwindows.toArray()[j];
							System.out.println("thirdwindow :"+thirdwindow);
							driver.switchTo().window((String)multiwindows.toArray()[j]);
							driver.manage().window().maximize();
							Thread.sleep(5000);
							//driver.findElement(By.xpath("//*[@class='lazyloading']")).click();
							System.out.println("third window url :"+driver.getCurrentUrl());
							//driver.findElement(By.xpath("(//*[text()='HOME'])[1]")).click();
							System.out.println("third window validation :"+driver.findElement(By.xpath("(//*[text()='HOME'])[1]")).getText());
							driver.switchTo().window(childwindow);
						}
					}
					
				}
			
			}else {
				System.out.println("window name :"+(String)allwindows.toArray()[i]);
			}
		}
	
		
		
		driver.switchTo().window(ParentWindow);
		System.out.println("Switch back to parent window :"+ParentWindow);
		System.out.println(driver.findElement(By.xpath("//*[contains(text(),'Browser Windows')]")).getText());
		
		System.out.println(" successfully verified element on parent window");
	}

}
