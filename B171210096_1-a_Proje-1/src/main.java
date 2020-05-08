import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class main
{
public static void main(String[] args) throws SQLException
	{
	Connection connection=null;
	try 
	{
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123");
			if(connection!=null)
			{
				System.out.println("Ba�lant� Ba�ar�l�...");
			}
			else
			{
				System.out.println("Ba�lant� Ba�ar�s�z...");	
			}		
	}
catch(Exception ex)
{
	System.out.println(ex.getMessage());
}
	Random r=new Random();
	int sicaklik=r.nextInt(100);	
	System.out.println("L�tfen Kullan�c� Ad�n�z� Giriniz..");
	Scanner scan=new Scanner(System.in);
	String kullaniciadi=scan.next();
	System.out.println("L�tfen �ifrenizi Giriniz..");
	Scanner scan2=new Scanner(System.in);
	String sifre=scan2.next();
	//Root olmayan kullan�c�lar�n giri�i
	String query="select * from kullanici where kullaniciadi=? and sifre=? ";
	PreparedStatement statement =connection.prepareStatement(query);
	statement.setString(1,kullaniciadi);
	statement.setString(2, sifre);
	ResultSet set=statement.executeQuery();
	//Root Olan kullan�c�lar�n giri�i
	String query2="select * from kullanici where kullaniciadi=? and sifre=? and yetki=? ";
	PreparedStatement statement2 =connection.prepareStatement(query2);
	statement2.setString(1,kullaniciadi);
	statement2.setString(2, sifre);
	statement2.setString(3,"root");
	ResultSet set2=statement2.executeQuery();

	
	if(set.next())
	{
		System.out.println("Oturum A��l�yor....");
		if(set2.next())
		{
		System.out.println("S�cakl�k De�eri:"+sicaklik);
		}
	}
	else
	{
		System.out.println("Kullan�c� Ad� Veya Paralo Hatal�");
	}
	
	}
}
