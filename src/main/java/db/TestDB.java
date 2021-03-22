package db;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
	
        String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
        
        try {
			
        	Connection conn = DriverManager.getConnection(jdbcURL);
        	
        	String sql = "SELECT DISTINCT shape FROM sighting WHERE shape<>'' ORDER BY shape ASC";
			
        	PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUfo = new ArrayList<>();
			
			while(res.next()) {
				String forma = res.getString("shape");
				formeUfo.add(forma);
			}
			st.close();
			
			System.out.println(formeUfo);
			
			
			//scrivo una query parametrica
			String sql2 = "SELECT * FROM sightingn WHERE shape = ?";
			
			String shapeScelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			st2.setString(1, shapeScelta);
			ResultSet res2 = st2.executeQuery();
			
			res2.first();
			int count = res2.getInt("cnt");
			st2.close();
			
			System.out.println("Ufo di forma scelta "+shapeScelta+"sono: "+count);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
