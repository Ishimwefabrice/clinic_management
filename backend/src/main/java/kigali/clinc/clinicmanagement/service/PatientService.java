package kigali.clinc.clinicmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.clinc.clinicmanagement.domain.Patient;
import kigali.clinc.clinicmanagement.repository.DoctorRepository;
import kigali.clinc.clinicmanagement.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    public String savePatient(Patient patient) {
        patientRepo.save(patient);
        return "Patient is saved Successfully";
    }

    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepo.findById(id);
    }

    public String updatePatient(Long id, Patient patient) {
        Optional<Patient> existing = patientRepo.findById(id);
        if (existing.isEmpty()) {
            return "Patient not found";
        }
        Patient dbPatient = existing.get();
        dbPatient.setSsNumber(patient.getSsNumber());
        dbPatient.setFirstName(patient.getFirstName());
        dbPatient.setLastName(patient.getLastName());
        dbPatient.setDateOfBirth(patient.getDateOfBirth());
        patientRepo.save(dbPatient);
        return "Patient is updated Successfully";
    }

    public String deletePatient(Long id) {
        if (!patientRepo.existsById(id)) {
            return "Patient not found";
        }
        patientRepo.deleteById(id);
        return "Patient is deleted Successfully";
    }

    public List<Patient> findByLastName(String lastName) {
        return patientRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public boolean doctorExists(Long doctorId) {
        return doctorRepo.existsById(doctorId);
    }

    public List<Patient> findPatientsOfDoctor(Long doctorId) {
        return patientRepo.findPatientsOfDoctor(doctorId);
    }

    public List<Patient> findFrequentPatients(long min) {
        return patientRepo.findFrequentPatients(min);
    }

    public Optional<Patient> findBySsNumber(String ssNumber) {
        return patientRepo.findBySsNumber(ssNumber);
    }

    public boolean existsBySsNumber(String ssNumber) {
        return patientRepo.existsBySsNumber(ssNumber);
    }
}
