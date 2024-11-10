package com.bssapp.TestBase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;

import com.bssapp.Utilities.DataBaseUtility;

public class DataBaseQueries extends BaseClass{
	

	public  void updateFlagsAdminCompanyProfile(String dataBase ,String coloumn,String subscriber,String companyID,String value) throws SQLException, ClassNotFoundException, IOException {
		//Transmission method should be Email
		String env=getProperty(envUrlPropertiesPath,"env");
		String QuerySelect = "select "+coloumn+",t.*,t.rowid from "+dataBase+".PSM_COMPANY_PROFILE t where t.subscriber_id="+subscriber+" and t.company_id="+companyID+"";
		System.out.println(QuerySelect);
		String UpdateQuery = "update "+dataBase+".PSM_COMPANY_PROFILE t set "+coloumn+"="+value+" where t.subscriber_id="+subscriber+" and t.company_id="+companyID+"";
		System.out.println(UpdateQuery);
		String commit = "commit";
		String coloumnValue="";
		if (env.equals("uat")){
			connectDB();
			ResultSet rs = executeQuery(QuerySelect);
			rs.next();
			coloumnValue = rs.getString(coloumn);
			System.out.println(coloumnValue);
		}
		if(coloumnValue!=value)
		{
			ResultSet rs_updatecoloumnValue;
			ResultSet rs_commit;
			rs_updatecoloumnValue=executeQuery(UpdateQuery);
			rs_commit = executeQuery(commit);
			ResultSet rs_AfterUpdate =executeQuery(QuerySelect);
			rs_AfterUpdate.next();
			String getUpdateValue = rs_AfterUpdate.getString(coloumn);
			System.out.println(getUpdateValue);
			Assert.assertEquals(getUpdateValue, value);

		}
		closeDB();

	}
	
	
	
	
}
//TCM--3046--Created this Class---aurehman--16th oct