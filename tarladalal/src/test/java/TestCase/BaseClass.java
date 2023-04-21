package TestCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.*;
import WebElements.Diabetics;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.IOException;
import java.time.Duration;




public class BaseClass {


	public static WebDriver driver;

	String path = ".//RecipeScrapping.xlsx";
	excelutils xlutil =new excelutils(path);
	String IG;
	String PM;
	String Nutrient;
	
	public static String[] DiabeticsElimination = {
			"Alcoholic beverages","Processed"," meat-Bacon","sausages", 
			"hot dos","deli meats","chicken nuggets","chciken patties","bacon",
			"Jams","Jelly","Pickled food","mango","cucumber","tomatoes",
			"Canned fruits/vegetables","pineapple","peaches","mixed fruit",
			"mandarine oranges","cherries","Chips","Mayonnaise","Palmolein oil",
			"Dried food- powdered milk","beans","peas","corn","Baked food","Doughnuts",
			"cakes","pastries", "cookies","croissants","Sweetened tea","coffee",
			"Packaged snacks","Soft drinks","Banana","melon","Dairy Milk","butter","cheese", 
			"Processed grains", "cream of rice","rice flour","rice rava","Sugar",
			"White rice","White bread","Pasta","Sweetened beverages","soda","flavoured water",
			"Gatorade","Fruit Juice","Apple Juice","orange juice"," pomegranate juice",
			"Trans fats found in margarines","peanut butter","spreads","frozen foods",
			"Flavoured curd/yogur","Sweetened breakfast","cereals-corn flakes","puffed rice",
			"bran flakes","instant oatmeal","Honey","Maple syrup","Jaggery","Sweets",
			"Candies","Chocolates","Refined/all purpose flour","paneer"};



	@BeforeClass
	public void OpenBrowser()
	{

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://www.tarladalal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));



	}

	@Test(priority=0)
	public void  excel_header() throws IOException
	{


		excelutils.setcellData("Diabetics", 0, 0, "Recipe ID");
		excelutils.setcellData("Diabetics", 0, 1, "Recipe Name");
		excelutils.setcellData("Diabetics", 0, 2, "Recipe category");
		excelutils.setcellData("Diabetics", 0, 3, "Food category");
		excelutils.setcellData("Diabetics", 0, 4, "Ingradients");
		excelutils.setcellData("Diabetics", 0, 5, "Prepration time");
		excelutils.setcellData("Diabetics", 0, 6, "Cooking time ");
		excelutils.setcellData("Diabetics", 0, 7, "Prepration method");
		excelutils.setcellData("Diabetics", 0, 8, "Nutrient values");
		excelutils.setcellData("Diabetics", 0, 9, "Targetted Morbid condition");
		excelutils.setcellData("Diabetics", 0, 10, "RecipeURL");

	}

	@Test(priority=1)
	public void scrapedata() throws IOException

	{


		Diabetics dia =new Diabetics(driver);
		dia.click_Recipe_List();
		for (int i=1;i<14;i++)

		{


			try

			{
				dia.click_Alphabet();
				dia.get_Recipeid();
				dia.gettext_Recipe_name(); 
				System.out.println("Recipename success");
			}
			catch(NoSuchElementException e)
			{

			}



			dia.open_recipe(i);

			String PT= dia.preprationtime();
			excelutils.setcellData("Diabetics", i, 5, PT);
			String CT= dia.cooktime();
			excelutils.setcellData("Diabetics", i, 6, CT);
			

			try
			{
				PM =dia.Prep_Method();
				excelutils.setcellData("Diabetics", i, 7, PM);
				System.out.println("prepname success");
			}
			catch(NoSuchElementException e)
			{
				excelutils.setcellData("Diabetics", i, 7, "Not available");
			}

			try
			{
				Nutrient  =dia.Nutrients_values();
				excelutils.setcellData("Diabetics", i, 8, Nutrient);
				System.out.println("Nutrient success");
			}
			catch(NoSuchElementException e)
			{
				excelutils.setcellData("Diabetics", i, 8, "NotAvaialble");
			}
			String pageurl = driver.getCurrentUrl();
			excelutils.setcellData("Diabetics", i, 10, pageurl);
			try

			{  
				IG = dia.ingradient();
				System.out.println(IG);
				
				for (int j = 0; j <= DiabeticsElimination.length-1; j++) 
				{

				    if (IG.contains(DiabeticsElimination[j])) 
				    	
						    {
				      
				    	excelutils.DeleteRow("Diabetics", i);
				    	System.out.println("Delete row  success");
				    	break;
				    }
				    else 
				    {
				    	 excelutils.setcellData("Diabetics",i, 4, IG);
				    	 System.out.println("Ingradient  success");
				    }
				    
				   
				}
												
				
			}
			catch(NoSuchElementException e)
			{
				excelutils.setcellData("Diabetics",i, 4, "Not available");
			}
			driver.navigate().back();
		}

	}


}



















