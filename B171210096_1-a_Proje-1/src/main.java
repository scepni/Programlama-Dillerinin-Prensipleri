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
				System.out.println("Baðlantý Baþarýlý...");
			}
			else
			{
				System.out.println("Baðlantý Baþarýsýz...");	
			}		
	}
catch(Exception ex)
{
	System.out.println(ex.getMessage());
}
	Random r=new Random();
	int sicaklik=r.nextInt(100);	
	System.out.println("Lütfen Kullanýcý Adýnýzý Giriniz..");
	Scanner scan=new Scanner(System.in);
	String kullaniciadi=scan.next();
	System.out.println("Lütfen Þifrenizi Giriniz..");
	Scanner scan2=new Scanner(System.in);
	String sifre=scan2.next();
	//Root olmayan kullanýcýlarýn giriþi
	String query="select * from kullanici where kullaniciadi=? and sifre=? ";
	PreparedStatement statement =connection.prepareStatement(query);
	statement.setString(1,kullaniciadi);
	statement.setString(2, sifre);
	ResultSet set=statement.executeQuery();
	//Root Olan kullanýcýlarýn giriþi
	String query2="select * from kullanici where kullaniciadi=? and sifre=? and yetki=? ";
	PreparedStatement statement2 =connection.prepareStatement(query2);
	statement2.setString(1,kullaniciadi);
	statement2.setString(2, sifre);
	statement2.setString(3,"root");
	ResultSet set2=statement2.executeQuery();

	
	if(set.next())
	{
		System.out.println("Oturum Açýlýyor....");
		if(set2.next())
		{
		System.out.println("Sýcaklýk Deðeri:"+sicaklik);
		}
	}
	else
	{
		System.out.println("Kullanýcý Adý Veya Paralo Hatalý");
	}
	
	}
}
