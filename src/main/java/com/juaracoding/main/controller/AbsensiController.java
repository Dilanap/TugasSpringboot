package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.dao.DaoAbsensi;
import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;

@RestController
@RequestMapping("/absensi")
public class AbsensiController {

	@Autowired
	JdbcTemplate jdbc;

	public List<Absensi> getAbsensi() {

		String sql = "Select * from absensi";

		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}
	
	public List<Absensi> SelectNik(String nik) {

		String sql = "SELECT * FROM absensi WHERE `nik`='"+nik+"'";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}
	
	public List<Absensi> SelectAbsensi() {

		String sql = "SELECT * FROM absensi WHERE YEAR (start_date) AND YEAR (end_date)='2021'";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}
	
/*	public List<Absensi> GetTahun(String start_date) {

		String sql = "SELECT * FROM absensi WHERE YEAR (start_date)='"+start_date+"'";
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}*/

	public int insertAbsensi(Absensi absensi) {
		return jdbc.update("insert into absensi(id,nik,start_date,end_date) values (" + absensi.getId() + ",'"
	+ absensi.getNik() + "','" + absensi.getStart_date() + "','" + absensi.getEnd_date() + "')");

	}

	
	public int updateAbsensi(int id, Absensi absensi) {

		return jdbc.update("UPDATE absensi SET `start_date`='" + absensi.getStart_date()
				+ "',`end_date`=" + absensi.getEnd_date() + " WHERE `id`= " + absensi.getId());

	}

	public int deleteAbsensi(int id) {
		return jdbc.update("DELETE FROM `absensi` WHERE `id` = " + id);
	}

	
	

	 @PostMapping("/")
	    public String add(@RequestBody Absensi absensi) {
		 

			if (this.insertAbsensi(absensi) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
	    }
	 
	 
	 
	 @DeleteMapping("/{id}")
	    public void delete(@PathVariable int id) {
		 	deleteAbsensi(id);
	 }
	 
	 
	 @GetMapping("/")
	    public List<Absensi> list() {
	        return getAbsensi();
	    }
	 
	 @GetMapping("/{nik}")
	  public List<Absensi> selectnik(@PathVariable String nik) {
	        return SelectNik(nik);
	    }
	 
	 @GetMapping("/tahun")
	  public List<Absensi> list1() {
	        return SelectAbsensi();
	    }

/*	 @GetMapping("/tahun/{start_date}")
	  public List<Absensi> cektahun(@PathVariable String start_date) {
	        return GetTahun(start_date);
	    }
*/
	
	@PutMapping("/{id}")
	    public ResponseEntity<?> update(@RequestBody Absensi absensi, @PathVariable int id) {
		 try {
	            updateAbsensi(id, absensi);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

}