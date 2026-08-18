package com.info.jobapp.company.impl;

import com.info.jobapp.company.Company;
import com.info.jobapp.company.CompanyRepository;
import com.info.jobapp.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository  companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> createAllCompanies(List<Company> companies) {
        return companyRepository.saveAll(companies);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Company existingCompany = getCompanyById(id);
        if(existingCompany == null){
           return false;
        }
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());
        existingCompany.setJobs(company.getJobs());
        companyRepository.save(existingCompany);
        return true;
    }

    @Override
    public Company getCompanyById(long id) {
        companyRepository.findById(id).orElse(null);
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        Company existingCompany = getCompanyById(id);
        if(existingCompany == null){
            return false;
        }
        companyRepository.deleteById(existingCompany.getId());
        return true;
    }
}