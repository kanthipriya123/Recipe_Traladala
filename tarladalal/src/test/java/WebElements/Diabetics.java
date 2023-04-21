package WebElements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.*;

public class Diabetics {



	public  WebDriver  ldriver;

	//Constructor for page object class

	public Diabetics(WebDriver rdriver)
	{

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}




	@FindBy (xpath = "//a[@title='Recipea A to Z']")WebElement Recipe;
	
	 @FindBy(xpath="//*[@id=\"ctl00_cntleftpanel_mnuAlphabetsn11\"]/table/tbody/tr/td/a") WebElement Alphebet; 
	
	 @FindBy(xpath="//span[@class='rcc_recipename']") List<WebElement> Recipenames; 
	
	 @FindBy(xpath="//div[starts-with(@id, 'rcp')]") List<WebElement> Recipeids; 
	 
	@FindBy(xpath="//time[@itemprop='prepTime']") WebElement preptime; 
	 
	 @FindBy(xpath="//time[@itemprop='cookTime']") WebElement cookTime; 
	 
	 @FindBy(xpath="//span[@itemprop='recipeIngredient']") List<WebElement> Ingradient; 
	
	 	 
	// @FindBy(xpath="//li[@itemtype='https://schema.org/HowToStep']") List<WebElement> Method; 
	 @FindBy(xpath="//*[@id='recipe_small_steps']") List<WebElement> Method;
	 
	
	 @FindBy(xpath="//table[@id='rcpnutrients']") WebElement Nutrienttable; 
	
	
	//Action
		
	
	public void click_Recipe_List()
	{
		Recipe.click();
		
	}
	
	public void click_Alphabet()
	{
		Alphebet.click();
		
		
	}

	public void gettext_Recipe_name() throws IOException
	{
		
		for(int i=1; i<Recipenames.size();i++)
		{
			String name =Recipenames.get(i).getText();
			excelutils.setcellData("Diabetics", i, 1, name);
					
		}
		
			
	}
		
		

	public void get_Recipeid() throws IOException
	{
		
		for(int i=1; i<Recipeids.size();i++)
		{
			String id =Recipeids.get(i).getAttribute("id");
			excelutils.setcellData("Diabetics", i, 0, id);
			excelutils.setcellData("Diabetics", i, 2, "Alphebet K");
			excelutils.setcellData("Diabetics", i, 3, "Vegitarian");
		}
		
		
	}
	
	public void open_recipe(int i)
	{
		
		Recipenames.get(i).click();
	 }
	
	
	public String preprationtime()
	{
		String Pt =preptime.getText().toString();
		return Pt;
			
	}
	
	public String cooktime()
	{
		String Ct= cookTime.getText().toString();
		return Ct;
			
	}
	
	public String ingradient()
	{
		  String Ig= "";
		for(int i=0; i<Ingradient.size();i++)
		{
			 Ig =Ig +Ingradient.get(i).getText().toString();
		 						 
		}	 
			
			 		
		return Ig;
			
	}
	
	
	public String Prep_Method() throws IOException
	{        
		String prep_method="";
		
		for(int i=0; i<Method.size();i++)
		{
			prep_method =prep_method+Method.get(i).getText().toString();
			
					
		}
		return prep_method;
	}
	
	
	public String Nutrients_values()
	{
		
		String Nu =Nutrienttable.getText().toString();
		
		return Nu;
	}
	
	}
