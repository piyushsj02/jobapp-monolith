package com.info.jobapp.company;

import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController{

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {

        companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Company added successfully");
    }
    @PostMapping("/all")
    public ResponseEntity<String> createCompany(@RequestBody List<Company> companies) {
        List<Company> list = companyService.createAllCompanies(companies);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(list.size() + " Companies added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(id, company);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Company updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(company);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");

    }

}