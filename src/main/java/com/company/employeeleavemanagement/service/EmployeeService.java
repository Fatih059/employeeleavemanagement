package com.company.employeeleavemanagement.service;

import com.company.employeeleavemanagement.model.Employee;
import com.company.employeeleavemanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public Employee addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        // E-posta gönderme
        String subject = "Kafein Yazılım Şirketine Kaydınız Yapıldı";
        String message = String.format("Sayın %s %s,\n\nKafein Yazılım şirketine kaydınız yapılmıştır. Kullanıcı bilgileri şu şekildedir:\n\nID: %d\nFIRST NAME: %s\nLAST NAME: %s\nEMAIL: %s\nDEPARTMENT: %s\nREMAINING LEAVE DAYS: %d",
                savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getId(),
                savedEmployee.getFirstName(), savedEmployee.getLastName(),
                savedEmployee.getEmail(), savedEmployee.getDepartment(), savedEmployee.getRemainingLeaveDays());

        emailService.sendEmail(savedEmployee.getEmail(), subject, message);

        return savedEmployee;
    }

    public Optional<Employee> updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setRemainingLeaveDays(employeeDetails.getRemainingLeaveDays());
            Employee updatedEmployee = employeeRepository.save(employee);

            // E-posta gönderme
            String subject = "Kafein Yazılım Şirketi İçerisindeki Kaydınız Düzenlendi";
            String message = String.format("Sayın %s %s,\n\nKafein Yazılım şirketi içerisindeki kaydınız aşağıda gösterildiği üzere düzenlenmiştir:\n\nID: %d\nFIRST NAME: %s\nLAST NAME: %s\nEMAIL: %s\nDEPARTMENT: %s\nREMAINING LEAVE DAYS: %d",
                    updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getId(),
                    updatedEmployee.getFirstName(), updatedEmployee.getLastName(),
                    updatedEmployee.getEmail(), updatedEmployee.getDepartment(), updatedEmployee.getRemainingLeaveDays());

            emailService.sendEmail(updatedEmployee.getEmail(), subject, message);

            return Optional.of(updatedEmployee);
        }
        return Optional.empty();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Employee> updateLeaveDays(Long id, int leaveDays) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setRemainingLeaveDays(leaveDays);
            Employee updatedEmployee = employeeRepository.save(employee);

            // E-posta gönderme
            String subject = "Kafein Yazılım Şirketi İçerisindeki İzin Günleri Güncellendi";
            String message = String.format("Sayın %s %s,\n\nKafein Yazılım şirketi içerisindeki izin günleriniz aşağıda gösterildiği üzere güncellenmiştir:\n\nID: %d\nFIRST NAME: %s\nLAST NAME: %s\nEMAIL: %s\nDEPARTMENT: %s\nREMAINING LEAVE DAYS: %d",
                    updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getId(),
                    updatedEmployee.getFirstName(), updatedEmployee.getLastName(),
                    updatedEmployee.getEmail(), updatedEmployee.getDepartment(), updatedEmployee.getRemainingLeaveDays());

            emailService.sendEmail(updatedEmployee.getEmail(), subject, message);

            return Optional.of(updatedEmployee);
        }
        return Optional.empty();
    }
}
