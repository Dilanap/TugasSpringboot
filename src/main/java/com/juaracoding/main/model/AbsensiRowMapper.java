package com.juaracoding.main.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class AbsensiRowMapper implements RowMapper<Absensi>{
	@Override
	public Absensi mapRow(ResultSet ab, int rowNum) throws SQLException {

		Absensi absensi= new Absensi();
		absensi.setId(ab.getInt("id"));
		absensi.setNik(ab.getString("nik"));
		absensi.setStart_date(ab.getString("start_date"));
		absensi.setEnd_date(ab.getString("end_date"));
	
		
		// TODO Auto-generated method stub
		return absensi;
	}
}

