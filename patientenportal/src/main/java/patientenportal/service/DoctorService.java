package patientenportal.service;

import java.util.List;

import patientenportal.dao.DoctorDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Doctor;


public class DoctorService {
	
	public List<Doctor> getDoctors(){
		DoctorDAOImpl ddi = new DoctorDAOImpl();
		List<Doctor> doctors = ddi.getAll();
		return doctors;
	}
	
	public Doctor getDoctorById(long doctorId){
		DoctorDAOImpl ddi = new DoctorDAOImpl();
		List<Doctor> doctors = ddi.getAll();
		for (Doctor d : doctors) {
			if (d.getId() == doctorId){
				return d;
			}
		}
		throw new DataNotFoundException("Doctor with id " + doctorId + " not found");
	}	

}
